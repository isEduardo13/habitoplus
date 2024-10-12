package com.habitoplus.habitoplusback.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habitoplus.habitoplusback.Model.GroupMember;
import com.habitoplus.habitoplusback.Model.GroupMemberPK;
import com.habitoplus.habitoplusback.enums.Role;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, GroupMemberPK> {

    boolean existsByGroupIdGroupAndProfileIdProfileAndRole(Integer idGroup, Integer idProfile, Role role);

    GroupMember findFirstByGroupIdGroupOrderByUnionDateAsc(Integer idGroup);
}
