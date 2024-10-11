package com.habitoplus.habitoplusback.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHabit;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategory")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Category category;
    @NotBlank
    @JsonProperty("name")
    private String habit_name;
    @NotBlank
    private String description;
    private Boolean status;
    @NotNull
    @Size(min = 4, max = 5)
    private String priority;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "streak_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Streak streak;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProfile")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Profile profile;

    public Integer getIdHabit() {
        return idHabit;
    }

    public void setIdHabit(Integer idHabit) {
        this.idHabit = idHabit;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getHabit_name() {
        return habit_name;
    }

    public void setHabit_name(String habit_name) {
        this.habit_name = habit_name;
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

    public Streak getStreak() {
        return streak;
    }

    public void setStreak(Streak streak) {
        this.streak = streak;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "Habit [id_habit=" + idHabit + ", category_id=" + category + ", habit_name=" + habit_name
                + ", description=" + description + ", status=" + status + ", priority=" + priority + ", streak="
                + streak + "]";
    }

}
