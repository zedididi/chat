package cn.edu.ncu.bootwebsocketmybatis.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 *  返回一个ServerEndpointExporter实例,用于扫描@ServerEndpoint注解
 */
@Configuration
public class EndPointServerConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
