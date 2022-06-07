package com.lin.gamestore.dao;

import com.lin.gamestore.BaseTest;
import com.lin.gamestore.entity.AdminUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

//根据方法名称排序进行测试 A-B-C-D
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminUserDaoTest extends BaseTest {
    @Autowired
    private AdminUserDao adminUserDao;

    @Test
//    @Ignore //忽略
    public void testQueryAuthUser() {
        List<AdminUser> adminUserList = adminUserDao.queryAdminUserList();
        assertEquals(1, adminUserList.size());
    }

    @Test
    public void testQueryAuthByUserNameAndPwd() {
        String username = "test1";
        String password = "1234";
        AdminUser adminUser = adminUserDao.queryAdminByUserNameAndPwd(username, password);
        System.out.println(adminUser.getUsername());
    }

    @Test
    public void testQueryAuthByUserId() {
        AdminUser adminUser = adminUserDao.queryAdminByAdminUserId(1l);
        assertEquals("test1", adminUser.getUsername());
    }

    @Test
    public void testInsertAuthUser() {
        String username = "test1";
        String password = "1234";
        Date createTime = new Date();
        Date updateTime = new Date();
        AdminUser adminUser = new AdminUser();
        adminUser.setUsername(username);
        adminUser.setPassword(password);
        adminUser.setCreateTime(createTime);
        adminUser.setUpdateTime(updateTime);
        System.out.println(adminUser.getUsername() + "," + adminUser.getPassword() + "," + adminUser.getCreateTime()
                + "," + adminUser.getUpdateTime());
        int i = adminUserDao.insertAdminUser(adminUser);
        System.out.println(i);
    }

    @Test
    public void testUpdateAuthUser() {
        Long adminUserId = 1l;
        String username = "test1";
        String password = "1234";
        String newPassword = "123456";
        Date updateTime = new Date();
        System.out.println(updateTime);
        int i = adminUserDao.updateAdminUser(adminUserId, username, password, newPassword, updateTime);
        System.out.println(i);
    }
}
