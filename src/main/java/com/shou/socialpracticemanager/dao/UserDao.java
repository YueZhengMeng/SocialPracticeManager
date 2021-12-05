package com.shou.socialpracticemanager.dao;

import com.shou.socialpracticemanager.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {
    @Select("select * from user")
    List<User> selectAllUser();

    @Select("select * from user where userid=#{userid}")
    User selectUserById(String userid);

    @Select("select * from user where username=#{username}")
    User selectUserByName(String username);
}
