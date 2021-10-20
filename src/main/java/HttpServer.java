import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    private ServerSocket serverSocket;

    public void HttpServer(int port) throws IOException {
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
                get(request);
                break;
            case "POST":
                post(request);
                break;
            default:
                throw new IllegalArgumentException();
        }

    }

    private void post(HttpRequest request) {

    }

    private void get(HttpRequest request) {

    }

}
