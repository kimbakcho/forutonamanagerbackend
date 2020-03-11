package com.management.forutona.FcubeQuestPage;

import lombok.Data;

@Data
public class FcubequestpageviewSearchdto {
    int offset;
    int limit;
    String sortBy;
    String sortDesc;
    String searchtype;
    String searchtext;
    String cubeType;
}
