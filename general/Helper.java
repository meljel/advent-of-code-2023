package general;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Helper {
    private static final String userDir = System.getProperty("user.dir");

    public static String readFile(String filepath) throws IOException {
        return Files.readString(Paths.get(filepath));
    }

    public static String getUserDir() {
        return userDir;
    }
}
