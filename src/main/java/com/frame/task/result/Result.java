package com.frame.task.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Package: com.frame.task.result
 * @ClassName: Result
 * @Description:
 * @Author: mac-pro
 * @CreateDate: 2018/9/17 下午3:37
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum  Result{

    ok("ok", "执行成功"),
    failed("error", "执行失败"),
    ;
    private String code;
    private String msg;

}