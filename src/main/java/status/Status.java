package status;

public enum Status {
    OK(200, "Ok"),
    NO_CONTENT(204, "No Content"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    CONFLICT(409, "Conflict");

    private final int codeNumber;
    private final String statusTitle;

    Status(int codeNumber, String statusTitle) {
        this.codeNumber = codeNumber;
        this.statusTitle = statusTitle;
    }
    public String answer() {
        String code = String.valueOf(codeNumber);
        return String.format("%s %s", code, statusTitle);
    }
}
