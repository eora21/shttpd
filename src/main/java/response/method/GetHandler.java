package response.method;

import response.body.HTMLConstructor;
import response.total.RequestData;
import status.Status;

import java.io.File;
import java.io.IOException;

import static response.total.ResponseConstructor.response;

public class GetHandler implements MethodHandler {
    @Override
    public String getResponseData(RequestData requestData) throws IOException {
        File file = new File(requestData.getFilePath());

        if (!file.exists()) {
            return response(Status.NOT_FOUND);
        }

        if (!file.canRead()) {
            return response(Status.FORBIDDEN);
        }

        if (file.isDirectory()) {
            return response(HTMLConstructor.process(file.listFiles()), Status.OK);
        }

        return response(file);
    }
}
