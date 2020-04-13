package com.deepbluebi.basic.bean.base;


import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: BaseEntity
 * @Description:TODO(实体类分为DO类和VO类，DO是跟数据库相关的实体类，VO是从页面过来的实体类 )
 * @author: wangshuai
 * @Embeddable 注解用于集成属性字段到类中
 * @date: 2017年7月14日 上午11:07:06
 * @Copyright: 2017 www.deepbluebi.com Inc. All rights reserved.
 */
public class BaseBeanDO implements Serializable {

    private static final long serialVersionUID = -4269104188475372478L;


    @ApiModelProperty("创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty("上次修改时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifyTime;
    @ApiModelProperty("创建用户")
    private String createUser;
    @ApiModelProperty("上次修改用户")
    private String lastModifyUser;
    @ApiModelProperty("所属用户saasCode")
    private String aipSaasCode;


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getLastModifyUser() {
        return lastModifyUser;
    }

    public void setLastModifyUser(String lastModifyUser) {
        this.lastModifyUser = lastModifyUser;
    }

    public String getAipSaasCode() {
        return aipSaasCode;
    }

    public void setAipSaasCode(String aipSaasCode) {
        this.aipSaasCode = aipSaasCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
