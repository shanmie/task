package com.frame.task.init;

import cn.org.zax.mapper.BindMapper;
import cn.org.zax.support.DBSupport;
import com.frame.task.dto.TaskDTO;
import com.frame.task.utils.ConnCreate;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Package: com.frame.task.init
 * @ClassName: Trigger
 * @Description:
 * @Author: mac-pro
 * @CreateDate: 2018/9/11 上午11:52
 * @Version: 1.0
 */
@Slf4j
public class JobInit {
    Scheduler scheduler;

    public static void main(String[] args) throws SchedulerException {
        JobInit ji = new JobInit();
        ji.init();
    }

    private void init() throws SchedulerException {
        scheduler = StdSchedulerFactory.getDefaultScheduler();
        addEchoJob();
        scheduler.start();
    }

    private void addEchoJob() {
        List<TaskDTO> dtoList = ConnCreate.getDbRepository().selectAll(new DBSupport("select * from %task", "testboot"), new Mapper());
        for (TaskDTO dto : dtoList) {
            if (dto.getJobStatus() == 2) {//排除状态 2 为作废的任务
                continue;
            }
            event(dto);
        }
    }

    private void event(TaskDTO dto) {
        try {
            JobDetail job = JobBuilder.newJob(JobFactory.class).withIdentity("job_" + dto.getJobId(), "group")
                    .build();
            job.getJobDataMap().put("taskDto", dto);
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger_" + dto.getTriggerId(), "group").startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(dto.getCron())).build();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            log.error("{{}}", e);
        }
    }

    class Mapper implements BindMapper {
        @Override
        public Object mapper(ResultSet rs) throws SQLException {
            TaskDTO dto = new TaskDTO();
            dto.setTriggerId(rs.getInt("trigger_id"));
            dto.setJobId(rs.getInt("job_id"));
            dto.setJobType(rs.getInt("job_type"));
            dto.setJobStatus(rs.getInt("job_status"));
            dto.setRunStatus(rs.getInt("run_status"));
            dto.setJobName(rs.getString("job_name"));
            dto.setJobDesc(rs.getString("job_desc"));
            dto.setGroup(rs.getString("group"));
            dto.setCron(rs.getString("cron"));
            dto.setClazz(rs.getString("clazz"));
            dto.setSqlText(rs.getString("sqlText"));
            dto.setScriptText(rs.getString("scriptText"));
            dto.setCreateTime(rs.getDate("create_time").getTime());
            dto.setUpdateTime(rs.getDate("update_time").getTime());
            return dto;
        }
    }
}
