import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Objects;

public class HttpMessage {

    private final String statusLine;
    private final Socket socket;
    private final HashMap<String, String> headers = new HashMap<>();
    private String messageBody = "";

    public HttpMessage(Socket socket) throws IOException {
        this.socket = socket;
        this.statusLine = getLine();

        String line;
        while(!(line = getLine()).isEmpty()){
            headers.put(line.substring(0, line.indexOf(":")), line.substring(line.indexOf(":") + 2));
        }

        if(headers.containsKey("Content-Length") && !Objects.equals(headers.get("Content-Length"), "0")){
            int c;
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < Integer.parseInt(headers.get("Content-Length")); i++){
                c = socket.getInputStream().read();
                builder.append((char)c);
            }
            messageBody = builder.toString();
        }
    }

    private String getLine() throws IOException {
        StringBuilder builder = new StringBuilder();

        int c;
        while((char)(c = socket.getInputStream().read()) != '\r'){
            builder.append((char)c);
        }
        socket.getInputStream().read();
        return builder.toString();
    }

    public String getHeader(String key){
        return headers.get(key);
    }

    public String getStatusLine() {
        return statusLine;
    }

    public String getMessageBody(){
        return messageBody;
    }
}
