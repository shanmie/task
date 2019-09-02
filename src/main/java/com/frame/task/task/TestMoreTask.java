package com.frame.task.task;

import com.frame.task.abs.JavaTask;
import com.frame.task.result.Result;
import lombok.extern.slf4j.Slf4j;

/**
 * @Package: com.frame.task.task
 * @ClassName: TestMoreTask
 * @Description:
 * @Author: mac-pro
 * @CreateDate: 2019/1/30 上午11:48
 * @Version: 1.0
 */
@Slf4j
public class TestMoreTask implements JavaTask {

    public static void main(String[] args) {
        TestFirstTask test = new TestFirstTask();
        test.run();
    }

    @Override
    public Result run() {
        //do something
        log.info(String.format("执行结果{%s},{%s}",Result.failed.getCode(),Result.failed.getMsg()));

        return Result.failed;
    }
}
