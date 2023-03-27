package response.total;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ResponseHandler {
    private static final String DIRECTORY = System.getProperty("user.dir");

    public void process(Socket socket) throws IOException {
        long beforeTime = System.currentTimeMillis();
        RequestData requestData = RequestData.getInstance(socket.getInputStream());
        String path = requestData.getPath();

        if ("/favicon.ico".equals(path)) {
            socket.close();
            return;
        }

        BufferedWriter messageToBrowser = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String filePath = DIRECTORY + path;
        String method = requestData.getMethod();
        String response = ResponseConstructor.process(method, filePath);
        messageToBrowser.write(response);
        messageToBrowser.close();
        Logger.printLog(method, filePath, beforeTime, response);
    }


}
