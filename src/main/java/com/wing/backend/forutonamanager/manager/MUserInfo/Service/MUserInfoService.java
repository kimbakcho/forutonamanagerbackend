package com.wing.backend.forutonamanager.manager.MUserInfo.Service;

import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import com.wing.backend.forutonamanager.manager.MUserInfo.Dto.MUserInfoResDto;
import com.wing.backend.forutonamanager.manager.MUserInfo.Dto.SignUpReqDto;
import com.wing.backend.forutonamanager.manager.MUserInfo.Repository.MUserInfoDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

public interface MUserInfoService {
    void signUp(SignUpReqDto reqDto) throws Exception;

    MUserInfoResDto signInUser(String uid);
}

@Service
@RequiredArgsConstructor
class UserInfoServiceImpl implements MUserInfoService {

    final private MUserInfoDataRepository mUserInfoDataRepository;

    final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpReqDto reqDto) throws Exception {

        Optional<MUserInfo> userInfo = mUserInfoDataRepository.findById(reqDto.getUid());
        if(userInfo.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "ID 중복 에러", new Exception("ID 중복 에러"));
        }

        MUserInfo signUpUser = MUserInfo.builder()
                .uid(reqDto.getUid())
                .userUuid(UUID.randomUUID().toString())
                .password(passwordEncoder.encode(reqDto.getPassWord()))
                .userName(reqDto.getUserName())
                .groupName(reqDto.getGroupName())
                .hasRole("normal")
                .build();
        mUserInfoDataRepository.saveAndFlush(signUpUser);
    }

    @Override
    public MUserInfoResDto signInUser(String uid) {
        Optional<MUserInfo> byId = mUserInfoDataRepository.findById(uid);
        MUserInfo userInfo = byId.get();
        return MUserInfoResDto.fromUserInfoResDto(userInfo);
    }
}

