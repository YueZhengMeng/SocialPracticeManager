package com.shou.socialpracticemanager.dao;

import com.shou.socialpracticemanager.po.GroupParticipation;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GroupParticipationDao {
    @Select("select * from groupParticipation")
    List<GroupParticipation> selectAllGroupParticipation();

    @Select("select * from groupParticipation where groupParticipationID=#{groupParticipationID}")
    GroupParticipation selectGroupParticipationByID(int groupParticipationID);

    @Select("select * from groupParticipation where groupID=#{groupID}")
    List<GroupParticipation> selectGroupParticipationByGroupID(int groupID);

    @Select("select * from groupParticipation where userID=#{userID}")
    List<GroupParticipation> selectGroupParticipationByUserID(int userID);

    @Insert("insert into groupParticipation(groupID,userID) values(#{groupID},#{userID})")
    int addGroupParticipation(GroupParticipation groupParticipation);

    @Delete("delete from groupParticipation where groupParticipationID = #{groupParticipationID}")
    int deleteGroupParticipation(int groupParticipationID);

    @Delete("delete from groupParticipation where groupID = #{groupID}")
    int deleteGroupParticipationByGroupID(int groupID);

    @Delete("delete from groupParticipation where groupID = #{groupID} and userID = #{userID}")
    int deleteGroupParticipationByGroupIDAndUserID(int groupID,int userID);
}
