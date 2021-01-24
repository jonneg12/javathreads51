package ru.netology;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread{


    public static final String WRONG_NUMBER = "wrong number of Fibonacci series.";
    public static final String NOT_A_NUMBER = "wrong format of number of Fibonacci series.";

    int port;
    String host;

    public Client(int port, String host) {
        this.port = port;
        this.host = host;
    }

    @Override
    public void run() {
        Socket socket = null;
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Client socket started.");

            String msg;
            while (true) {
                System.out.println("CLIENT: Enter N (from 1 to 93) number to calculate the Nth term of the Fibonacci series: ");
                msg = scanner.nextLine();
                out.println(msg);
                if ("end".equals(msg)) {
                    System.out.println("Client stopped.");
                    break;
                }
                String result = in.readLine();
                System.out.print("SERVER: ");
                switch (result) {
                    case (WRONG_NUMBER):
                        System.out.println(WRONG_NUMBER);
                        break;
                    case (NOT_A_NUMBER):
                        System.out.println(NOT_A_NUMBER);
                        break;
                    default:
                        System.out.println( " value of " + msg + " number of Fibonacci series is " + result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
