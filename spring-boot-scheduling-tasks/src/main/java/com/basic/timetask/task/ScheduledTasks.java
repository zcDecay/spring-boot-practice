package com.basic.timetask.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * @Description 定时调度任务
 * @Author zcc
 * @Date 19/01/08
 */
@Slf4j
@Component
public class ScheduledTasks {

    /**
     * 时间格式化
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    /**
     * @Description: 调度执行任务，设定定时时间
     *      fixedRate : 上一次开始执行时间点之后5秒再次执行
     *      fixedDelay: 上一次执行完毕时间点之后5秒再次执行
     *      initialDelay=1000, fixedRate=5000 : 第一次延迟1秒执行，之后按第一次执行之后5秒执行一次
     *      cron=" /5 " : 通过cron表达式定义规则
     * @param:  * @param
     * @return: void
     */
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(System.currentTimeMillis()));
    }

}
