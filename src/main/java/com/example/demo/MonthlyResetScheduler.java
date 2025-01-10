package com.example.demo;

import com.example.demo.Users.UsersService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MonthlyResetScheduler {

    private final UsersService usersService;

    public MonthlyResetScheduler(UsersService usersService) {
        this.usersService = usersService;
    }

    @Scheduled(cron = "0 0 0 1 * ?") // Execută la ora 00:00 în prima zi a fiecărei luni
    public void resetMonthlyLimits() {
        System.out.println("Running monthly reset job...");
        usersService.resetMonthlyLimits();
        System.out.println("Monthly limits reset completed.");
    }

}
