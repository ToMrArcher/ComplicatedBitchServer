import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {

    private String statusLine;
    private Socket socket;
    private Map<String, String> headers = new HashMap();
    private String messageBody = "";

    public HttpResponse(Socket socket) throws IOException {
        this.socket = socket;
        this.statusLine = getLine();

        String line;
        while(!(line = getLine()).isEmpty()){
            headers.put(line.substring(0, line.indexOf(":")), line.substring(line.indexOf(":") + 2));
        }

        if(headers.containsKey("Content-Length") && headers.get("Content-Length") != "0"){
            int c;
            StringBuilder builder = new StringBuilder();
            while((c = socket.getInputStream().read()) != -1){
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
