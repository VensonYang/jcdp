package cn.org.jcdp.controller.vo;

/**
 * StatusCode
 *
 * @author venson
 * @version 20180703
 */
public enum StatusCode {
    SUCCESS(200),
    ERROR(-1);
    private int code;

    StatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
