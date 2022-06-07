package com.lin.gamestore.enums;

public enum ProductCategoryStateEnum {
    SUCCESS(0, "操作成功"), FAILED(-1, "操作失败");
    private int state;
    private String stateInfo;


    ProductCategoryStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static ProductCategoryStateEnum stateOf(int index) {
        for (ProductCategoryStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
