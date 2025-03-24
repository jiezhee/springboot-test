package com.eris.springboottest.enums;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum CommonEnum {

    Enum1(1, "enum1"),
    Enum2(2, "enum2");

    private final int code;
    private final String name;

    CommonEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        CommonEnum enum1 = CommonEnum.valueOf("Enum3");
        log.info("test enum: {}", enum1);
    }
}
