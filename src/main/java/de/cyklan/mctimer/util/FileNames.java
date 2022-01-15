package de.cyklan.mctimer.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileNames {
    public static String getLevelTimePath() {
        Path path = Paths.get(System.getProperty("user.dir"), "leveltimes");
        return path.toString();
    }

    public static String getConfigPath() {
        Path path = Paths.get(System.getProperty("user.dir"), "timerconfig.json");
        return path.toString();
    }
}
