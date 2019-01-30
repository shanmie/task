package com.frame.task.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Package: com.frame.task.dto
 * @ClassName: TaskDTO
 * @Description:
 * @Author: mac-pro
 * @CreateDate: 2018/12/10 下午4:47
 * @Version: 1.0
 */
@Getter
@Setter
public class TaskDTO {
    private int id;
    private int triggerId;
    private int jobId;
    public int jobType;
    private int jobStatus;
    private int runStatus;
    private String jobName;
    private String jobDesc;
    private String group;
    private String cron;
    public String clazz;
    private String sqlText;
    private String scriptText;
    private long createTime;
    private long updateTime;
}
