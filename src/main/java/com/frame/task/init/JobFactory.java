package com.frame.task.init;

import com.frame.task.abs.JavaTask;
import com.frame.task.dto.TaskDTO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;


/**
 * @Package: com.frame.task.init
 * @ClassName: Job
 * @Description: 任务工厂执行通过读取页面类型
 * @Author: mac-pro
 * @CreateDate: 2018/9/11 上午11:50
 * @Version: 1.0
 */
@Slf4j
@NoArgsConstructor
public class JobFactory implements Job {
    @Override
    public void execute(JobExecutionContext ctx) {
        JobDataMap dataMap = ctx.getJobDetail().getJobDataMap();
        TaskDTO dto = (TaskDTO) dataMap.get("taskDto");
        execute(dto);
    }

    /**
     * 执行
     */
    private void execute(TaskDTO dto) {
        switch (dto.jobType) {
            case 1:
                executeJava(dto);
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

    private void executeJava(TaskDTO dto) {
        String clazz = dto.clazz;
        if (clazz.length() <=0) {
            return;
        }
        try {
            JavaTask task = (JavaTask) Class.forName(clazz).newInstance();
            long statTime = System.currentTimeMillis();
            task.run();
            long endTime = System.currentTimeMillis();
            log.info("{} 耗时时长 : {} ms", clazz ,(endTime - statTime));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
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
