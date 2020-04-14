package com.chayedan.health.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chayedan666
 * @version 1.0
 * @className: Result
 * @description: 返回结果实体类（Json格式，底层使用的是阿里的fastJson转换,在配置文件spring-mvc里面），因为要在网络上传输，实现序列化接口
 * @date: 2020/4/14
 */
@Data
public class Result implements Serializable {
    private boolean flag;//执行结果，true为执行成功 false为执行失败
    private String message;//返回结果信息
    private Object data;//返回数据

    public Result() {
    }

    public Result(boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public Result(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }
}
