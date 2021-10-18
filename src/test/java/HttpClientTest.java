import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpClientTest {

    HttpClient httpClient;

    private void setup(String url, int port, String path, String requestMethod) throws IOException {
        httpClient = new HttpClient(url, port, path, requestMethod);
    }

    @Test
    void shouldThrowExceptionBecauseOfRequestMethod(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> setup("httpbin.org", 80, "/html", "GOT"));
    }

    @Test
    void shouldReturn200AsStatusCode() throws IOException {
        setup("httpbin.org", 80, "/html", "GET");
        int statusCode = httpClient.getStatusCode();
        assertEquals(200, statusCode);
    }

}


