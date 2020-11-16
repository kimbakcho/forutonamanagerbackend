package com.wing.backend.forutonamanager.manager.EventManagement.Service.EventManagementSearch;

import com.wing.backend.forutonamanager.manager.EventManagement.Domain.EventManagement;
import com.wing.backend.forutonamanager.manager.EventManagement.Dto.EventManagementResDto;
import com.wing.backend.forutonamanager.manager.EventManagement.Repository.EventManagementDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EventManagementAllSearchService implements EventManagementSearchService{

    final EventManagementDataRepository eventManagementDataRepository;

    @Override
    public Page<EventManagementResDto> search(Pageable pageable) {
        Page<EventManagement> all = eventManagementDataRepository.findAll(pageable);
        return all.map(x->EventManagementResDto.fromEventManagement(x));
    }
}
