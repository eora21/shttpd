package response.method;

import response.total.RequestData;

public class NotAllowedMethodHandler implements MethodHandler {
    @Override
    public String getResponseData(RequestData requestData) {
        throw new IllegalArgumentException("허용되지 않은 메서드입니다.");
    }
}
