import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    
    //int num = 0;

    public String handleRequest(URI url) {
        if (url.getPath().contains("/add-message")) {
            String returnString = "";
            String[] queryx = url.getQuery().split("/add-message");
            for (String query : queryx){
                query = query.substring(query.indexOf("s="));
                String[] params = query.split("&");
                String user = "";
                String message = "";

                for(String param : params){
                    String[] keyValue = param.split("=");
                    if(param.contains("user")){
                        user = keyValue.length > 1 ? keyValue[1] : "";
                    }
                    else if (param.contains("s")){
                        message = keyValue.length > 1 ? keyValue[1] : "";
                    }
                }
                returnString += user + ": " + message + "\n";
            }
            return String.format(returnString);
            } else {
            return "404 Not Found!";
        }
    }
}

class ChatServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}