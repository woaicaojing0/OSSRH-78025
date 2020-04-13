package com.deepbluebi.basic.dao.aipSaasDao;


import com.deepbluebi.basic.bean.po.user.AipSaasUserBeanDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author cj
 */
@Mapper
public interface AipSaasUserBeanDao {


    /**
     * 登陆根据用户的姓名查询信息(启用的)
     *
     * @param userName
     * @return
     */
    AipSaasUserBeanDO findByLoginName(@Param("userName") String userName);

}