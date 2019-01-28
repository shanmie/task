package com.frame.task.utils;

import cn.org.zax.DB;
import cn.org.zax.config.Config;
import cn.org.zax.repository.DBRepository;


/**
 * @Package: com.frame.task.utils
 * @ClassName: ConnCreate
 * @Description:
 * @Author: mac-pro
 * @CreateDate: 2018/12/10 下午4:50
 * @Version: 1.0
 */
public class ConnCreate {
    private DBRepository dbRepository;

    public DBRepository getDbRepository()  {
        Config config = new Config().setUrl("jdbc:mysql://127.0.0.1:3306/").setUsername("root").setPassword("root");
        return this.dbRepository = DB.create(config);
    }
}
