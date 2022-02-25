package com.payMyBuddy.Models;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractGlobalClass {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "create_at" ,nullable = false ,updatable = false)
	private Instant createDate;
	
	@LastModifiedDate
	@Column(name = "update_at")
	private Instant updateDate;
	
	@PrePersist
    void createdAt() {
        this.createDate = this.updateDate = new Date().toInstant();
    }

    @PreUpdate
    void updatedAt() {
        this.updateDate = new Date().toInstant();
    }

}
