package com.habitoplus.habitoplusback.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "streaks")
public class Streak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStreak;

    @JsonManagedReference
    @OneToMany(mappedBy = "streak", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Habit> habits;

    @JsonBackReference(value = "profile-streak")
    @OneToOne
    @JoinColumn(name = "idProfile", referencedColumnName = "idProfile")
    private Profile profile;

    private Integer consecutiveDays = 0;
    private Integer completeHabits = 0;
    private Date startDate;
    private Date endDate;

}
