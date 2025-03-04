package com.longfish.orca.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FilePathEnum {

    AVATAR("avatar/user/", "头像路径");

    private final String path;

    private final String desc;

}
