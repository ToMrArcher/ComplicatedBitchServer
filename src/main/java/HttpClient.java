import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HttpClient {

    private Socket socket;
    private String url;
    private int port;
    private String path;
    private String requestMethod;

    private final ArrayList<String> allowedRequestMethods = new ArrayList<String>(List.of(new String[]{"GET", "POST"}));

    public HttpClient(String url, int port, String path, String requestMethod) throws IOException {
        this.url = url;
        this.port = port;
        this.path = path;
        this.requestMethod = requestMethod;

        if(!allowedRequestMethods.contains(requestMethod)){
            throw new IllegalArgumentException("WTF, you cant do this. Use a valid request method, like GET or POST");
        }

        try {
            this.socket = new Socket(url, port);
        } catch (IOException e) {
            System.out.println("Non existent URL or port. " + e.getMessage());;
        }

        if(requestMethod.equals("GET")){
            getRequest();
        }
        else if(requestMethod.equals("POST")){
            postRequest();
        }

    }

    private void getRequest() throws IOException {
        socket.getOutputStream().write(createGetRequest().getBytes());

        HttpResponse response = new HttpResponse(socket);
        System.out.println(response.getHeader("Content-Length"));
    }

    private void postRequest() {

    }



    private String createGetRequest(){
        return String.format("GET %s HTTP/1.1\r\n" +
                         "Host: %s\r\n" +
                         "Connection: close\r\n\r\n", path, url);
    }

    public static void main(String[] args) throws IOException {
        HttpClient httpClient = new HttpClient("httpbin.org", 80, "/html", "GET");
    }

    public int getStatusCode() {
        return 200;
    }
}
