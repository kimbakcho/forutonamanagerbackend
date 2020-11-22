package com.wing.backend.forutonamanager.manager.EventManagement.Service.EventManagementSearch;

import com.wing.backend.forutonamanager.manager.EventManagement.Domain.EventSearchType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventManagementSearchServiceFactory {

    final EventManagementAllSearchService eventManagementAllSearchService;
    final EventManagementBeforeSearchService eventManagementBeforeSearchService;
    final EventManagementEndSearchService eventManagementEndSearchService;
    final EventManagementPlaySearchService eventManagementPlaySearchService;

    public EventManagementSearchService getInstance(EventSearchType eventSearchType) throws Exception {
        switch (eventSearchType) {
            case ALL:
                return eventManagementAllSearchService;
            case BEFORE:
                return eventManagementBeforeSearchService;
            case PLAY:
                return eventManagementPlaySearchService ;
            case END:
                return eventManagementEndSearchService;
            default:
                throw new Exception("don't Support");
        }
    }
}
