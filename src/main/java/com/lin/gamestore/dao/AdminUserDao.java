package com.lin.gamestore.dao;

import com.lin.gamestore.entity.AdminUser;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AdminUserDao {
    /**
     * 列出所有管理列表
     * @return
     */
    List<AdminUser> queryAdminUserList();

    /**
     * 添加管理员信息
     * @param adminUser
     * @return
     */
    int insertAdminUser(AdminUser adminUser);

    /**
     * 根据authId，username，password修改密码
     * @param adminUserId
     * @param username
     * @param password
     * @param newPassword
     * @param updateTime
     * @return
     */
    int updateAdminUser(@Param("adminUserId")Long adminUserId, @Param("username")String username,
                       @Param("password")String password, @Param("newPassword")String newPassword,
                       @Param("updateTime")Date updateTime);

    /**
     * 根据账户与密码查询对应信息，登录用
     *
     * @param username
     * @param password
     * @return
     */
    AdminUser queryAdminByUserNameAndPwd(@Param("username") String username, @Param("password") String password);

    /**
     * 根据管理员id查询信息
     * @param adminUserId
     * @return
     */
    AdminUser queryAdminByAdminUserId(@Param("adminUserId") long adminUserId);
}
