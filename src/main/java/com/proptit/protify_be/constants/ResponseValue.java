package com.proptit.protify_be.constants;

import org.springframework.http.HttpStatus;

public enum ResponseValue {
    //200x OK
    SUCCESS(HttpStatus.OK, "thành công"),

    //400x Bad request
    INVALID_FIELDS(HttpStatus.BAD_REQUEST, 4001, "trường không hợp lệ"),
    INVALID_OR_MISSING_REQUEST_PARAMS(HttpStatus.BAD_REQUEST, 4002, "requests param thiếu hoặc không hợp lệ"),
    INVALID_OR_MISSING_REQUEST_BODY(HttpStatus.BAD_REQUEST, 4003, "request body thiếu hoặc không hợp lệ"),
    FIELD_VALIDATION_ERROR(HttpStatus.BAD_REQUEST, 4004, "lỗi validation trường thông tin"),
    MISSING_CLIENT_ID_OR_SECRET(HttpStatus.NOT_FOUND, 4005, "thiếu client id hoặc secret"),

    //401x Unauthorized
    AUTHENTICATION_REQUIRED(HttpStatus.UNAUTHORIZED, 4011, "truy cập yêu cầu access token để xác thực"),
    WRONG_CLIENT_ID_OR_SECRET(HttpStatus.UNAUTHORIZED, 4012, "client id hoặc secret không đúng"),
    WRONG_PASSWORD(HttpStatus.UNAUTHORIZED, 4013, "sai mật khẩu"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, 4014, "token không hợp lệ"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, 4015, "token đã hết hạn"),

    //403x Forbidden
    ACCESS_DENIED(HttpStatus.FORBIDDEN, 4031, "không có quyền truy cập"),

    //404x Not found
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 4041, "không tìm thấy người dùng"),

    //409x Conflict
    EMAIL_HAS_BEEN_USED(HttpStatus.CONFLICT, 4091, "tên đăng nhập đã tồn tại"),

    //500x Internal server error
    UNEXPECTED_ERROR_OCCURRED(HttpStatus.INTERNAL_SERVER_ERROR, "lỗi hệ thống");

    private final HttpStatus httpStatus;
    private final String message;
    private final int specialCode;

    ResponseValue(HttpStatus httpStatus, int specialCode, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.specialCode = specialCode;
    }

    ResponseValue(HttpStatus httpStatus, String message) {
        this(httpStatus, httpStatus.value(), message);
    }

    ResponseValue(HttpStatus httpStatus) {
        this(httpStatus, httpStatus.value(), httpStatus.getReasonPhrase());
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public int getSpecialCode() {
        return specialCode;
    }

}