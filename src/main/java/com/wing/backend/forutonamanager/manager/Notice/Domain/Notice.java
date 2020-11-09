package com.wing.backend.forutonamanager.manager.Notice.Domain;

import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Notice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("0")
    Integer idx;
    String title;
    String content;
    Boolean openFlag;

    @JoinColumn(name = "writerUid")
    @ManyToOne(fetch = FetchType.LAZY)
    MUserInfo writerUid;

    LocalDateTime modifyDate;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setOpenFlag(Boolean openFlag) {
        this.openFlag = openFlag;
    }

    public void setWriterUid(MUserInfo writerUid) {
        this.writerUid = writerUid;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }
}
