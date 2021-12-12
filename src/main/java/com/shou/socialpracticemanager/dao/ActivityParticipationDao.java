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

    @Select("select * from activityParticipation where userID=#{userID}")
    List<ActivityParticipation> selectActivityParticipationByUserID(int userID);

    @Insert("insert into activityParticipation(activityID,userID) values(#{activityID},#{userID})")
    void addActivityParticipation(ActivityParticipation ActivityParticipation);

    @Update("update activityParticipation set finishTime = #{finishTime},state = 0 where activityParticipation = #{activityParticipation}")
    void endActivityParticipation(int ActivityParticipationID);

    @Delete("delete from activityParticipation where activityParticipationID = #{activityParticipationID}")
    void deleteActivityParticipation(int ActivityParticipationID);

    @Delete("delete from activityParticipation where activityID = #{activityID}")
    void deleteActivityParticipationByGroupID(int activityID);

    @Delete("delete from activityParticipation where userID = #{userID}")
    void deleteActivityParticipationByPracticeID(int userID);
}
