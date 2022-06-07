package com.lin.gamestore.service;

import com.lin.gamestore.BaseTest;
import com.lin.gamestore.dto.AdminUserExecution;
import com.lin.gamestore.entity.AdminUser;
import com.lin.gamestore.enums.AdminUserStateEnum;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminUserServiceTest extends BaseTest {
    @Autowired
    private AdminUserService adminUserService;
    @Test
    public void testABindAdminUser(){
        String username = "admin4";
        String password = "1234";
        Date createTime = new Date();
        Date updateTime = new Date();
        AdminUser adminUser = new AdminUser();
        adminUser.setUsername(username);
        adminUser.setPassword(password);
        adminUser.setCreateTime(createTime);
        adminUser.setUpdateTime(updateTime);
        AdminUserExecution ae = adminUserService.bindAdminUser(adminUser);
        assertEquals(AdminUserStateEnum.SUCCESS.getState(),ae.getState());
    }
    @Test
    public void testBGetAdminUserList(){
        List<AdminUser> adminUserList = adminUserService.getAdminUserList();
        assertEquals(3, adminUserList.size());
    }
    @Test
    public void testCGetAdminUserByAuthId(){
        AdminUser adminUser = adminUserService.getAdminUserByAdminUserId(3l);
        assertEquals("admin4", adminUser.getUsername());
    }
    @Test
    public void testDGetAdminUserByUserNameAndPwd(){
        AdminUser adminUser = adminUserService.getAdminUserByUserNameAndPwd("admin4","1234");
        assertEquals("admin4", adminUser.getUsername());
    }

    @Test
    public void testEModifyAdminUser(){
        Long adminUserId = 3l;
        String username = "admin4";
        String password = "1234";
        String newPassword = "12344444";
        AdminUserExecution ae = adminUserService.modifyAdminUser(adminUserId,username,password,newPassword);
        assertEquals(AdminUserStateEnum.SUCCESS.getState(),ae.getState());
    }
}
