package org.example.mylearn.common;

public class Result<T> {
    boolean success;
    T data;
    ErrorCode errorCode;
    String message;

    public Result() {
    }

    public Result(boolean success, T data, ErrorCode errorCode, String message) {
        this.success = success;
        this.data = data;
        this.errorCode = errorCode;
        this.message = message;
    }

    public static <T> Result<T> fail(T data, ErrorCode code, String msg){
        return new Result<>(false, data, code, msg);
    }

    public static <T> Result<T> ok(T data){
        return new Result<>(true, data, ErrorCode.OK, "");
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}