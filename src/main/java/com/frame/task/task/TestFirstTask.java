package com.frame.task.task;

import com.frame.task.abs.JavaTask;
import com.frame.task.result.Result;
import lombok.extern.slf4j.Slf4j;

/**
 * @Package: com.frame.task.task
 * @ClassName: TestFirstTask
 * @Description:
 * @Author: mac-pro
 * @CreateDate: 2018/9/11 上午11:51
 * @Version: 1.0
 */
@Slf4j
public class TestFirstTask implements JavaTask {

    public static void main(String[] args) {
        TestFirstTask test = new TestFirstTask();
        test.run();

    }

    @Override
    public Result run() {
        //do something
        log.info(String.format("执行结果{%s},{%s}",Result.successfully.getCode(),Result.successfully.getMsg()));
        int a = 20 ,b =30 ;
        log.info(String.format("a*b = {%s}",a*b));
        return Result.successfully;
    }

}
