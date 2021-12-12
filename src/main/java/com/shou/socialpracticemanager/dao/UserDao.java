package com.shou.socialpracticemanager.dao;

import com.shou.socialpracticemanager.po.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {
    @Select("select * from user")
    List<User> selectAllUser();

    @Select("select * from user where userID=#{userID}")
    User selectUserByID(String userID);

    @Select("select * from user where username=#{username}")
    User selectUserByName(String username);

    @Insert("insert into user(username,password) values(#{username},#{password})")
    void addUser(User user);

    @Update("update user set username = #{username},password = #{password},role = #{role} where userID = #{userID}")
    void updateUser(User user);

    @Delete("delete from user where userID = #{userID}")
    void deleteUser(int userID);
}
