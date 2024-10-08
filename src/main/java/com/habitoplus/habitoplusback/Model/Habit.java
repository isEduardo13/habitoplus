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
import jakarta.validation.constraints.Size;

@Entity
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_habit;
    @NotBlank(message = "la categoria es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Category category_id;
    @NotBlank
    @JsonProperty("name")
    private String habit_name;
    @NotBlank
    private String description;
    @NotBlank
    private Boolean status;
    @NotBlank
    @Size(min = 4, max = 5)
    private String priority;

    public Integer getId_habit() {
        return id_habit;
    }

    public void setId_habit(Integer id_habit) {
        this.id_habit = id_habit;
    }

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        this.category_id = category_id;
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

    @Override
    public String toString() {
        return id_habit + " :: " + category_id + " :: " + habit_name + " :: " + description
                + " :: " + status + " :: " + priority;
    }
}
