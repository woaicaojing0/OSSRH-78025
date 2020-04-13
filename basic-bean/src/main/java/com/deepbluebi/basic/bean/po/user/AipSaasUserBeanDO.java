package com.deepbluebi.basic.bean.po.user;

import com.deepbluebi.basic.bean.base.BaseBeanDO;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 帐号相关信息实体类
 *
 * @author cj
 */
public class AipSaasUserBeanDO extends BaseBeanDO {
    /**
     *
     */
    private Integer id;

    /**
     * 预留
     */
    private String name;

    /**
     * 登陆密码
     */
    private String password;

    /**
     *
     */
    private String phone;

    /**
     * 预留，暂无
     */
    private String userId;

    /**
     * 预留
     */
    private String state;

    /**
     * 是否启用0 启用；1禁用
     */
    private Integer enable = 0;
    /**
     * 帐号类型：0是主账号，1是子账号。默认是主账号
     */
    private Integer accountType = 0;
    /**
     * 子账号才有的字段，所属父帐号id
     */
    private String parentAccountSaasCode;
    /**
     * 备注信息
     */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getParentAccountSaasCode() {
        return parentAccountSaasCode;
    }

    public void setParentAccountSaasCode(String parentAccountSaasCode) {
        this.parentAccountSaasCode = parentAccountSaasCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}