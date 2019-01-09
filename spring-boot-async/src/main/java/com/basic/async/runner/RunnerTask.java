package com.basic.async.runner;

import com.basic.async.entity.User;
import com.basic.async.service.GitHubLookupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @Description 请求 CommandLineRunner : 启动时执行run方法
 * @Author zcc
 * @Date 19/01/08
 */
@Slf4j
@Component
public class RunnerTask implements CommandLineRunner {

    private final GitHubLookupService gitHubLookupService;

    public RunnerTask(GitHubLookupService gitHubLookupService) {
        this.gitHubLookupService = gitHubLookupService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Start the clock
        long start = System.currentTimeMillis();
        Future<User> page1 = gitHubLookupService.findUser("zcDecay");
        Future<User> page2 = gitHubLookupService.findUser("zcDecay");
        Future<User> page3 = gitHubLookupService.findUser("zcDecay");

        // Wait until they are all done
        while (!(page1.isDone() && page2.isDone() && page3.isDone())) {
            //10-millisecond pause between each check
            Thread.sleep(10);
        }
        // Print results, including elapsed time
        log.info("Elapsed time: " + (System.currentTimeMillis() - start));
        // log.info("--> " + page1.get());
        // log.info("--> " + page2.get());
        // log.info("--> " + page3.get());
    }
}
