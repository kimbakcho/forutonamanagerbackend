package com.management.forutona.NoticePage;

import lombok.Data;

@Data
public class NoticePageSearchDto {
    int offset;
    int limit;
    String sortBy;
    String sortDesc;
}
