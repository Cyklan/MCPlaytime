package de.cyklan.mctimer.util;

import java.io.Serializable;
import java.util.HashMap;

public class LevelTime implements Serializable {
    private HashMap<String, Long> levelTimes;

    public LevelTime() {
        this.levelTimes = new HashMap<String, Long>();
    }

    public long getTime(String world) {
        Long levelTime = this.levelTimes.get(world);
        if (levelTime == null) {
            this.setTime(world, (long)0);
            return 0;
        }

        return levelTime;
    }

    public void setTime(String world, long time) {
        this.levelTimes.put(world, time);
    }
}
