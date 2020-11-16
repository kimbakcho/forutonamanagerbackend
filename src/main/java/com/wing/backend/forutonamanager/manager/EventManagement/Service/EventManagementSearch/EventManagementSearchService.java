package com.wing.backend.forutonamanager.manager.EventManagement.Service.EventManagementSearch;

import com.wing.backend.forutonamanager.manager.EventManagement.Dto.EventManagementResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface  EventManagementSearchService {
    Page<EventManagementResDto> search(Pageable pageable);
}

