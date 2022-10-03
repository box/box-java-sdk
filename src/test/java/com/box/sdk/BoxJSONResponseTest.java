package com.box.sdk;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import com.eclipsesource.json.ParseException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import org.junit.Test;

public class BoxJSONResponseTest {

    @Test
    public void whenJsonCannotBeParseJsonIsInException() throws IOException {
        HttpURLConnection mockedConnection = mock(HttpURLConnection.class);
        InputStream inputStream = new ByteArrayInputStream("Not a Json".getBytes(UTF_8));
        doReturn(200).when(mockedConnection).getResponseCode();
        doReturn(inputStream).when(mockedConnection).getInputStream();
        try {
            new BoxJSONResponse(mockedConnection).getJSON();
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Error parsing JSON:\nNot a Json"));
            assertThat(e.getCause().getClass(), is(ParseException.class));
        }
    }

    @Test
    public void whenOtherExceptionIsThrownItIsRethrown() throws IOException {
        HttpURLConnection mockedConnection = mock(HttpURLConnection.class);
        doReturn(200).when(mockedConnection).getResponseCode();
        doThrow(new RuntimeException("Some random exception")).when(mockedConnection).getInputStream();
        try {
            new BoxJSONResponse(mockedConnection).getJSON();
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Some random exception"));
            assertThat(e.getClass(), is(RuntimeException.class));
        }
    }
}
