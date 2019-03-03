package com.chris.projects.fx.fog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "fix")
public class FOGFixProperties {

    private boolean acceptor;
    private String SenderCompId;
    private String TargetCompId;
    private String configFile;



    public String getSenderCompId() {
        return SenderCompId;
    }

    public String getTargetCompId() {
        return TargetCompId;
    }

    public boolean isAcceptor() {
        return acceptor;
    }

    public void setAcceptor(boolean acceptor) {
        this.acceptor = acceptor;
    }

    public void setSenderCompId(String senderCompId) {
        SenderCompId = senderCompId;
    }

    public void setTargetCompId(String targetCompId) {
        TargetCompId = targetCompId;
    }

    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }
}