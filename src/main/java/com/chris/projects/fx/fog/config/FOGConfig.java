package com.chris.projects.fx.fog.config;

import com.chris.projects.fx.fog.fix.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import quickfix.Application;
import quickfix.MessageCracker;

@Configuration
public class FOGConfig {

    private static final Logger LOG = LoggerFactory.getLogger(FOGConfig.class);

    @Bean
    public FOGFixProperties fogFixProperties() {
        LOG.info("Initialize fogFixProperties");
        return new FOGFixProperties();
    }

    @Bean
    @DependsOn("fogFixProperties")
    public FixSender fogFixSender(FOGFixProperties fogFixProperties) {
        LOG.info("Initialize FixSender");
        return new FOGFixMessageSender(fogFixProperties.getSenderCompId(), fogFixProperties().getTargetCompId());
    }

    @Bean
    public FixApplication ftpFixApplication(MessageCracker ftpFixMessageReceiver) {
        LOG.info("Initialize FixApplication");
        return new FixApplication(ftpFixMessageReceiver);
    }

    @Bean
    public MessageCracker fopMessageCracker(){
        LOG.info("Initialize MessageCracker");
        return new FOGFixMessageReceiver();
    }

    @Bean
    public FixSessionConnector fixSessionConnector(FOGFixProperties fogFixProperties, Application fixApplication) {
        LOG.info("Creating fix session connector");
        FixSessionConnector fixSessionConnector = new FixSessionConnector(fogFixProperties().getConfigFile(), fogFixProperties().isAcceptor(), fixApplication, new FixConnectorFactoryImpl());
        fixSessionConnector.start();
        return fixSessionConnector;
    }


}
