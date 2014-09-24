package cn.superid.dao.Form;



/**
 * Created by usbuild on 2014/8/6.
 */
public class SimpleResponse {
    private int code;
    private Object data;

    public SimpleResponse() {
        this.code = 0;
    }

    public SimpleResponse(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public SimpleResponse setError(int code, String errorMessage) {
        this.code = code;
        this.data = errorMessage;
        return this;
    }
}
