package cn.org.jcdp.controller.vo;

import java.io.Serializable;

public class ResponseResult implements Serializable {

    private final static ThreadLocal<ResponseResult> RESPONSE_RESULT_THREAD_LOCAL = new ThreadLocal<>();

    private final static String DEAFULT_MESSAGE="success";
    private final static Serializable DEAFULT_DATA="";
    private static final long serialVersionUID = 2442419542480787984L;

    private int code;
    private String message;
    private Object data;

    private <T extends Serializable> ResponseResult(int code, T data, String message) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static void remove() {
        RESPONSE_RESULT_THREAD_LOCAL.remove();
    }

    public static <T extends Serializable> ResponseResult builder(int code, T data, String message) {
        ResponseResult responseResult = RESPONSE_RESULT_THREAD_LOCAL.get();
        if (responseResult == null) {
            responseResult = new ResponseResult(code, data, message);
            RESPONSE_RESULT_THREAD_LOCAL.set(responseResult);
        }
        return responseResult;
    }

    public static ResponseResult success() {
        return success(DEAFULT_DATA);
    }

    public static <T extends Serializable> ResponseResult success(T data) {
        return builder(StatusCode.SUCCESS.getCode(), data, DEAFULT_MESSAGE);
    }

    public static ResponseResult fail() {
        return fail(DEAFULT_MESSAGE);
    }

    public static ResponseResult fail(String message) {
        return builder(StatusCode.ERROR.getCode(), DEAFULT_DATA, message);
    }

    public int getCode() {
        return code;
    }

    public ResponseResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public <T extends Serializable> ResponseResult setData(T data) {
        this.data = data;
        return this;
    }
}