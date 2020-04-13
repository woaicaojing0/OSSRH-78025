package com.deepbluebi.basic.listener;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * 描述：smart-office中会议过期失效回调事件 redis 配置需要修改 notify-keyspace-events 的参数为 “Ex”
 *
 * @author caojing
 * @create 2019-03-14-12:19
 */
@Slf4j
@Component
public class KeyExpiredListener implements MessageListener {

    private Logger deviceLogger = LoggerFactory.getLogger("deviceLogger");


    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(message.getChannel(), StandardCharsets.UTF_8);
        //过期的key
        String key = new String(message.getBody(), StandardCharsets.UTF_8);
    }
}
