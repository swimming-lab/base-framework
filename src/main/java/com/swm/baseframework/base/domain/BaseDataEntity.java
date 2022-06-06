package com.swm.baseframework.base.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public abstract class BaseDataEntity extends BaseTimeEntity {
    @CreatedBy
    @Column(name = "reg_id", length = 50, updatable = false)
    private String registerId;

    @LastModifiedBy
    @Column(name = "upd_id", length = 50)
    private String updateId;
}
