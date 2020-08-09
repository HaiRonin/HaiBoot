package com.whl.junit.molde;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class Result implements Serializable {
    private static final long serialVersionUID = -1802122468331526708L;

    private int status = -1;
    private String message = "待处理";
    private Map<String, Object> data = new HashMap<String, Object>();

    public Result(){}

    public Result(int status, String message){
        this.status = status;
        this.message = message;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void putData(String key, Object value) {
        data.put(key, value);
    }

    public void removeData(String key) {
        data.remove(key);
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
