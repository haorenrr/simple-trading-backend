package org.example.mylearn.common;

public enum ErrorCode {
    // ======粗粒度定义======,
    OK(0, "success"),
    ERROR(1, "Error"),
    DEFAULT(2, "Defualt"),

    // ====== 客户端错误（4xx 语义）======
    INVALID_PARAM(1001, "invalid parameter"),
    MISSING_PARAM(1002, "missing required parameter"),
    PARAM_TYPE_ERROR(1003, "parameter type error"),
    REQUEST_NOT_READABLE(1004, "request body not readable"),

    // ====== 认证 / 鉴权 ======
    UNAUTHORIZED(1101, "unauthorized"),
    FORBIDDEN(1102, "forbidden"),
    TOKEN_EXPIRED(1103, "token expired"),
    TOKEN_INVALID(1104, "token invalid"),

    // ====== 资源相关 ======
    RESOURCE_NOT_FOUND(1201, "resource not found"),
    RESOURCE_ALREADY_EXISTS(1202, "resource already exists"),

    // ====== 服务端错误（5xx 语义）======
    INTERNAL_ERROR(2001, "internal server error"),
    SERVICE_UNAVAILABLE(2002, "service unavailable"),
    DEPENDENCY_FAILURE(2003, "dependency service failure"),

    // ====== 业务相关 ======
    USER_NOT_FOUND(3001, "user not found"),
    USER_DISABLED(3002, "user is disabled"),
    USER_ALREADY_EXISTS(3003, "user already exists"),

    PASSWORD_ERROR(3004, "password incorrect"),
    PASSWORD_TOO_WEAK(3005, "password too weak"),

    USER_ROLE_INVALID(3006, "user role invalid"),

    ORDER_NOT_FOUND(3101, "order not found"),
    ORDER_ALREADY_EXISTS(3103, "order already exists"),

    ASSET_NOT_FOUND(3201, "asset not found"),
    ASSET_DISABLED(3202, "asset is disabled"),
    ASSET_ALREADY_EXISTS(3203, "asset already exists"),
    ASSET_NOT_ENOUGH(3204, "asset is not enough"),

    // ====== System control ===========
    FLOW_CONTROL(4001, "blocked by flow contrl"),

    // ====== 未知 ======
    UNKNOWN_ERROR(9999, "unknown error");


    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
