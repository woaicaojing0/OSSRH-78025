package com.deepbluebi.basic.listener;

import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SpringEventListener {
	//只有关于用户的那部分事务没有被spring管理，最后发现在shiro在启动配置的时候Spring还没启动，因为是Shiro先启动的。
	//解决参考：https://blog.csdn.net/fengfeng1229/article/details/76640080
    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        
    	
    	ApplicationContext context = event.getApplicationContext();
        
    	DefaultWebSecurityManager manager = (DefaultWebSecurityManager) context.getBean("securityManager");
        
    	AuthorizingRealm realm = (AuthorizingRealm) context.getBean("userRealm");
        /**
         * 采用明文验证，不进行加密验证
         */
//    	HashedCredentialsMatcher hashedCredentialsMatcher = (HashedCredentialsMatcher) context.getBean("credentialsMatcher");
    	
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        
      //  hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
      //  hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        //storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
      //  hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        
        
//        realm.setCredentialsMatcher(hashedCredentialsMatcher);
        manager.setRealm(realm);
    }


}
