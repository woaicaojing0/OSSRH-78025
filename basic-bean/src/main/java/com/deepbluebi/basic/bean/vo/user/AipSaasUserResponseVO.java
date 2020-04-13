package com.deepbluebi.basic.bean.vo.user;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author cj
 */
public class AipSaasUserResponseVO {
    @ApiModelProperty("主键id")
    private Integer id;
    @ApiModelProperty("帐号")
    private String name;
    @ApiModelProperty("唯一标识")
    private String aipSaasCode;
    @ApiModelProperty("是否启用0 启用；1禁用")
    private Integer enable;
    @ApiModelProperty("0是主账号，1是子账号。默认是主账号")
    private Integer accountType = 0;
    @ApiModelProperty("子账号才有的字段，所属父帐号id")
    private String parentAccountSaasCode;
    @ApiModelProperty("备注信息")
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

    public String getAipSaasCode() {
        return aipSaasCode;
    }

    public void setAipSaasCode(String aipSaasCode) {
        this.aipSaasCode = aipSaasCode;
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
