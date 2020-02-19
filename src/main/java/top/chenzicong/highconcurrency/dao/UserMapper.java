package top.chenzicong.highconcurrency.dao;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.chenzicong.highconcurrency.model.MiaoshaUser;
import top.chenzicong.highconcurrency.model.User;

import java.util.List;

@Repository
public interface UserMapper {
    @Select("select * from user ")
    List<User> findAll();

    @Select("select * from miaosha_user where id = #{id}")
    MiaoshaUser getById(@Param("id") Long id);
}
