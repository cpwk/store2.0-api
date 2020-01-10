package com.maidao.edu.store.common.task;

import com.maidao.edu.store.common.util.L;
import com.sunnysuperman.commons.task.TaskEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:TaskService
 * 类描述:TODO
 **/
@Service
public class TaskService {
    @Value("${taskservice.max-pool-size}")
    private int maxPoolSize;
    @Value("${taskservice.threshold}")
    private int threshold;
    private TaskEngine engine;

    @PostConstruct
    public void init() {
        if (maxPoolSize <= 0) {
            throw new IllegalArgumentException("Bad maxPoolSize");
        }
        if (threshold <= 0) {
            throw new IllegalArgumentException("Bad threshold");
        }
        engine = new TaskEngine("taskservice", maxPoolSize);
    }

    public void addTask(Runnable task) {
        int taskNum = engine.getTasksNum();
        if (taskNum >= threshold) {
            L.warn("Too many tasks to execute: " + taskNum);
        }
        engine.addTask(task);
    }

    public void scheduleTask(Runnable task, long delay) {
        int taskNum = engine.getTasksNum();
        if (taskNum >= threshold) {
            L.warn("Too many tasks to execute: " + taskNum);
        }
        engine.scheduleTask(task, delay);
    }

    public void scheduleTask(Runnable task, long delay, long period) {
        engine.scheduleTask(task, delay, period);
    }
}
