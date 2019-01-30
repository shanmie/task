package com.frame.task.init;

import com.frame.task.abs.JavaTask;
import com.frame.task.dto.TaskDTO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
                executeJava(dto.clazz);
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

    private void executeJava(String clazz) {
        if (StringUtils.isEmpty(clazz)) {
            return;
        }
        try {
            long statTime = System.currentTimeMillis();
            JavaTask task = (JavaTask) Class.forName(clazz).newInstance();
            log.info("run class is {{}}", clazz);
            task.run();
            long endTime = System.currentTimeMillis();
            log.info("耗时时长 : {{}} ms", (endTime - statTime));
        } catch (Exception e) {
            log.error("{{}}", e);
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
