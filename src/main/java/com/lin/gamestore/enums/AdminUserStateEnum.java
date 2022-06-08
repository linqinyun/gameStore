package com.lin.gamestore.enums;

public enum AdminUserStateEnum {
    LOGINFAIL(-1,"用户或账号输入有误"), SUCCESS(0, "操作成功"), NULL_AUTH_INFO(-1006,
            "注册信息为空"), FAILED(500,"更新失败");
    private int state;
    private String stateInfo;

    AdminUserStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
    public static AdminUserStateEnum stateOf(int index){
        for (AdminUserStateEnum state:values()){
            if (state.getState() == index){
                return state;
            }
        }
        return null;
    }
}
