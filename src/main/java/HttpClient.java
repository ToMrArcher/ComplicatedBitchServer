import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HttpClient {

    private final ArrayList<String> allowedRequestMethods = new ArrayList<String>(List.of(new String[]{"GET", "POST"}));

    HttpResponse httpResponse;

    public void get(String host, int port, String path) throws IOException {
        Socket socket = new Socket(host, port);
        socket.getOutputStream().write(createGetRequest(path, host).getBytes());

        httpResponse = new HttpResponse(socket);
    }

    private void postRequest() {

    }



    private String createGetRequest(String path, String url){
        return String.format("GET %s HTTP/1.1\r\n" +
                         "Host: %s\r\n" +
                         "Connection: close\r\n\r\n", path, url);
    }

    public HttpResponse response(){
        return httpResponse;
    }

    public static void main(String[] args) throws IOException {
        HttpClient httpClient = new HttpClient();
        httpClient.get("httpbin.org", 80, "/html");
        System.out.println(httpClient.response().getMessageBody());
        httpClient.get("httpbin.org", 80, "/");
        System.out.println(httpClient.response().getMessageBody());

    }
}
