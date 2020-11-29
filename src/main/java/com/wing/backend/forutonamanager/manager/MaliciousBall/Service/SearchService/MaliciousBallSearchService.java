package com.wing.backend.forutonamanager.manager.MaliciousBall.Service.SearchService;

import com.wing.backend.forutonamanager.forutona.FBall.Dto.FBallSimpleResDto;
import com.wing.backend.forutonamanager.forutona.FBall.Service.FBallService;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Domain.MaliciousBall;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Dto.MaliciousBallResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class MaliciousBallSearchService {

    final FBallService fBallService;

    public abstract Page<MaliciousBallResDto> search(Pageable pageable);

    protected Page<MaliciousBallResDto> makePageMaliciousBallResDto(Page<MaliciousBall> maliciousBallPage){
        List<String> ballCollect = maliciousBallPage.getContent()
                .stream()
                .map(x -> x.getBallUuid())
                .collect(Collectors.toList());

        List<FBallSimpleResDto> ballUuidsToFBallSimpleData =
                fBallService.getBallUuidsToFBallSimpleData(ballCollect);

        Page<MaliciousBallResDto> resultPage = maliciousBallPage
                .map(x -> {
                    MaliciousBallResDto resDto = MaliciousBallResDto.fromMaliciousBall(x);

                    Optional<FBallSimpleResDto> OpBallSimpleResDto = ballUuidsToFBallSimpleData.stream()
                            .filter(p -> p.getBallUuid().equals(x.getBallUuid()))
                            .findFirst();

                    if (OpBallSimpleResDto.isPresent()) {
                        FBallSimpleResDto fBallSimpleResDto = OpBallSimpleResDto.get();
                        resDto.setBallName(fBallSimpleResDto.getBallName());
                        resDto.setMakerNickName(fBallSimpleResDto.getUid().getNickName());
                        resDto.setBallMakeTime(fBallSimpleResDto.getMakeTime());
                        resDto.setBallHits(fBallSimpleResDto.getBallHits());
                    } else {
                        resDto.setBallName("don't find");
                        resDto.setMakerNickName("don't find");
                    }

                    return resDto;
                });

        return resultPage;
    }
}
