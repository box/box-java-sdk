package com.box.sdkgen.managers.chunkeduploads;

import static com.box.sdkgen.internal.utils.UtilsManager.bufferLength;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStreamFromBuffer;
import static com.box.sdkgen.internal.utils.UtilsManager.hexToBase64;
import static com.box.sdkgen.internal.utils.UtilsManager.iterateChunks;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;
import static com.box.sdkgen.internal.utils.UtilsManager.prepareParams;
import static com.box.sdkgen.internal.utils.UtilsManager.readByteStream;
import static com.box.sdkgen.internal.utils.UtilsManager.reduceIterator;

import com.box.sdkgen.internal.utils.Hash;
import com.box.sdkgen.internal.utils.HashName;
import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.files.Files;
import com.box.sdkgen.schemas.uploadedpart.UploadedPart;
import com.box.sdkgen.schemas.uploadpart.UploadPart;
import com.box.sdkgen.schemas.uploadparts.UploadParts;
import com.box.sdkgen.schemas.uploadsession.UploadSession;
import com.box.sdkgen.serialization.json.JsonManager;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChunkedUploadsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public ChunkedUploadsManager() {
    this.networkSession = new NetworkSession();
  }

  protected ChunkedUploadsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public UploadSession createFileUploadSession(CreateFileUploadSessionRequestBody requestBody) {
    return createFileUploadSession(requestBody, new CreateFileUploadSessionHeaders());
  }

  public UploadSession createFileUploadSession(
      CreateFileUploadSessionRequestBody requestBody, CreateFileUploadSessionHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getUploadUrl(),
                            "/2.0/files/upload_sessions"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), UploadSession.class);
  }

  public UploadSession createFileUploadSessionForExistingFile(
      String fileId, CreateFileUploadSessionForExistingFileRequestBody requestBody) {
    return createFileUploadSessionForExistingFile(
        fileId, requestBody, new CreateFileUploadSessionForExistingFileHeaders());
  }

  public UploadSession createFileUploadSessionForExistingFile(
      String fileId,
      CreateFileUploadSessionForExistingFileRequestBody requestBody,
      CreateFileUploadSessionForExistingFileHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getUploadUrl(),
                            "/2.0/files/",
                            convertToString(fileId),
                            "/upload_sessions"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), UploadSession.class);
  }

  public UploadSession getFileUploadSessionByUrl(String url) {
    return getFileUploadSessionByUrl(url, new GetFileUploadSessionByUrlHeaders());
  }

  public UploadSession getFileUploadSessionByUrl(
      String url, GetFileUploadSessionByUrlHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(url, "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), UploadSession.class);
  }

  public UploadSession getFileUploadSessionById(String uploadSessionId) {
    return getFileUploadSessionById(uploadSessionId, new GetFileUploadSessionByIdHeaders());
  }

  public UploadSession getFileUploadSessionById(
      String uploadSessionId, GetFileUploadSessionByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getUploadUrl(),
                            "/2.0/files/upload_sessions/",
                            convertToString(uploadSessionId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), UploadSession.class);
  }

  public UploadedPart uploadFilePartByUrl(
      String url, InputStream requestBody, UploadFilePartByUrlHeaders headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(
                    entryOf("digest", convertToString(headers.getDigest())),
                    entryOf("content-range", convertToString(headers.getContentRange()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(url, "PUT")
                    .headers(headersMap)
                    .fileStream(requestBody)
                    .contentType("application/octet-stream")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), UploadedPart.class);
  }

  public UploadedPart uploadFilePart(
      String uploadSessionId, InputStream requestBody, UploadFilePartHeaders headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(
                    entryOf("digest", convertToString(headers.getDigest())),
                    entryOf("content-range", convertToString(headers.getContentRange()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getUploadUrl(),
                            "/2.0/files/upload_sessions/",
                            convertToString(uploadSessionId)),
                        "PUT")
                    .headers(headersMap)
                    .fileStream(requestBody)
                    .contentType("application/octet-stream")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), UploadedPart.class);
  }

  public void deleteFileUploadSessionByUrl(String url) {
    deleteFileUploadSessionByUrl(url, new DeleteFileUploadSessionByUrlHeaders());
  }

  public void deleteFileUploadSessionByUrl(
      String url, DeleteFileUploadSessionByUrlHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(url, "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  public void deleteFileUploadSessionById(String uploadSessionId) {
    deleteFileUploadSessionById(uploadSessionId, new DeleteFileUploadSessionByIdHeaders());
  }

  public void deleteFileUploadSessionById(
      String uploadSessionId, DeleteFileUploadSessionByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getUploadUrl(),
                            "/2.0/files/upload_sessions/",
                            convertToString(uploadSessionId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  public UploadParts getFileUploadSessionPartsByUrl(String url) {
    return getFileUploadSessionPartsByUrl(
        url,
        new GetFileUploadSessionPartsByUrlQueryParams(),
        new GetFileUploadSessionPartsByUrlHeaders());
  }

  public UploadParts getFileUploadSessionPartsByUrl(
      String url, GetFileUploadSessionPartsByUrlQueryParams queryParams) {
    return getFileUploadSessionPartsByUrl(
        url, queryParams, new GetFileUploadSessionPartsByUrlHeaders());
  }

  public UploadParts getFileUploadSessionPartsByUrl(
      String url, GetFileUploadSessionPartsByUrlHeaders headers) {
    return getFileUploadSessionPartsByUrl(
        url, new GetFileUploadSessionPartsByUrlQueryParams(), headers);
  }

  public UploadParts getFileUploadSessionPartsByUrl(
      String url,
      GetFileUploadSessionPartsByUrlQueryParams queryParams,
      GetFileUploadSessionPartsByUrlHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("offset", convertToString(queryParams.getOffset())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(url, "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), UploadParts.class);
  }

  public UploadParts getFileUploadSessionParts(String uploadSessionId) {
    return getFileUploadSessionParts(
        uploadSessionId,
        new GetFileUploadSessionPartsQueryParams(),
        new GetFileUploadSessionPartsHeaders());
  }

  public UploadParts getFileUploadSessionParts(
      String uploadSessionId, GetFileUploadSessionPartsQueryParams queryParams) {
    return getFileUploadSessionParts(
        uploadSessionId, queryParams, new GetFileUploadSessionPartsHeaders());
  }

  public UploadParts getFileUploadSessionParts(
      String uploadSessionId, GetFileUploadSessionPartsHeaders headers) {
    return getFileUploadSessionParts(
        uploadSessionId, new GetFileUploadSessionPartsQueryParams(), headers);
  }

  public UploadParts getFileUploadSessionParts(
      String uploadSessionId,
      GetFileUploadSessionPartsQueryParams queryParams,
      GetFileUploadSessionPartsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("offset", convertToString(queryParams.getOffset())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getUploadUrl(),
                            "/2.0/files/upload_sessions/",
                            convertToString(uploadSessionId),
                            "/parts"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), UploadParts.class);
  }

  public Files createFileUploadSessionCommitByUrl(
      String url,
      CreateFileUploadSessionCommitByUrlRequestBody requestBody,
      CreateFileUploadSessionCommitByUrlHeaders headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(
                    entryOf("digest", convertToString(headers.getDigest())),
                    entryOf("if-match", convertToString(headers.getIfMatch())),
                    entryOf("if-none-match", convertToString(headers.getIfNoneMatch()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(url, "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    if (convertToString(response.getStatus()).equals("202")) {
      return null;
    }
    return JsonManager.deserialize(response.getData(), Files.class);
  }

  public Files createFileUploadSessionCommit(
      String uploadSessionId,
      CreateFileUploadSessionCommitRequestBody requestBody,
      CreateFileUploadSessionCommitHeaders headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(
                    entryOf("digest", convertToString(headers.getDigest())),
                    entryOf("if-match", convertToString(headers.getIfMatch())),
                    entryOf("if-none-match", convertToString(headers.getIfNoneMatch()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getUploadUrl(),
                            "/2.0/files/upload_sessions/",
                            convertToString(uploadSessionId),
                            "/commit"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    if (convertToString(response.getStatus()).equals("202")) {
      return null;
    }
    return JsonManager.deserialize(response.getData(), Files.class);
  }

  public PartAccumulator reducer(PartAccumulator acc, InputStream chunk) {
    long lastIndex = acc.getLastIndex();
    List<UploadPart> parts = acc.getParts();
    byte[] chunkBuffer = readByteStream(chunk);
    Hash hash = new Hash(HashName.SHA1);
    hash.updateHash(chunkBuffer);
    String sha1 = hash.digestHash("base64");
    String digest = String.join("", "sha=", sha1);
    int chunkSize = bufferLength(chunkBuffer);
    long bytesStart = lastIndex + 1;
    long bytesEnd = lastIndex + chunkSize;
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
        this.uploadFilePartByUrl(
            acc.getUploadPartUrl(),
            generateByteStreamFromBuffer(chunkBuffer),
            new UploadFilePartByUrlHeaders(digest, contentRange));
    UploadPart part = uploadedPart.getPart();
    String partSha1 = hexToBase64(part.getSha1());
    assert partSha1.equals(sha1);
    assert part.getSize() == chunkSize;
    assert part.getOffset() == bytesStart;
    acc.getFileHash().updateHash(chunkBuffer);
    return new PartAccumulator(
        bytesEnd,
        Stream.concat(parts.stream(), Arrays.asList(part).stream()).collect(Collectors.toList()),
        acc.getFileSize(),
        acc.getUploadPartUrl(),
        acc.getFileHash());
  }

  public FileFull uploadBigFile(
      InputStream file, String fileName, long fileSize, String parentFolderId) {
    UploadSession uploadSession =
        this.createFileUploadSession(
            new CreateFileUploadSessionRequestBody(parentFolderId, fileSize, fileName));
    String uploadPartUrl = uploadSession.getSessionEndpoints().getUploadPart();
    String commitUrl = uploadSession.getSessionEndpoints().getCommit();
    String listPartsUrl = uploadSession.getSessionEndpoints().getListParts();
    long partSize = uploadSession.getPartSize();
    int totalParts = uploadSession.getTotalParts();
    assert partSize * totalParts >= fileSize;
    assert uploadSession.getNumPartsProcessed() == 0;
    Hash fileHash = new Hash(HashName.SHA1);
    Iterator<InputStream> chunksIterator = iterateChunks(file, partSize, fileSize);
    PartAccumulator results =
        reduceIterator(
            chunksIterator,
            this::reducer,
            new PartAccumulator(-1, Collections.emptyList(), fileSize, uploadPartUrl, fileHash));
    List<UploadPart> parts = results.getParts();
    UploadParts processedSessionParts = this.getFileUploadSessionPartsByUrl(listPartsUrl);
    assert processedSessionParts.getTotalCount() == totalParts;
    String sha1 = fileHash.digestHash("base64");
    String digest = String.join("", "sha=", sha1);
    Files committedSession =
        this.createFileUploadSessionCommitByUrl(
            commitUrl,
            new CreateFileUploadSessionCommitByUrlRequestBody(parts),
            new CreateFileUploadSessionCommitByUrlHeaders(digest));
    return committedSession.getEntries().get(0);
  }

  public Authentication getAuth() {
    return auth;
  }

  public NetworkSession getNetworkSession() {
    return networkSession;
  }

  public static class Builder {

    protected Authentication auth;

    protected NetworkSession networkSession;

    public Builder() {
      this.networkSession = new NetworkSession();
    }

    public Builder auth(Authentication auth) {
      this.auth = auth;
      return this;
    }

    public Builder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public ChunkedUploadsManager build() {
      return new ChunkedUploadsManager(this);
    }
  }
}
