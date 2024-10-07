package com.habitoplus.habitoplusback.Model;

import java.io.Serializable;
import java.util.Objects;

public class GroupMemberPK implements Serializable{
    private Group group;
    private Profile profile;

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if (!(o instanceof GroupMemberPK groupMemberPK))
            return false;
        return group.getIdGroup() == groupMemberPK.group.getIdGroup() && profile.getIdProfile() == groupMemberPK.profile.getIdProfile();
    }

    @Override
    public int hashCode() {
        return  Objects.hash(group, profile);
    }

    public Group getGroup(){
        return group;
    }

     public Profile getProfile(){
        return profile;
     }
}
