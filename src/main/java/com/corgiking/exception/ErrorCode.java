package com.corgiking.exception;

public enum ErrorCode {
    
    UNKNOWN(0),
    SYSTEM_ERROR(1),
    PARAM_ERROR(2),
    INVALID_ACCESS_TOKEN(3),
    INSER_ERROR(4),
    UPDATE_ERROR(5),
    UNAUTHORIZED_ACCESS(6),//无权访问
    TOKEN_MISS(7),
    WORK_DEAL_ERROR(8),//找不到该工单号
    MD5_ENCRYPTION_ERROR(10), //MD5加密出错
    REQUEST_TYPE_ERROR(405),
    NOT_FOUND(404),
    BAD_REQUEST(500),    
    ACCOUNT_IDENTITY_MISSING(7002), 
    //token过期
    TOKEN_EXPIRED(7003),
    //该账号在其他地方登录
    TOKEN_CANCELED_BY_OTHERS(7004);
	
    private final int code;

    ErrorCode(final int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
