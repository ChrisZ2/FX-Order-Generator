package com.chris.projects.fx.fog.fix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.*;

/**
 * TODO: Move to fix common module
 */
public class FixSessionConnector implements SessionConnector {

    private static final Logger LOG = LoggerFactory.getLogger(FixSessionConnector.class);
    private Connector connector;
    private FixConnectorFactory fixConnectorFactory;
    private volatile boolean isFixSessionStarted;

    public FixSessionConnector(String configFile, boolean isAcceptor, Application fixApplication, FixConnectorFactory fixConnectorFactory) {
        this.fixConnectorFactory = fixConnectorFactory;
        this.connector = createConnector(configFile, isAcceptor, fixApplication);
    }

    @Override
    public void start() {
        startFixSession();
    }

    @Override
    public void stop() {
        stopSession();
    }

    private void startFixSession() {
        try {
            connector.start();
            isFixSessionStarted  = true;
        } catch (ConfigError configError) {
            LOG.error("Fail to start FOG fix application {}, with exception", configError.getMessage(), configError);
        }
    }

    private void stopSession() {
        connector.stop();
        isFixSessionStarted = false;
    }


    private Connector createConnector(String configFile, boolean isAcceptor, Application fixApplication) {
        try {
            SessionSettings sessionSettings = getSessionSettings(configFile);
            MessageStoreFactory messageStoreFactory = new FileStoreFactory(sessionSettings);
            MessageFactory messageFactory = new DefaultMessageFactory();
            LogFactory logFactory = new SLF4JLogFactory(sessionSettings);
            if (isAcceptor) {
                return fixConnectorFactory.createSocketAcceptor(fixApplication, messageStoreFactory, sessionSettings, logFactory, messageFactory);
            }else {
                return fixConnectorFactory.createSocketInitiator(fixApplication, messageStoreFactory, sessionSettings, logFactory, messageFactory);

            }
        }catch (ConfigError | RuntimeException ex) {
            LOG.error("Fail to create FOG fix application {}, with exception {}", ex.getMessage(), ex);
            return null; // bad practice, show return some special case
        }
    }

    private SessionSettings getSessionSettings(String configFile) throws ConfigError {
        LOG.info("Loading fix configuration from: {}", configFile);
        final SessionSettings fixSessionSettings = new FixSessionSettingsFactory(configFile).create();
        LOG.info("Fix session {} created", fixSessionSettings);
        return fixSessionSettings;
    }


}
