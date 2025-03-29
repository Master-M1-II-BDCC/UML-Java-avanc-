package client.models;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String pseudo;

    public Client(String ip, int port, String pseudo) throws IOException {
        this.pseudo = pseudo;
        socket = new Socket(ip, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void envoyerMessage(String message) {
        out.println(pseudo + ": " + message);
    }

    public BufferedReader getReader() {
        return in;
    }

    public void fermerConnexion() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}
