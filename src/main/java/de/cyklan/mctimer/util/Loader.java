package de.cyklan.mctimer.util;

import de.cyklan.mctimer.MCTimer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class Loader {
    private final Converter converter = new Converter();

    public void createLevelTimesFileIfNotExists() {
        File f = new File(FileNames.getLevelTimePath());
        if (createFileIfNotExists(f)) {
            writeTimes(new LevelTime());
        }
    }

    public void createConfigFileIfNotExists() {
        File f = new File(FileNames.getConfigPath());
        if (createFileIfNotExists(f)) {
            writeConfig(new Config());
        }
    }

    private boolean createFileIfNotExists(File file) {
        try {
            return file.createNewFile();
        } catch (Exception e) {
            return false;
        }
    }

    public LevelTime readTimes() {
        Object o = null;
        try {
            InputStream in = new FileInputStream(FileNames.getLevelTimePath());
            byte[] bytes = in.readAllBytes();
            o = converter.bytesToObject(bytes);
        } catch (Exception e) {
            MCTimer.LOGGER.error(e.getMessage());
        }

        return Objects.requireNonNullElseGet((LevelTime) o, LevelTime::new);
    }

    public void writeTimes(LevelTime time) {
        byte[] bytes = converter.objectToBytes(time);
        String path = FileNames.getLevelTimePath();
        try {
            OutputStream out = new FileOutputStream(path);
            out.write(bytes);
        } catch (Exception e) {
            MCTimer.LOGGER.error(e.getMessage());
        }
    }

    public Config readConfig() {
        Config config = null;
        String path = FileNames.getConfigPath();
        try {
            String json = Files.readAllLines(Paths.get(path)).get(0);
            config = converter.JSONToObject(json, Config.class);
        } catch (Exception e) {
            MCTimer.LOGGER.error(e.getMessage());
        }

        return Objects.requireNonNullElseGet(config, Config::new);
    }

    public void writeConfig(Config config) {
        String json = converter.objectToJSON(config);
        String path = FileNames.getConfigPath();
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(json);
            writer.flush();
        } catch (Exception e) {
            MCTimer.LOGGER.error(e.getMessage());
        }
    }
}
