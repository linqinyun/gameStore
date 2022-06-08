package com.lin.gamestore.service;

import com.lin.gamestore.dto.AdminUserExecution;
import com.lin.gamestore.entity.AdminUser;
import com.lin.gamestore.exceptions.AdminUserOperationException;

import java.util.List;

public interface AdminUserService {
    List<AdminUser> getAdminUserList();

    AdminUser getAdminUserByAdminUserId(Long adminUserId);

    AdminUser getAdminUserByUserNameAndPwd(String username, String password);

    AdminUserExecution bindAdminUser(AdminUser adminUser) throws AdminUserOperationException;

    AdminUserExecution modifyAdminUser(Long adminUserId, String username, String password, String newPassword) throws AdminUserOperationException;
}
