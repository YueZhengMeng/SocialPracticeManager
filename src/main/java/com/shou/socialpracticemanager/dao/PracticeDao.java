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

    @Select("select * from practice where practiceName=#{practiceName}")
    Practice selectPracticeByName(String practiceName);

    @Insert("insert into practice(practiceName,startTime) values(#{practiceName},#{startTime})")
    void addPractice(Practice practice);

    @Update("update practice set endTime = #{endTime},state = 1 where practiceID = #{practiceID}")
    void endPractice(Practice practice);

    @Update("update practice set practiceName = #{practiceName} where practiceID = #{practiceID}")
    void updatePractice(Practice practice);

    @Delete("delete from practice where practiceID = #{practiceID}")
    void deletePractice(int practiceID);
}
