import java.io.IOException;
import java.net.Socket;

public class HttpResponse extends HttpMessage {

    private final String statusCode;

    public HttpResponse(Socket socket) throws IOException {
        super(socket);

        this.statusCode = getStatusLine().split(" ")[1];
    }

    public String getStatusCode() {
        return statusCode;
    }
}
