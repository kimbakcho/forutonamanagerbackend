package com.wing.backend.forutonamanager.manager.TermsConditions.Domain;

import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
public class TermsConditions {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idx;
    String termsName;
    String termsContent;
    LocalDateTime modifyDate;

    @JoinColumn(name = "modifyUser")
    @ManyToOne(fetch = FetchType.LAZY)
    MUserInfo modifyUser;

    public void setTermsName(String termsName) {
        this.termsName = termsName;
    }

    public void setTermsContent(String termsContent) {
        this.termsContent = termsContent;
    }

    public void setModifyUser(MUserInfo mUserInfo) {
        this.modifyUser = mUserInfo;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }
}
