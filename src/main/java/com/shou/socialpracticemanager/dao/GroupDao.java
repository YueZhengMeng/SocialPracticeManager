package com.shou.socialpracticemanager.dao;

import com.shou.socialpracticemanager.po.Group;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GroupDao {

    //group是mysql保留字段，sql语句中不能直接使用，要加socialpracticemanager.前缀

    @Select("select * from socialpracticemanager.group")
    List<Group> selectAllGroup();

    @Select("select * from socialpracticemanager.group where groupID=#{groupID}")
    Group selectGroupByID(int groupID);

    @Select("select * from socialpracticemanager.group where groupName=#{groupName}")
    Group selectGroupByName(String groupName);

    @Select("select * from socialpracticemanager.group where groupName=#{groupName} and practiceID=#{practiceID}")
    Group selectGroupByNameAndPracticeID(String groupName,int practiceID);

    @Select("select * from socialpracticemanager.group where practiceID=#{practiceID}")
    List<Group> selectGroupByPracticeID(int practiceID);

    @Select("select * from socialpracticemanager.group where userID = #{userID}")
    List<Group> selectGroupByUserID(int userID);

    @Select("select * from socialpracticemanager.group where practiceID=#{practiceID} and userID = #{userID}")
    Group selectGroupByPracticeIDAndUserID(int practiceID,int userID);

    @Insert("insert into socialpracticemanager.group(groupName,practiceID) values(#{groupName},#{practiceID})")
    int addGroup(Group group);

    @Update("update socialpracticemanager.group set groupName = #{groupName} where groupID = #{groupID}")
    int updateGroup(Group group);

    @Update("update socialpracticemanager.group set score = #{score} where groupID = #{groupID}")
    int updateGroupScore(Group group);

    @Delete("delete from socialpracticemanager.group where groupID = #{groupID}")
    int deleteGroup(int groupID);
}
