package com.habitoplus.habitoplusback.Dto;

public class ProfileDto {
    private Integer idProfile;
    private String name;

    public ProfileDto(Integer idProfile, String name) {
        this.idProfile = idProfile;
        this.name = name;
    }

    public Integer getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Integer idProfile) {
        this.idProfile = idProfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
