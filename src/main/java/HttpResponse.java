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


    public static String create200Response(String connection, String messageBody){
        System.out.println(messageBody);
        System.out.println(messageBody.length());
        return String.format("HTTP/1.1 200 OK\r\n" +
                "Connection: %s\r\n" +
                "Content-Length: %o\r\n\r\n" +
                "%s", connection, messageBody.length(), messageBody);
    }

    public static String create404Response(String connection, String messageBody){
        return String.format("HTTP/1.1 404 Not Found\r\n" +
                "Connection: %s\r\n" +
                "Content-Length: %o\r\n\r\n" +
                "%s", connection, messageBody.getBytes().length, messageBody);
    }
}
