package com.management.forutona.FcubeUserinfo;

import lombok.Data;

@Data
public class FcubeUserSearchdto {
    int offset;
    int limit;
    String sortBy;
    String sortDesc;
    String searchtype;
    String searchtext;
}
