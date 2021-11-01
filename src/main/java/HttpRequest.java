import java.io.IOException;
import java.net.Socket;

public class HttpRequest extends HttpMessage{

    private final String requestMethod;
    private final String path;

    public HttpRequest(Socket socket) throws IOException {
        super(socket);
        String[] statusLines = getStatusLine().split(" ");
        this.requestMethod = statusLines[0];
        this.path = statusLines[1];
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getPath() {
        return path;
    }
}
