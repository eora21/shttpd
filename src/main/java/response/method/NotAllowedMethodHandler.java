package response.method;

public class NotAllowedMethodHandler implements MethodHandler {
    @Override
    public String getResponseData(String filePath) {
        throw new IllegalArgumentException("허용되지 않은 메서드입니다.");
    }
}
