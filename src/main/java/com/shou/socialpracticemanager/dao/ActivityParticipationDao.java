package com.shou.socialpracticemanager.dao;


import com.shou.socialpracticemanager.po.ActivityParticipation;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ActivityParticipationDao {
    @Select("select * from activityParticipation")
    List<ActivityParticipation> selectAllActivityParticipation();

    @Select("select * from activityParticipation where activityParticipationID=#{activityParticipationID}")
    ActivityParticipation selectActivityParticipationByID(int activityParticipationID);

    @Select("select * from activityParticipation where activityID=#{activityID}")
    List<ActivityParticipation> selectActivityParticipationByActivityID(int activityID);

    @Select("select * from activityParticipation where groupID=#{groupID}")
    List<ActivityParticipation> selectActivityParticipationByGroupID(int groupID);

    @Insert("insert into activityParticipation(activityID,groupID) values(#{activityID},#{groupID})")
    int addActivityParticipation(ActivityParticipation ActivityParticipation);

    @Update("update activityParticipation set finishTime = #{finishTime},state = 0 where activityParticipation = #{activityParticipation}")
    int endActivityParticipation(int ActivityParticipationID);

    @Delete("delete from activityParticipation where activityParticipationID = #{activityParticipationID}")
    int deleteActivityParticipation(int ActivityParticipationID);

    @Delete("delete from activityParticipation where activityID = #{activityID}")
    int deleteActivityParticipationByGroupID(int activityID);

    @Delete("delete from activityParticipation where groupID = #{groupID}")
    int deleteActivityParticipationByPracticeID(int groupID);

    @Select("select * from activityParticipation where activityID=#{activityID} and groupID=#{groupID}")
    ActivityParticipation deleteActivityParticipationByActivityIDAndGroupID(ActivityParticipation activityParticipation);
}
