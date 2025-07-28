package com.box.sdkgen.test.chunkeduploads;

import static com.box.sdkgen.internal.utils.UtilsManager.bufferLength;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStream;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStreamFromBuffer;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.internal.utils.UtilsManager.hexToBase64;
import static com.box.sdkgen.internal.utils.UtilsManager.iterateChunks;
import static com.box.sdkgen.internal.utils.UtilsManager.readByteStream;
import static com.box.sdkgen.internal.utils.UtilsManager.reduceIterator;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.internal.utils.Hash;
import com.box.sdkgen.internal.utils.HashName;
import com.box.sdkgen.managers.chunkeduploads.CreateFileUploadSessionCommitByUrlHeaders;
import com.box.sdkgen.managers.chunkeduploads.CreateFileUploadSessionCommitByUrlRequestBody;
import com.box.sdkgen.managers.chunkeduploads.CreateFileUploadSessionCommitHeaders;
import com.box.sdkgen.managers.chunkeduploads.CreateFileUploadSessionCommitRequestBody;
import com.box.sdkgen.managers.chunkeduploads.CreateFileUploadSessionRequestBody;
import com.box.sdkgen.managers.chunkeduploads.UploadFilePartByUrlHeaders;
import com.box.sdkgen.managers.chunkeduploads.UploadFilePartHeaders;
import com.box.sdkgen.schemas.file.File;
import com.box.sdkgen.schemas.files.Files;
import com.box.sdkgen.schemas.uploadedpart.UploadedPart;
import com.box.sdkgen.schemas.uploadpart.UploadPart;
import com.box.sdkgen.schemas.uploadparts.UploadParts;
import com.box.sdkgen.schemas.uploadsession.UploadSession;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class ChunkedUploadsITest {

  private static final BoxClient client = getDefaultClient();

  public static TestPartAccumulator reducerById(TestPartAccumulator acc, InputStream chunk) {
    int lastIndex = acc.getLastIndex();
    List<UploadPart> parts = acc.getParts();
    byte[] chunkBuffer = readByteStream(chunk);
    Hash hash = new Hash(HashName.SHA1);
    hash.updateHash(chunkBuffer);
    String sha1 = hash.digestHash("base64");
    String digest = String.join("", "sha=", sha1);
    int chunkSize = bufferLength(chunkBuffer);
    int bytesStart = lastIndex + 1;
    int bytesEnd = lastIndex + chunkSize;
    String contentRange =
        String.join(
            "",
            "bytes ",
            convertToString(bytesStart),
            "-",
            convertToString(bytesEnd),
            "/",
            convertToString(acc.getFileSize()));
    UploadedPart uploadedPart =
        client
            .getChunkedUploads()
            .uploadFilePart(
                acc.getUploadSessionId(),
                generateByteStreamFromBuffer(chunkBuffer),
                new UploadFilePartHeaders(digest, contentRange));
    UploadPart part = uploadedPart.getPart();
    String partSha1 = hexToBase64(part.getSha1());
    assert partSha1.equals(sha1);
    assert part.getSize() == chunkSize;
    assert part.getOffset() == bytesStart;
    acc.getFileHash().updateHash(chunkBuffer);
    return new TestPartAccumulator.Builder(
            bytesEnd,
            Stream.concat(parts.stream(), Arrays.asList(part).stream())
                .collect(Collectors.toList()),
            acc.getFileSize(),
            acc.getFileHash())
        .uploadSessionId(acc.getUploadSessionId())
        .build();
  }

  public static TestPartAccumulator reducerByUrl(TestPartAccumulator acc, InputStream chunk) {
    int lastIndex = acc.getLastIndex();
    List<UploadPart> parts = acc.getParts();
    byte[] chunkBuffer = readByteStream(chunk);
    Hash hash = new Hash(HashName.SHA1);
    hash.updateHash(chunkBuffer);
    String sha1 = hash.digestHash("base64");
    String digest = String.join("", "sha=", sha1);
    int chunkSize = bufferLength(chunkBuffer);
    int bytesStart = lastIndex + 1;
    int bytesEnd = lastIndex + chunkSize;
    String contentRange =
        String.join(
            "",
            "bytes ",
            convertToString(bytesStart),
            "-",
            convertToString(bytesEnd),
            "/",
            convertToString(acc.getFileSize()));
    UploadedPart uploadedPart =
        client
            .getChunkedUploads()
            .uploadFilePartByUrl(
                acc.getUploadPartUrl(),
                generateByteStreamFromBuffer(chunkBuffer),
                new UploadFilePartByUrlHeaders(digest, contentRange));
    UploadPart part = uploadedPart.getPart();
    String partSha1 = hexToBase64(part.getSha1());
    assert partSha1.equals(sha1);
    assert part.getSize() == chunkSize;
    assert part.getOffset() == bytesStart;
    acc.getFileHash().updateHash(chunkBuffer);
    return new TestPartAccumulator.Builder(
            bytesEnd,
            Stream.concat(parts.stream(), Arrays.asList(part).stream())
                .collect(Collectors.toList()),
            acc.getFileSize(),
            acc.getFileHash())
        .uploadPartUrl(acc.getUploadPartUrl())
        .build();
  }

  @Test
  public void testChunkedManualProcessById() {
    int fileSize = 20 * 1024 * 1024;
    InputStream fileByteStream = generateByteStream(fileSize);
    String fileName = getUuid();
    String parentFolderId = "0";
    UploadSession uploadSession =
        client
            .getChunkedUploads()
            .createFileUploadSession(
                new CreateFileUploadSessionRequestBody(parentFolderId, fileSize, fileName));
    String uploadSessionId = uploadSession.getId();
    long partSize = uploadSession.getPartSize();
    int totalParts = uploadSession.getTotalParts();
    assert partSize * totalParts >= fileSize;
    assert uploadSession.getNumPartsProcessed() == 0;
    Hash fileHash = new Hash(HashName.SHA1);
    Iterator<InputStream> chunksIterator = iterateChunks(fileByteStream, partSize, fileSize);
    TestPartAccumulator results =
        reduceIterator(
            chunksIterator,
            (TestPartAccumulator acc, InputStream chunk) -> reducerById(acc, chunk),
            new TestPartAccumulator.Builder(-1, Collections.emptyList(), fileSize, fileHash)
                .uploadSessionId(uploadSessionId)
                .build());
    List<UploadPart> parts = results.getParts();
    UploadParts processedSessionParts =
        client.getChunkedUploads().getFileUploadSessionParts(uploadSessionId);
    assert processedSessionParts.getTotalCount() == totalParts;
    UploadSession processedSession =
        client.getChunkedUploads().getFileUploadSessionById(uploadSessionId);
    assert processedSession.getId().equals(uploadSessionId);
    String sha1 = fileHash.digestHash("base64");
    String digest = String.join("", "sha=", sha1);
    Files committedSession =
        client
            .getChunkedUploads()
            .createFileUploadSessionCommit(
                uploadSessionId,
                new CreateFileUploadSessionCommitRequestBody(parts),
                new CreateFileUploadSessionCommitHeaders(digest));
    assert committedSession.getEntries().get(0).getName().equals(fileName);
    client.getChunkedUploads().deleteFileUploadSessionById(uploadSessionId);
  }

  @Test
  public void testChunkedManualProcessByUrl() {
    int fileSize = 20 * 1024 * 1024;
    InputStream fileByteStream = generateByteStream(fileSize);
    String fileName = getUuid();
    String parentFolderId = "0";
    UploadSession uploadSession =
        client
            .getChunkedUploads()
            .createFileUploadSession(
                new CreateFileUploadSessionRequestBody(parentFolderId, fileSize, fileName));
    String uploadPartUrl = uploadSession.getSessionEndpoints().getUploadPart();
    String commitUrl = uploadSession.getSessionEndpoints().getCommit();
    String listPartsUrl = uploadSession.getSessionEndpoints().getListParts();
    String statusUrl = uploadSession.getSessionEndpoints().getStatus();
    String abortUrl = uploadSession.getSessionEndpoints().getAbort();
    String uploadSessionId = uploadSession.getId();
    long partSize = uploadSession.getPartSize();
    int totalParts = uploadSession.getTotalParts();
    assert partSize * totalParts >= fileSize;
    assert uploadSession.getNumPartsProcessed() == 0;
    Hash fileHash = new Hash(HashName.SHA1);
    Iterator<InputStream> chunksIterator = iterateChunks(fileByteStream, partSize, fileSize);
    TestPartAccumulator results =
        reduceIterator(
            chunksIterator,
            (TestPartAccumulator acc, InputStream chunk) -> reducerByUrl(acc, chunk),
            new TestPartAccumulator.Builder(-1, Collections.emptyList(), fileSize, fileHash)
                .uploadPartUrl(uploadPartUrl)
                .build());
    List<UploadPart> parts = results.getParts();
    UploadParts processedSessionParts =
        client.getChunkedUploads().getFileUploadSessionPartsByUrl(listPartsUrl);
    assert processedSessionParts.getTotalCount() == totalParts;
    UploadSession processedSession =
        client.getChunkedUploads().getFileUploadSessionByUrl(statusUrl);
    assert processedSession.getId().equals(uploadSessionId);
    String sha1 = fileHash.digestHash("base64");
    String digest = String.join("", "sha=", sha1);
    Files committedSession =
        client
            .getChunkedUploads()
            .createFileUploadSessionCommitByUrl(
                commitUrl,
                new CreateFileUploadSessionCommitByUrlRequestBody(parts),
                new CreateFileUploadSessionCommitByUrlHeaders(digest));
    assert committedSession.getEntries().get(0).getName().equals(fileName);
    client.getChunkedUploads().deleteFileUploadSessionByUrl(abortUrl);
  }

  @Test
  public void testChunkedUploadConvenienceMethod() {
    int fileSize = 20 * 1024 * 1024;
    InputStream fileByteStream = generateByteStream(fileSize);
    String fileName = getUuid();
    String parentFolderId = "0";
    File uploadedFile =
        client
            .getChunkedUploads()
            .uploadBigFile(fileByteStream, fileName, fileSize, parentFolderId);
    assert uploadedFile.getName().equals(fileName);
    assert uploadedFile.getSize() == fileSize;
    assert uploadedFile.getParent().getId().equals(parentFolderId);
    client.getFiles().deleteFileById(uploadedFile.getId());
  }
}
