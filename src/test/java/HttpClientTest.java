import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class HttpClientTest {

    HttpClient httpClient;

    private void setup() throws IOException {
        httpClient = new HttpClient();
    }

    @Test
    void shouldThrowExceptionBecauseOfRequestMethod(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> setup());
    }

}


