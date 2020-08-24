package com.utstar.c1handler.config;

import com.utstar.c1handler.util.TarUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.reflect.Field;

@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
    private static Logger log= LoggerFactory.getLogger(ApplicationStartup.class);
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        Class<ConfigReader> configReaderClass = ConfigReader.class;
        ConfigReader bean = applicationContext.getBean(configReaderClass);
        String localFilePath = bean.getLocalFilePath();
        bean.setCmdPath(localFilePath + File.separator + "cmd_path");
        bean.setTransferCmdPath(localFilePath + File.separator + "transfer_cmd_path");
        bean.setNotifyPath(localFilePath + File.separator + "notify_path");
        bean.setTransferNotifyPath(localFilePath + File.separator + "transfer_notify_path");
        bean.setEpgTemplatePath(localFilePath + File.separator + "epg_template_path");
        createFolder(bean.getLocalFilePath());
        createFolder(bean.getCmdPath());
        createFolder(bean.getEpgFileUnzipPath());
        createFolder(bean.getEpgTemplatePath());
        createFolder(bean.getNotifyPath());
        createFolder(bean.getTransferCmdPath());
        createFolder(bean.getTransferNotifyPath());
    }

    private void createFolder(String folder) {
        if (folder != null) {
            File file = new File(folder);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }

    private void setValue(String name, Object object, Class clazz, String value) throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        field.set(object, value);
    }
}
