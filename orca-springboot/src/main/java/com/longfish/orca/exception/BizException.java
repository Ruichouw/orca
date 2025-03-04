package com.longfish.orca.exception;


import com.longfish.orca.enums.StatusCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BizException extends RuntimeException {

    private StatusCodeEnum statusCodeEnum;

    private final Integer code;

    private final String message;

    public BizException(StatusCodeEnum statusCodeEnum) {
        this.statusCodeEnum = statusCodeEnum;
        this.code = statusCodeEnum.getCode();
        this.message = statusCodeEnum.getDesc();
    }

    public BizException(String message) {
        this.code = StatusCodeEnum.FAIL.getCode();
        this.message = message;
    }

}
