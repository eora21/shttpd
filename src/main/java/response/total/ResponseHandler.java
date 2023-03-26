package response.total;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ResponseHandler {
    private static final String DIRECTORY = System.getProperty("user.dir");

    public void process(Socket socket) throws IOException {
        long beforeTime = System.currentTimeMillis();
        Scanner request = new Scanner(socket.getInputStream());
        String method = request.next();
        String nowPath = URLDecoder.decode(request.next(), StandardCharsets.UTF_8);

        if ("/favicon.ico".equals(nowPath)) {
            socket.close();
            return;
        }

        BufferedWriter messageToBrowser = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String filePath = DIRECTORY + nowPath;
        String response = ResponseConstructor.process(method, filePath);
        messageToBrowser.write(response);
        messageToBrowser.close();
        Logger.printLog(method, filePath, beforeTime, response);
    }


}
