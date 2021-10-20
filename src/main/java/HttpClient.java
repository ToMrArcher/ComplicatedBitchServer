import java.io.IOException;
import java.net.Socket;

public class HttpClient {

    public HttpResponse get(String host, int port, String path) throws IOException {
        Socket socket = new Socket(host, port);
        socket.getOutputStream().write(createGetRequest(path, host).getBytes());

        return new HttpResponse(socket);
    }

    public HttpResponse post() {
        return null;
    }



    private String createGetRequest(String path, String url){
        return String.format("GET %s HTTP/1.1\r\n" +
                         "Host: %s\r\n" +
                         "Connection: close\r\n\r\n", path, url);
    }

    private String createPostRequest(String path, String messageBody){
        return String.format("POST %s HTTP/1.1\r\n" +
                             "Content-Length: %o\r\n\r\n" +
                             "%s", path, messageBody.getBytes().length, messageBody);
    }

    public static void main(String[] args) throws IOException {
        HttpClient httpClient = new HttpClient();
        HttpResponse response = httpClient.get("httpbin.org", 80, "/");
        HttpResponse response2 = httpClient.get("httpbin.org", 80, "/html");
        System.out.println(response.getMessageBody());
        System.out.println(response2.getMessageBody());
    }
}
