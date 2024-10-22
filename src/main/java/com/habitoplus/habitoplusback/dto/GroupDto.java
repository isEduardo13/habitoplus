package com.habitoplus.habitoplusback.dto;

import com.habitoplus.habitoplusback.enums.GroupType;

public class GroupDTO{
    private Integer idGroup;
    private String name;
    private String description;
    private GroupType groupType;

    public GroupDTO() {
    }

    public GroupDTO(Integer idGroup, String name, String description, GroupType groupType) {
        this.description = description;
        this.groupType = groupType;
        this.idGroup = idGroup;
        this.name = name;
    }

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
