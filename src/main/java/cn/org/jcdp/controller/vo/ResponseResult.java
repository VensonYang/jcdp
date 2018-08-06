package cn.org.jcdp.controller.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * ResponseResult
 *
 * @author venson
 * @version 20180724
 */
public class ResponseResult extends ResponseEntity<Serializable> implements Serializable {

    private static final long serialVersionUID = 2442419542480787984L;
    private static final ThreadLocal<ResponseResult> RESPONSE_RESULT_THREAD_LOCAL = new ThreadLocal<>();

    private int code;
    private String message;

    public static ResponseResult fail(String message) {
        return fail(StatusCode.ERROR.getCode(), message);
    }

    public static ResponseResult fail(int code, String message) {
        return success(HttpStatus.OK).setMessage(message).setCode(code);
    }

    public static ResponseResult blank() {
        return success(HttpStatus.NO_CONTENT);
    }
    public static ResponseResult empty() {
        return success( HttpStatus.NOT_FOUND);
    }

    public static ResponseResult success(HttpStatus status) {
        return success(null, status);
    }

    public static ResponseResult success(Serializable data) {
        return success(data, HttpStatus.OK);
    }

    public static ResponseResult success(Serializable body, HttpStatus status) {
        return of(body,  null, status);
    }

    public static ResponseResult of(Serializable body, MultiValueMap<String, String> headers, HttpStatus status) {
        ResponseResult responseResult = RESPONSE_RESULT_THREAD_LOCAL.get();
        if (responseResult == null) {
            responseResult = new ResponseResult(body, headers, status);
            RESPONSE_RESULT_THREAD_LOCAL.set(responseResult);
        }
        return responseResult;
    }

    private ResponseResult(Serializable body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers, status);
        this.code = status.value();
        this.message = status.getReasonPhrase();
    }

    public static void remove() {
        RESPONSE_RESULT_THREAD_LOCAL.remove();
    }


    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public ResponseResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public ResponseResult setCode(int code) {
        this.code = code;
        return this;
    }

    @JsonIgnore
    @Override
    public HttpStatus getStatusCode() {
        return super.getStatusCode();
    }


    @JsonIgnore
    @Override
    public int getStatusCodeValue() {
        return super.getStatusCodeValue();
    }
}
