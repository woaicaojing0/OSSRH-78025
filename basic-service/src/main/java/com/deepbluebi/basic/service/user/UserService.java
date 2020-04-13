package com.deepbluebi.basic.service.user;




import com.deepbluebi.basic.bean.vo.user.AipSaasUserResponseVO;


/**
 * @author cj
 */
public interface UserService {


    /**
     * 根据登陆人的信息获取用户信息
     *
     * @param userName
     * @return
     */
    AipSaasUserResponseVO findByLoginName(String userName);

}
