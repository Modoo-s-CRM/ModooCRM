package com.modoocrm.modoocrm.domain.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//Auditing을 적용하기 위해서 @EntityListener 어노테이션 추가
@EntityListeners(value = {AuditingEntityListener.class})
//공통 매핑 정보가 필요할 때 사용하는 어노테이션. 부모 클래스를 상속받는 자식 클래스에
//매핑 정보만 제공
@MappedSuperclass
@Getter
public abstract class BaseModel {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createTime;

    @LastModifiedDate
    private LocalDateTime updateTime;
}
