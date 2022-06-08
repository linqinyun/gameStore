package com.lin.gamestore.service.impl;

import com.lin.gamestore.dao.AdminUserDao;
import com.lin.gamestore.dto.AdminUserExecution;
import com.lin.gamestore.entity.AdminUser;
import com.lin.gamestore.enums.AdminUserStateEnum;
import com.lin.gamestore.exceptions.AdminUserOperationException;
import com.lin.gamestore.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private AdminUserDao adminUserDao;

    @Override
    public List<AdminUser> getAdminUserList() {
        return adminUserDao.queryAdminUserList();
    }

    @Override
    public AdminUser getAdminUserByAdminUserId(Long adminUserId) {
        return adminUserDao.queryAdminByAdminUserId(adminUserId);
    }

    @Override
    public AdminUser getAdminUserByUserNameAndPwd(String username, String password) {
        return adminUserDao.queryAdminByUserNameAndPwd(username, password);
    }

    @Override
    @Transactional
    public AdminUserExecution bindAdminUser(AdminUser adminUser) throws AdminUserOperationException {
        // 空值判断，帐号密码，用户信息不能为空，否则直接返回错误
        if (adminUser == null || adminUser.getPassword() == null || adminUser.getUsername() == null) {
            return new AdminUserExecution(AdminUserStateEnum.NULL_AUTH_INFO);
        }
        try {
            int effectedNum = adminUserDao.insertAdminUser(adminUser);
            if (effectedNum <= 0) {
                throw new AdminUserOperationException("账号创建失败!");
            } else {
                return new AdminUserExecution(AdminUserStateEnum.SUCCESS, adminUser);
            }
        } catch (Exception e) {
            throw new AdminUserOperationException("insertAuthUser error: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public AdminUserExecution modifyAdminUser(Long adminUserId, String username, String password, String newPassword) throws AdminUserOperationException {
        if (adminUserId != null && username != null && password != null && newPassword != null && !password.equals(newPassword)) {
            try {
                int effectedNum = adminUserDao.updateAdminUser(adminUserId, username, password, newPassword, new Date());
                if (effectedNum <= 0) {
                    return new AdminUserExecution(AdminUserStateEnum.FAILED);
                }
                return new AdminUserExecution(AdminUserStateEnum.SUCCESS);
            } catch (Exception e) {
                throw new AdminUserOperationException("更新密码失败：" + e.toString());
            }
        } else {
            return new AdminUserExecution(AdminUserStateEnum.NULL_AUTH_INFO);
        }
    }
}
