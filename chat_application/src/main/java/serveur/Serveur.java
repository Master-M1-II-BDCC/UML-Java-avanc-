package serveur;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Serveur {
    private static final int PORT = 1234;
    private static final List<PrintWriter> clients = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Serveur lancé sur le port : " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socketClient = serverSocket.accept();
                System.out.println("✅ Nouveau client connecté : " + socketClient);

                PrintWriter out = new PrintWriter(socketClient.getOutputStream(), true);
                clients.add(out);

                Thread thread = new Thread(() -> {
                    try (Scanner in = new Scanner(socketClient.getInputStream())) {
                        while (in.hasNextLine()) {
                            String message = in.nextLine();
                            System.out.println("📩 Message reçu : " + message);

                            for (PrintWriter client : clients) {
                                client.println(message);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("⚠️ Client déconnecté.");
                    }
                });
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
