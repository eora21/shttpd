package response.body;

import java.io.File;
import java.util.Arrays;
import java.util.StringJoiner;

public class HTMLConstructor {
    private HTMLConstructor() {

    }

    public static String process(File[] files) {
        StringJoiner stringJoiner = new StringJoiner("<br>");
        Arrays.stream(files)
                .map(HTMLConstructor::createHref)
                .forEach(stringJoiner::add);
        stringJoiner.add("<br><a href=\"./..\">[Back]</a>");
        stringJoiner.add("<a href=\"/\">[Home]</a>");
        return createHtmlData(stringJoiner.toString());
    }

    public static String createHtmlData(String data) {
        return String.format("<html>%s</html>", data);
    }

    private static String createPath(File file) {
        if (file.isFile()) {
            return file.getName();
        }

        return file.getName() + "/";
    }

    private static String createHref(File file) {
        String path = createPath(file);
        return String.format("<a href=\"./%s\">%s</a>", path, path);
    }
}
