package response.method;

import response.total.RequestData;

public class PostHandler implements MethodHandler {
    @Override
    public String getResponseData(RequestData requestData) {
        /*
        requestData를 받는다
        헤더를 파악한다
        멀티폼이 아니면 터침
        데이터 받아서 파일로 변경
        저장
        */
        return "";
    }
}
