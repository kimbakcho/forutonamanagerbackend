package com.wing.backend.forutonamanager.forutona.FUserInfo.Domain;

public enum MaliciousLevel {
    LEVEL1(new LevelStopDay(1,0)),
    LEVEL2(new LevelStopDay(2,1)),
    LEVEL3(new LevelStopDay(3,3)),
    LEVEL4(new LevelStopDay(4,9)),
    LEVEL5(new LevelStopDay(5,3650));

    public final LevelStopDay value;

    MaliciousLevel(LevelStopDay levelStopDay) {
        this.value = levelStopDay;
    }

}
