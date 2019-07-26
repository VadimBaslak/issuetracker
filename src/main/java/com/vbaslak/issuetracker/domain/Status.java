package com.vbaslak.issuetracker.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Status implements GrantedAuthority {
    CREATED, IN_PROGRESS, RESOLVED, CLOSED;

    @Override
    public String getAuthority() {
        return name();
    }
}

