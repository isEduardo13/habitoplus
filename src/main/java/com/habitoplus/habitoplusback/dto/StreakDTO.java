package com.habitoplus.habitoplusback.dto;

import java.util.Date;

public class StreakDto {
    private Date startDate;
    private int consecutiveDays;

    public StreakDto(Date startDate, int consecutiveDays) {
        this.startDate = startDate;
        this.consecutiveDays = consecutiveDays;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getConsecutiveDays() {
        return consecutiveDays;
    }
    
}
