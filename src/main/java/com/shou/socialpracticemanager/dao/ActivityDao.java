package com.shou.socialpracticemanager.dao;

import com.shou.socialpracticemanager.po.Activity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ActivityDao {
    @Select("select * from activity")
    List<Activity> selectAllActivity();

    @Select("select * from activity where activityID=#{activityID}")
    Activity selectActivityByID(int activityID);

    @Select("select * from activity where activityName=#{activityName}")
    Activity selectActivityByName(String activityName);

    @Select("select * from activity where practiceID=#{practiceID}")
    List<Activity> selectActivityByPracticeID(int practiceID);

    @Insert("insert into activity(activityName,practiceID,startTime) values(#{activityName},#{practiceID},#{startTime})")
    int addActivity(Activity activity);

    @Update("update activity set endTime = #{endTime},state = 1 where activityID = #{activityID}")
    int endActivity(Activity activity);

    @Update("update activity set activityName = #{activityName} where activityID = #{activityID}")
    int updateActivity(Activity activity);

    @Delete("delete from activity where activityID = #{activityID}")
    int deleteActivity(int activityID);
}
