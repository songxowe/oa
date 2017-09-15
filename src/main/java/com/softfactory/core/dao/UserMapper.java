package com.softfactory.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.softfactory.pojo.User;

@Repository("userMapper")
public interface UserMapper {

  @Select("SELECT ID,USERNAME,USER_TRUE_NAME as userTrueName,PASSWORD,STATUS,PHOTO_PATH as photoPath,MANAGER_ID as managerId FROM SYS_USERS WHERE USERNAME=#{username} AND PASSWORD=#{password} AND STATUS='1'")
  User login(@Param("username") String username, @Param("password") String password);

  @Update("UPDATE SYS_USERS SET PASSWORD=#{password} WHERE ID=#{id}")
  int changePassword(@Param("id") Integer id, @Param("password") String password);

  // ** CRUD *********************************************************
  @Insert("insert into SYS_USERS(USERNAME,USER_TRUE_NAME,PASSWORD,STATUS,PHOTO_PATH,MANAGER_ID) values(#{username},#{userTrueName},#{password},#{status},#{photoPath},#{managerId})")
  //@SelectKey(statement = "select SEQ_SYS_USERS.nextval from dual", keyProperty = "id", resultType = int.class, before = true)
  int add(User user);

  @Update("update SYS_USERS set USERNAME=#{username},USER_TRUE_NAME=#{userTrueName},PASSWORD=#{password},STATUS=#{status},PHOTO_PATH=#{photoPath},MANAGER_ID=#{managerId} where ID=#{id}")
  int modify(User user);

  @Delete("delete from SYS_USERS where ID=#{id}")
  int remove(Integer id);

  @Select("SELECT ID,USERNAME,USER_TRUE_NAME as userTrueName,PASSWORD,STATUS,PHOTO_PATH as photoPath,MANAGER_ID as managerId FROM SYS_USERS WHERE ID=#{id}")
  User findById(Integer id);

  @Select("select u.id,u.user_true_name as userTrueName from sys_users u,sys_roles r,sys_user_role ur where u.id=ur.user_id and ur.role_id=r.id and r.name='部门领导'")
  List<User> findManager();

  List<User> findPager(@Param("pageno") Integer pageno, @Param("pagesize") Integer pagesize, @Param("sort") String sort,
      @Param("order") String order, @Param("username") String username, @Param("status") String status);

  long findPagerTotal(@Param("username") String username, @Param("status") String status);

  @Delete("delete from SYS_USER_ROLE where USER_ID=#{userId} and ROLE_ID=#{roleId}")
  void removeUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

  @Insert("insert into SYS_USER_ROLE(USER_ID,ROLE_ID) values(#{userId},#{roleId})")
  void addUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

  //查询所有用户id
  @Select("select id from sys_users")
  List<Integer> findAllId();

  //查询所有用户
  @Select("select id,user_true_name userTrueName from sys_users")
  List<User> findAllUser();

  @Select("SELECT ID,USERNAME,USER_TRUE_NAME as userTrueName,PASSWORD,STATUS,PHOTO_PATH as photoPath,MANAGER_ID as managerId FROM SYS_USERS ")
  List<User> findList();

  /*
   * 按真实姓名查id
   */
  @Select("select id from sys_users where user_true_name=#{userTrueName}")
  int findIdByTrueName(@Param("userTrueName") String userTrueName);
}
