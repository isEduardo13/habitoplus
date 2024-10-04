package com.habitoplus.habitoplusback.dto;

import java.util.Date;

public class StreakDTO {
    private Date startDate;
    private int consecutiveDays;

    public StreakDTO(Date startDate, int consecutiveDays) {
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
