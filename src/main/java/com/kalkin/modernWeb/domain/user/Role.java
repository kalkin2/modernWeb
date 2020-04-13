package com.kalkin.modernWeb.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    /**
     * 항상 ROLE_로 시작 해야한다.
     */
    GUEST("ROLE_GUEST","게스트"),
    USER("ROLE_USER","일반사용자");

    private final String key;
    private final String title;
}
