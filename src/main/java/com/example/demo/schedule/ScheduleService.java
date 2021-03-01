package com.example.demo.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ScheduleService {
    @Scheduled(cron = "0 * * * * ?")
    public void execute(){
      log.info("Schedule executed");
    }
}
