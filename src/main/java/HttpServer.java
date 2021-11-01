import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    private final ServerSocket serverSocket;

    public HttpServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        new Thread(this::handleClients).start();
    }

    private void handleClients() {
        try{
            while(true) handleClient();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient() throws IOException {

        Socket socket = serverSocket.accept();
        HttpRequest request = new HttpRequest(socket);
        String requestMethod = request.getRequestMethod();
        switch (requestMethod) {
            case "GET":
                get(socket, request);
                break;
            case "POST":
                post(socket, request);
                break;
            default:
                throw new IllegalArgumentException();
        }

    }

    private void post(Socket socket, HttpRequest request) {

    }

    private void get(Socket socket, HttpRequest request) throws IOException {

        socket.getOutputStream().write(HttpResponse.create200Response("keep-alive", "Hello World!").getBytes());

    }

    public static void main(String[] args) {
        try {
            HttpServer server = new HttpServer(80);
            HttpClient client = new HttpClient();
            HttpResponse localhost = client.get("localhost", 80, "/");
            System.out.println(localhost.getHeader("Content-Length"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
