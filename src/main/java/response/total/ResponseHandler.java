package response.total;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ResponseHandler {

    public void process(Socket socket) throws IOException {
        long beforeTime = System.currentTimeMillis();
        RequestData requestData = RequestData.getInstance(socket.getInputStream());
        String path = requestData.getNowPath();

        if ("/favicon.ico".equals(path)) {
            socket.close();
            return;
        }

        BufferedWriter messageToBrowser = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String response = ResponseConstructor.process(requestData);
        messageToBrowser.write(response);
        messageToBrowser.close();
        Logger.printLog(requestData, beforeTime, response);
    }


}
