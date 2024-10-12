package com.habitoplus.habitoplusback.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habitoplus.habitoplusback.model.GroupMember;
import com.habitoplus.habitoplusback.model.GroupMemberPK;
import com.habitoplus.habitoplusback.enums.Role;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, GroupMemberPK> {

    boolean existsByGroupIdGroupAndProfileIdProfileAndRole(Integer idGroup, Integer idProfile, Role role);

    GroupMember findFirstByGroupIdGroupOrderByUnionDateAsc(Integer idGroup);
}
