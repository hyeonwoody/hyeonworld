package com.toyproject.hyeonworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ThreadService {

    public ExecutorService executorService;
    private int poolSize = 10;

    public ThreadService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void setPoolSize (Integer poolSize){
        this.poolSize = poolSize;
    }

    public void submitTask(Runnable task){
        executorService.submit(task);
    }

}
