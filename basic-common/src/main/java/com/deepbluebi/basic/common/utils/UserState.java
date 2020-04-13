package com.deepbluebi.basic.common.utils;

/**
 * Created by wk on 2019/5/9
 *
 * @author wk
 */
public enum UserState {

    COMMON("COMMON", "正常"),
    LOGOUT("LOGOUT", "注销");

    /**
     * code
     */
    private String code;
    /**
     * 描述
     */
    private String description;

    /**
     * 构造函数
     *
     * @param code
     * @param description
     */
    UserState(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据code查询用户状态
     *
     * @param code 用户状态code
     * @return 用户状态
     */
    public static UserState getUserStateByCode(String code) {
        if (code != null) {
            for (UserState userState : UserState.values()) {
                if (userState.getCode().equals(code)) {
                    return userState;
                }
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserState{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
