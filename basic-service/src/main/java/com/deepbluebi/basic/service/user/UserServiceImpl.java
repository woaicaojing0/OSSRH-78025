package com.deepbluebi.basic.service.user;


import com.deepbluebi.basic.bean.po.user.AipSaasUserBeanDO;
import com.deepbluebi.basic.bean.vo.user.AipSaasUserResponseVO;
import com.deepbluebi.basic.dao.aipSaasDao.AipSaasUserBeanDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cj on 2019/5/9
 *
 * @author cj
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AipSaasUserBeanDao userInfoDAO;

    @Override
    public AipSaasUserResponseVO findByLoginName(String userName) {
        AipSaasUserResponseVO aipSaasUserResponseVO = new AipSaasUserResponseVO();
        AipSaasUserBeanDO userInfoDO = userInfoDAO.findByLoginName(userName);
        if (userInfoDO == null) {
            return null;
        }
        BeanUtils.copyProperties(userInfoDO, aipSaasUserResponseVO);
        return aipSaasUserResponseVO;
    }

}
