package com.chris.projects.fx.fog.fix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.*;

public class FixApplication implements Application {

    private static final Logger LOG = LoggerFactory.getLogger(FixApplication.class);
    private final MessageCracker fogFixMessageCracker;

    public FixApplication(MessageCracker fogFixMessageCracker) {
        this.fogFixMessageCracker = fogFixMessageCracker;
    }

    @Override
    public void onCreate(SessionID sessionID) {
        LOG.info("Session {} created", sessionID);
    }

    @Override
    public void onLogon(SessionID sessionID) {
        LOG.info("Session {} log on", sessionID);
    }

    @Override
    public void onLogout(SessionID sessionID) {
        LOG.info("Session {} log out", sessionID);
    }

    @Override
    public void toAdmin(Message message, SessionID sessionID) {
        LOG.info("To Admin {}", sessionID);
    }

    @Override
    public void fromAdmin(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        LOG.info("From Admin {}", sessionID);
    }

    @Override
    public void toApp(Message message, SessionID sessionID) throws DoNotSend {
        LOG.info("To App with message {}, with session {}", message, sessionID);
    }

    @Override
    public void fromApp(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        LOG.info("From App with message {}, with session {}", message, sessionID);
        fogFixMessageCracker.crack(message, sessionID);
    }

}
