package com.cydeo.entity;

import com.cydeo.entity.common.UserPrinciple;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Component
public class BaseEntityListener extends AuditingEntityListener {

    Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

    @PrePersist
    private void onPrePersist(BaseEntity baseEntity){
        Authentication authentication1=SecurityContextHolder.getContext().getAuthentication();
      baseEntity.setInsertDateTime(LocalDateTime.now());
        baseEntity.setLastUpdateDateTime(LocalDateTime.now());

        if(authentication !=null && !authentication.getName().equals("anonymousUser")) {
            Object principal = authentication.getPrincipal();
        baseEntity.setInsertUserId(((UserPrinciple) principal).getId());
            baseEntity.setLastUpdateUserId(((UserPrinciple) principal).getId());
        }
    }

    @PreUpdate
    private void onPreUpdate(BaseEntity entity){
        Authentication authentication1=SecurityContextHolder.getContext().getAuthentication();
        entity.setLastUpdateDateTime(LocalDateTime.now());
        if(authentication !=null && !authentication.getName().equals("anonymousUser")) {
            Object principal = authentication.getPrincipal();
            entity.setLastUpdateUserId(((UserPrinciple) principal).getId());
        }
    }




}
