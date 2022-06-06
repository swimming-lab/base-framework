package com.swm.baseframework.base.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {
    @CreatedDate
    @Column(name = "reg_dtm", nullable = false, updatable = false)
    private LocalDateTime registerAt;

    @LastModifiedDate
    @Column(name = "upd_dtm", nullable = false)
    private LocalDateTime updateAt;
}