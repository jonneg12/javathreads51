package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static ru.netology.Client.NOT_A_NUMBER;
import static ru.netology.Client.WRONG_NUMBER;


public class Server extends Thread {

    private int port;

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try (Socket socket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                System.out.println("Server socket started.");

                int numberFibSer;
                String line;
                while (!(line = in.readLine()).isEmpty()){
                    if (line.equals("end")) {
                        System.out.println("Server stopped.");
                        break;
                    }
                    try {
                        numberFibSer = Integer.parseInt(line);
                        if (numberFibSer < 1 || numberFibSer > 93) {
                            out.println(WRONG_NUMBER);
                        } else {
                            out.println(new Fibonacci().getNFibonacci(numberFibSer));
                        }
                    } catch  (NumberFormatException e) {
                        out.println(NOT_A_NUMBER);
                    }
                }
                break;
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
    }
}

