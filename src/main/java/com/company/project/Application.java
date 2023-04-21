package com.company.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        // 监控应用的PID，启动时可指定PID路径：--spring.pid.file=/home/code/app.pid
        // 或者在 application.yml 添加文件路径，方便 kill，kill `cat /home/code/app.pid`
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }
}

