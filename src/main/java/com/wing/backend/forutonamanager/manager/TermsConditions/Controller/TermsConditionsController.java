package com.wing.backend.forutonamanager.manager.TermsConditions.Controller;

import com.wing.backend.forutonamanager.manager.Security.CustomOAuth2User;
import com.wing.backend.forutonamanager.manager.TermsConditions.Dto.TermsConditionsResDto;
import com.wing.backend.forutonamanager.manager.TermsConditions.Dto.TermsConditionsSaveDto;
import com.wing.backend.forutonamanager.manager.TermsConditions.Dto.TermsConditionsUpdateDto;
import com.wing.backend.forutonamanager.manager.TermsConditions.Service.TermsConditionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/termsConditions")
@RequiredArgsConstructor
public class TermsConditionsController {

    final TermsConditionsService termsConditionsService;

    @GetMapping("/Terms")
    public TermsConditionsResDto getTermsConditions(@RequestParam Integer idx){
        return termsConditionsService.getTerms(idx);
    }

    @PostMapping("/Terms")
    public TermsConditionsResDto saveTermsConditions(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                                                     @RequestBody TermsConditionsSaveDto termsConditionsSaveDto){
        return termsConditionsService.saveTermsConditions(customOAuth2User,termsConditionsSaveDto);
    }

    @PutMapping("/Terms")
    public TermsConditionsResDto updateTermsConditions(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                                                       @RequestBody TermsConditionsUpdateDto termsConditionsUpdateDto){
        return termsConditionsService.updateTermsConditions(customOAuth2User,termsConditionsUpdateDto);
    }

    @DeleteMapping("/Terms")
    public Integer deleteTermsConditions(@RequestParam Integer idx){
        return termsConditionsService.deleteTermsConditions(idx);
    }

}
