package com.frame.task.init;

import com.frame.task.abs.JavaTask;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * @Package: com.frame.task.init
 * @ClassName: Job
 * @Description: 任务工厂执行通过读取页面类型
 * @Author: mac-pro
 * @CreateDate: 2018/9/11 上午11:50
 * @Version: 1.0
 */
@Slf4j
public class JobFactory implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        execute();
    }

    /**
     *
     */
    private void execute() {
        log.info("这里开始执行job");
        //这里类型应该从数据中读取
        int val = 1;
        switch (val) {
            case 1:
                executeJava();
                break;
            case 2:
                executeScript();
                break;
            case 3:
                executeSql();
                break;
            default:
                executeOther();
                break;
        }

    }

    private void executeJava() {
        try {
            log.info("java 定时任务");

            long statTime = System.currentTimeMillis();
            //这里从应该从数据读出所有全类名地址
            String clazz = "com.frame.task.task.TestFirstTask";
            JavaTask task = (JavaTask) Class.forName(clazz).newInstance();
            log.info(String.format("run from class is {%S}",clazz));
            task.run();
            long endTime = System.currentTimeMillis();

            log.info(String.format("耗时时长 : %s ms",(endTime - statTime)));
        }catch (Exception e){
            log.error("{%s}",e);
        }
    }

    private void executeScript() {
        log.info("script 定时任务");
    }

    private void executeSql() {
        log.info("sql 定时任务");
    }

    private void executeOther() {
        log.info("other 定时任务");
    }
}
