package com.deepbluebi.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 描述：
 *
 * @author caojing
 * @create 2019-07-29-13:50
 */

@Configuration
public class WebSocketConfig {

    // 是因为在tomcat下有自己的websocket支持包，不需要spring来管理。所以在tomcat下运行的话需要注释掉下面的代码
    // 如果需要打war包 且发布到tomcat，请注释下面的代码 否则本地调试请 取消注释
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
