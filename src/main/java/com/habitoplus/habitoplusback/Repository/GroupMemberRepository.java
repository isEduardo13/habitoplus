package com.habitoplus.habitoplusback.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;

import com.habitoplus.habitoplusback.Model.GroupMember;
import com.habitoplus.habitoplusback.Model.GroupMemberPK;
import com.habitoplus.habitoplusback.enums.Role;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, GroupMemberPK> {

    boolean existsByGroupIdGroupAndProfileIdProfileAndRole(Integer idGroup, Integer idProfile, Role role);

    GroupMember findFirstByGroupIdGroupOrderByUnionDateAsc(Integer idGroup);

    @Query("SELECT gm FROM GroupMember gm WHERE gm.group.idGroup = :idGroup AND gm.role <> 'ADMIN' ORDER BY gm.unionDate ASC")
    GroupMember findFirstNonAdminByGroupIdGroupOrderByUnionDateAsc(@Param("idGroup") Integer idGroup);

    @Query("SELECT gm FROM GroupMember gm WHERE gm.group.idGroup = :idGroup")
    Page<GroupMember> findMembersByIdGroup(@Param("idGroup") Integer idGroup, PageRequest pageRequest);

    @Query("SELECT CASE WHEN COUNT(gm) > 0 THEN TRUE ELSE FALSE END FROM GroupMember gm WHERE gm.group.idGroup = :idGroup")
    boolean existsGroupMembersByGroupId(@Param("idGroup") Integer idGroup);
}