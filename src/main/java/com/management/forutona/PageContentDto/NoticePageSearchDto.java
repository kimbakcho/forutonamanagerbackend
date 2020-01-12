package com.management.forutona.PageContentDto;

import lombok.Data;

@Data
public class NoticePageSearchDto {
    int offset;
    int limit;
    String sortBy;
    String sortDesc;
}
