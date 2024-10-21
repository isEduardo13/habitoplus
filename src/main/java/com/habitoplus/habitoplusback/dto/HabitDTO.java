package com.habitoplus.habitoplusback.dto;


public class HabitDTO {
    private Integer categoryId;
    private String description;
    private Boolean status;
    private String priority;
    private Integer streakId;
    private Integer profileId;
    private String habitName;

    

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Integer getStreakId() {
        return streakId;
    }

    public void setStreakId(Integer streakId) {
        this.streakId = streakId;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }
}
