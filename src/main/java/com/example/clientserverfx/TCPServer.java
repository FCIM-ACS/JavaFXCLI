import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.*;
import java.security.KeyPair;

public class TCPServer {
    public static KeyPair keyPair;

    static {
        try {
            keyPair = CustomSecurity.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String args[]) throws Exception {
        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(11169);
        System.out.println(InetAddress.getLocalHost().getHostAddress());
        System.out.println(InetAddress.getByName("localhost"));
        while(true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            SocketAddress clientAddress = connectionSocket.getRemoteSocketAddress();
            System.out.println("(Подключился клиент с адрессом): " + clientAddress);
            clientSentence = inFromClient.readLine();
            clientSentence = CustomSecurity.decryptData(clientSentence, keyPair.getPrivate());
            clientSentence = CustomSecurity.wordSecurity(clientSentence);

            /*String apiKey = "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";
            URL url = new URL("https://api.openai.com/v1/engines/davinci/completions");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setDoOutput(true);
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            String jsonInputString = "{\"prompt\": \"" + clientSentence + "\", \"max_tokens\": 150}";
            outputStream.writeBytes(jsonInputString);
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = responseReader.readLine()) != null) {
                response.append(inputLine);
            }
            responseReader.close();

            outToClient.writeBytes(response.toString() + '\n');*/
            outToClient.writeBytes(clientSentence);
            inFromClient.close();
            outToClient.close();
            connectionSocket.close();
        }
    }
}