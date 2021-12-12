package com.shou.socialpracticemanager.dao;

import com.shou.socialpracticemanager.po.Practice;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PracticeDao {
    @Select("select * from practice")
    List<Practice> selectAllPractice();

    @Select("select * from practice where practiceID=#{practiceID}")
    Practice selectPracticeByID(int practiceID);

    @Select("select * from practice where practiceName=#{practiceName} and startTime = #{startTime}")
    Practice selectPracticeByNameAndStartTime(Practice practice);

    @Insert("insert into practice(practiceName,startTime) values(#{practiceName},#{startTime})")
    int addPractice(Practice practice);

    @Update("update practice set endTime = #{endTime},state = 1 where practiceID = #{practiceID}")
    int endPractice(Practice practice);

    @Update("update practice set practiceName = #{practiceName} where practiceID = #{practiceID}")
    int updatePractice(Practice practice);

    @Delete("delete from practice where practiceID = #{practiceID}")
    int deletePractice(int practiceID);
}
