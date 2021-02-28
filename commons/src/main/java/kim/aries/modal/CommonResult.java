package kim.aries.modal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author aries
 * @Data 2021-02-28
 */
public class CommonResult<T> implements Serializable {
    String status;
    String msg;
    T data;

    public CommonResult() {
    }

    public CommonResult(String status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
