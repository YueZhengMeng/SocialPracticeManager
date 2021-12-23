package com.shou.socialpracticemanager.dao;


import com.shou.socialpracticemanager.po.ActivityParticipation;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ActivityParticipationDao {
    @Select("select * from activityparticipation")
    List<ActivityParticipation> selectAllActivityParticipation();

    @Select("select * from activityparticipation where activityParticipationID=#{activityParticipationID}")
    ActivityParticipation selectActivityParticipationByID(int activityParticipationID);

    @Select("select * from activityparticipation where activityID=#{activityID}")
    List<ActivityParticipation> selectActivityParticipationByActivityID(int activityID);

    @Select("select * from activityparticipation where groupID=#{groupID}")
    List<ActivityParticipation> selectActivityParticipationByGroupID(int groupID);

    @Insert("insert into activityparticipation(activityID,groupID) values(#{activityID},#{groupID})")
    int addActivityParticipation(ActivityParticipation ActivityParticipation);

    @Update("update activityparticipation set finishTime = #{finishTime},state = 1 where activityParticipation = #{activityParticipation}")
    int finishActivityParticipation(ActivityParticipation activityParticipation);

    @Delete("delete from activityparticipation where activityParticipationID = #{activityParticipationID}")
    int deleteActivityParticipation(int ActivityParticipationID);

    @Delete("delete from activityparticipation where activityID = #{activityID}")
    int deleteActivityParticipationByGroupID(int activityID);

    @Delete("delete from activityparticipation where groupID = #{groupID}")
    int deleteActivityParticipationByPracticeID(int groupID);

    @Select("select * from activityparticipation where activityID=#{activityID} and groupID=#{groupID}")
    ActivityParticipation selectActivityParticipationByActivityIDAndGroupID(ActivityParticipation activityParticipation);
}
