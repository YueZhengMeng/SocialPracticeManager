package com.shou.socialpracticemanager.dao;

import com.shou.socialpracticemanager.po.Group;
import com.shou.socialpracticemanager.po.GroupParticipation;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GroupParticipationDao {
    @Select("select * from groupparticipation")
    List<GroupParticipation> selectAllGroupParticipation();

    @Select("select * from groupparticipation where groupParticipationID=#{groupParticipationID}")
    GroupParticipation selectGroupParticipationByID(int groupParticipationID);

    @Select("select * from groupparticipation where groupID=#{groupID}")
    List<GroupParticipation> selectGroupParticipationByGroupID(int groupID);

    @Select("select * from groupparticipation where userID=#{userID}")
    List<GroupParticipation> selectGroupParticipationByUserID(int userID);

    @Insert("insert into groupparticipation(groupID,userID) values(#{groupID},#{userID})")
    int addGroupParticipation(GroupParticipation groupParticipation);

    @Delete("delete from groupparticipation where groupParticipationID = #{groupParticipationID}")
    int deleteGroupParticipation(int groupParticipationID);

    @Delete("delete from groupparticipation where groupID = #{groupID}")
    int deleteGroupParticipationByGroupID(int groupID);

    @Delete("delete from groupparticipation where groupID = #{groupID} and userID = #{userID}")
    int deleteGroupParticipationByGroupIDAndUserID(int groupID,int userID);

    @Select("select * from groupparticipation where activityID=#{activityID}")
    List<GroupParticipation> selectGroupParticipationByActivityID(int activityID);
}
