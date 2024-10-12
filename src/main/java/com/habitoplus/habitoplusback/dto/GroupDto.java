package com.habitoplus.habitoplusback.dto;

import com.habitoplus.habitoplusback.enums.GroupType;

public class GroupDto{
    private Integer idGroup;
    private String name;
    private String description;
    private GroupType groupType;

    public GroupDto(Integer idGroup, String name, String description, GroupType groupType) {
        this.description = description;
        this.groupType = groupType;
        this.idGroup = idGroup;
        this.name = name;
    }

   

    // // public static class Builder{
    //     private Integer idGroup;
    //     private String name;
    //     private String description;
    //     private GroupType groupType;

    //     public Builder idGroup(Integer idGroup) {
    //         this.idGroup = idGroup;
    //         return this;
    //     }
        
    //     public Builder name(String name) {
    //         this.name = name;
    //     }
    //     public Builder description(String description) {
    //         this.description = description;
    //     }
    //     public Builder groupType(GroupType groupType) {
    //         this.groupType = groupType;
    //     }

    //     public GroupDto build() {
    //         return new GroupDto(this);
    //     }
        
    // }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

}
