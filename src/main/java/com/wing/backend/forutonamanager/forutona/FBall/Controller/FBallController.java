package com.wing.backend.forutonamanager.forutona.FBall.Controller;

import com.wing.backend.forutonamanager.forutona.FBall.Domain.FBall;
import com.wing.backend.forutonamanager.forutona.FBall.Dto.FBallResDto;
import com.wing.backend.forutonamanager.forutona.FBall.Service.FBallService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/FBall")
public class FBallController {

    final FBallService fBallService;

    @GetMapping("/BallUuid")
    public FBallResDto getFBall(String ballUuid){
        return fBallService.getBall(ballUuid);
    }
}
