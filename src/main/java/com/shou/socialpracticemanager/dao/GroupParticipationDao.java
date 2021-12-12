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

    @Select("select * from groupParticipation where practiceID=#{practiceID}")
    List<GroupParticipation> selectGroupParticipationByPracticeID(int practiceID);

    @Insert("insert into groupParticipation(groupID,practiceID) values(#{groupID},#{practiceID})")
    void addGroupParticipation(GroupParticipation groupParticipation);

    @Delete("delete from groupParticipation where groupParticipationID = #{groupParticipationID}")
    void deleteGroupParticipation(int groupParticipationID);

    @Delete("delete from groupParticipation where groupID = #{groupID}")
    void deleteGroupParticipationByGroupID(int groupID);

    @Delete("delete from groupParticipation where practiceID = #{practiceID}")
    void deleteGroupParticipationByPracticeID(int practiceID);
}
