package com.wing.backend.forutonamanager.forutona.UserInfoMaliciousHistory.Domain;

import com.wing.backend.forutonamanager.forutona.FUserInfo.Domain.FUserInfo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor()
public class UserInfoMaliciousHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idx;

    @JoinColumn(name = "uid")
    @ManyToOne
    FUserInfo uid;

    LocalDateTime maliciousDateTime;

    @Enumerated(EnumType.STRING)
    MaliciousType maliciousType;
}
