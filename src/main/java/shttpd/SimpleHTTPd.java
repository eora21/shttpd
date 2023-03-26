package shttpd;

import socket.Server;

import java.io.IOException;

public class SimpleHTTPd {
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(args[0]);
        new Server(port).run();
    }

}
