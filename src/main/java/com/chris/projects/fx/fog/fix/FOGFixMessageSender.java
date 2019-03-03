package com.chris.projects.fx.fog.fix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.Message;
import quickfix.Session;

public class FOGFixMessageSender implements FixSender {
    private static final Logger LOG = LoggerFactory.getLogger(FOGFixMessageSender.class);
    private final String senderCompId;
    private final String targetCompId;

    public FOGFixMessageSender(String senderCompId, String targetCompId) {
        this.senderCompId = senderCompId;
        this.targetCompId = targetCompId;
    }

    @Override
    public boolean send(final Message message, final String senderCompId, final String targetCompId) {
        try {
            LOG.info("Sending message {} to target {}", message, targetCompId);
            return Session.sendToTarget(message, senderCompId, targetCompId);
        }catch (Exception ex) {
            LOG.error("Unable to send fix message {}", message);
        }
        return false;
    }
}
