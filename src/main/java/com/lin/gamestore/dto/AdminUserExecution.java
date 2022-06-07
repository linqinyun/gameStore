package com.lin.gamestore.dto;

import com.lin.gamestore.entity.AdminUser;
import com.lin.gamestore.enums.AdminUserStateEnum;

import java.util.List;

public class AdminUserExecution {
    private int state;
    private String stateInfo;
    private int count;
    private AdminUser adminUser;
    private List<AdminUser> adminUserList;

    public AdminUserExecution() {
    }

    //失败的构造器
    public AdminUserExecution(AdminUserStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }
    //成功的构造器-1
    public AdminUserExecution(AdminUserStateEnum stateEnum, AdminUser adminUser) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.adminUser = adminUser;
    }

    //成功的构造器-2
    public AdminUserExecution(AdminUserStateEnum stateEnum, List<AdminUser> adminUserList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.adminUserList = adminUserList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public AdminUser getAuthUser() {
        return adminUser;
    }

    public void setAuthUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    public List<AdminUser> getAuthUserList() {
        return adminUserList;
    }

    public void setAuthUserList(List<AdminUser> adminUserList) {
        this.adminUserList = adminUserList;
    }
}
