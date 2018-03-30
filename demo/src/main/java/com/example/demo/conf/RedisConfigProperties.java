package com.example.demo.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author licoy.cn
 * @version 2018/3/20
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfigProperties {

    private String host;
    private int port;
    private String password;

}
