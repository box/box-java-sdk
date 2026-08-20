package com.box.sdkgen.internal.utils;

import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStream;
import static com.box.sdkgen.internal.utils.UtilsManager.iterateChunks;
import static com.box.sdkgen.internal.utils.UtilsManager.readByteStream;
import static com.box.sdkgen.internal.utils.UtilsManager.reduceIterator;

import java.io.InputStream;
import java.util.Iterator;
import org.junit.jupiter.api.Test;

public class UtilsManagerTest {

  @Test
  public void testIterateChunksWhenFileSizeIsExactMultipleOfChunkSize() {
    int chunkSize = 8;
    int fileSize = 16;
    drainAndAssertChunks(chunkSize, fileSize, fileSize, 2);
  }

  @Test
  public void testIterateChunksWhenFileSizeHasRemainder() {
    int chunkSize = 8;
    int fileSize = 20;
    drainAndAssertChunks(chunkSize, fileSize, fileSize, 3);
  }

  @Test
  public void testIterateChunksReadsUntilEofWhenReportedFileSizeDoesNotMatch() {
    int chunkSize = 8;
    int streamSize = 16;
    drainAndAssertChunks(chunkSize, streamSize, -1, 2);
  }

  private static void drainAndAssertChunks(
      int chunkSize, int streamSize, long reportedFileSize, int expectedChunkCount) {
    Iterator<InputStream> chunks =
        iterateChunks(generateByteStream(streamSize), chunkSize, reportedFileSize);
    ChunkDrainResult result =
        reduceIterator(chunks, UtilsManagerTest::reduceChunks, new ChunkDrainResult());
    assert result.chunkCount == expectedChunkCount;
    assert result.totalBytes == streamSize;
  }

  private static ChunkDrainResult reduceChunks(ChunkDrainResult acc, InputStream chunk) {
    assert chunk != null;
    byte[] bytes = readByteStream(chunk);
    acc.chunkCount += 1;
    acc.totalBytes += bytes.length;
    return acc;
  }

  private static final class ChunkDrainResult {
    private int chunkCount;
    private int totalBytes;
  }
}
