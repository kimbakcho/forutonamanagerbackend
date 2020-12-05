package com.wing.backend.forutonamanager.forutona.FUserInfo.Domain;

import lombok.Data;

@Data
public class LevelStopDay {
    Integer count;
    Integer stopDay;

    public LevelStopDay(Integer count, Integer stopDay) {
        this.count = count;
        this.stopDay = stopDay;
    }
}
