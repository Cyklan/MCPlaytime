package de.cyklan.mctimer.util;

import java.util.HashMap;

public class LevelTime {
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
