package ru.netology;

public class Main {

    public static void main(String[] args) {

        int port = 8080;
        String host = "127.0.0.1";

        Thread server = new Server(port);
        Thread client = new Client(port, host);

        server.start();
        client.start();

        try {
            server.join();
            client.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
