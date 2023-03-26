package socket;

import response.total.ResponseHandler;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private final ServerSocket serverSocket;
    private final ResponseHandler responseHandler;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        responseHandler = new ResponseHandler();
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                responseHandler.process(serverSocket.accept());
            } catch (IOException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
