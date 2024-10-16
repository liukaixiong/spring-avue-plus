package com.liukx.spring.client.model;

/**
 * @author liukaixiong
 * @date 2024/10/15 - 15:09
 */
public class HttpResult {

    private boolean success;

    private String message;

    private Object data;

    public static HttpResult ok(Object data) {
        HttpResult result = new HttpResult();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static HttpResult ok() {
        return ok(null);
    }

    public static HttpResult error(String message) {
        HttpResult result = new HttpResult();
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
