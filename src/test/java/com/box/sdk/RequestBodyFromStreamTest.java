package com.box.sdk;

import static com.box.sdk.AbstractBoxMultipartRequest.BUFFER_SIZE;

import java.io.ByteArrayInputStream;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.MediaType;
import okio.Buffer;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class RequestBodyFromStreamTest {

    @Test
    public void reportCorrectProgressWhenFileIsEmpty() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[]{});
        ProgressListener progressListener = (numBytes, totalBytes) -> {
            MatcherAssert.assertThat(numBytes, Matchers.is((long) 0));
            MatcherAssert.assertThat(totalBytes, Matchers.is((long) 0));
        };

        RequestBodyFromStream request = new RequestBodyFromStream(
            inputStream,
            MediaType.parse("application/json"),
            progressListener,
            0
        );

        request.writeTo(new Buffer());
    }

    @Test
    public void reportCorrectProgressWhenFileSizeIfLessThanBuffer() {
        int howManyBytes = 1000;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(generateBytes(howManyBytes));
        ProgressListener progressListener = (numBytes, totalBytes) -> {
            MatcherAssert.assertThat(numBytes, Matchers.is((long) howManyBytes));
            MatcherAssert.assertThat(totalBytes, Matchers.is((long) howManyBytes));
        };

        RequestBodyFromStream request = new RequestBodyFromStream(
            inputStream,
            MediaType.parse("application/json"),
            progressListener,
            howManyBytes
        );

        request.writeTo(new Buffer());
    }

    @Test
    public void reportCorrectProgressWhenFileSizeIfEqualToBuffer() {
        int howManyBytes = BUFFER_SIZE;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(generateBytes(howManyBytes));
        ProgressListener progressListener = (numBytes, totalBytes) -> {
            MatcherAssert.assertThat(numBytes, Matchers.is((long) howManyBytes));
            MatcherAssert.assertThat(totalBytes, Matchers.is((long) howManyBytes));
        };

        RequestBodyFromStream request = new RequestBodyFromStream(
            inputStream,
            MediaType.parse("application/json"),
            progressListener,
            howManyBytes
        );

        request.writeTo(new Buffer());
    }

    @Test
    public void reportCorrectProgressWhenFileSizeIfGreaterThanBuffer() {
        int howManyBytes = BUFFER_SIZE + 1000;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(generateBytes(howManyBytes));
        AtomicInteger counter = new AtomicInteger(0);
        ProgressListener progressListener = (numBytes, totalBytes) -> {
            if (counter.getAndIncrement() == 0) {
                MatcherAssert.assertThat(numBytes, Matchers.is((long) BUFFER_SIZE));
                MatcherAssert.assertThat(totalBytes, Matchers.is((long) howManyBytes));
            } else {
                MatcherAssert.assertThat(numBytes, Matchers.is((long) howManyBytes));
                MatcherAssert.assertThat(totalBytes, Matchers.is((long) howManyBytes));
            }
        };

        RequestBodyFromStream request = new RequestBodyFromStream(
            inputStream,
            MediaType.parse("application/json"),
            progressListener,
            howManyBytes
        );

        request.writeTo(new Buffer());
    }

    private byte[] generateBytes(int howManyBytes) {
        byte[] bytes = new byte[howManyBytes];
        new Random().nextBytes(bytes);
        return bytes;
    }
}
