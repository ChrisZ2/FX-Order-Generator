package com.chris.projects.fx.fog.fix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.*;

/**
 * TODO: Move to fix common module
 */
public class FixConnectorFactoryImpl implements FixConnectorFactory{

    private static final Logger LOG = LoggerFactory.getLogger(FixConnectorFactoryImpl.class);

    @Override
    public Connector createSocketAcceptor(Application application, MessageStoreFactory messageStoreFactory, SessionSettings sessionSettings, LogFactory logFactory, MessageFactory messageFactory) throws ConfigError {
        LOG.info("Creating fix socket acceptor...");
        return new SocketAcceptor(application, messageStoreFactory, sessionSettings, logFactory, messageFactory);
    }

    @Override
    public Connector createSocketInitiator(Application application, MessageStoreFactory messageStoreFactory, SessionSettings sessionSettings, LogFactory logFactory, MessageFactory messageFactory) throws ConfigError {
        LOG.info("Creating fix socket initiator...");
        return new SocketInitiator(application, messageStoreFactory, sessionSettings, logFactory, messageFactory);
    }
}
