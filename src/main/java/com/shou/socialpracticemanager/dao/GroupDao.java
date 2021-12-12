package com.shou.socialpracticemanager.dao;

import com.shou.socialpracticemanager.po.Group;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GroupDao {
    @Select("select * from group")
    List<Group> selectAllGroup();

    @Select("select * from group where groupID=#{groupID}")
    Group selectGroupByID(int groupID);

    @Select("select * from group where groupName=#{groupName}")
    Group selectGroupByName(String groupName);

    @Select("select * from group where practiceID=#{practiceID}")
    List<Group> selectGroupByPracticeID(int practiceID);

    @Insert("insert into group(groupName,practiceID) values(#{groupName},#{practiceID})")
    void addGroup(Group group);

    @Update("update Group set groupName = #{groupName} where groupID = #{groupID}")
    void updateGroup(Group group);

    @Delete("delete from group where groupID = #{groupID}")
    void deleteGroup(int groupID);
}
