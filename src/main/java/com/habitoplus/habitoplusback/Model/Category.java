package com.habitoplus.habitoplusback.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer category_id;
    @NotBlank
    @Size(min = 1, max = 50)
    @JsonProperty("name")
    private String category_name;
    @NotBlank
    @JsonProperty("description")
    private String description;
    @JsonManagedReference
    @OneToMany(mappedBy = "category_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Habit> habits;

    public Category() {

    }

    public Category(Integer category_id, String name, String description) {
        this.category_id = category_id;
        this.category_name = name;
        this.description = description;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return category_id + " :: " + category_name + " :: " + description;
    }
}
