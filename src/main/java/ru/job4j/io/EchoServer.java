package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        boolean serverIsOpen = true;
        try (ServerSocket server = new ServerSocket(9000)) {
            while (serverIsOpen) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String string = input.readLine();
                    if (string.contains("msg=Hello")) {
                        output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        output.write("Hello, dear friend.".getBytes());
                    } else if (string.contains("msg=Exit")) {
                        serverIsOpen = false;
                    } else {
                        output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        output.write("What.".getBytes());
                    }
                    output.flush();
                }
            }
        }
    }
}
