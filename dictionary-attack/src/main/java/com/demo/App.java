package com.demo;

import com.demo.thread.ThreadManager;
import com.demo.util.CliOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        CliOptions cliOptions = CliOptions.getInstance();
        cliOptions.init(args); // override from cli options

        StopWatch stopWatch = new StopWatch("App");
        stopWatch.start("ApplicationContext");
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ThreadManager threadManager = ac.getBean(ThreadManager.class);
        ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) ac.getBean(TaskExecutor.class);
        stopWatch.stop();
        stopWatch.start("ThreadManager");
        threadManager.start(taskExecutor);
        stopWatch.stop();
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(stopWatch.prettyPrint());
        }
    }
}
