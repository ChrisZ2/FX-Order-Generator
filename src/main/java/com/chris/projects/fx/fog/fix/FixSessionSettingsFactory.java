package com.chris.projects.fx.fog.fix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.ConfigError;
import quickfix.SessionSettings;

import java.io.InputStream;

import static java.util.Objects.requireNonNull;

/**
 * TODO: Move to common module
 */
public class FixSessionSettingsFactory implements FixSessionSettings{

    private static final Logger LOG = LoggerFactory.getLogger(FixSessionSettingsFactory.class);

    private final String fileName;

    public FixSessionSettingsFactory(String fileName) {
        this.fileName = requireNonNull(fileName);
    }

    private InputStream getConfigInputStream(){
        return this.getClass().getClassLoader().getResourceAsStream(fileName);
    }

    @Override
    public SessionSettings create() throws ConfigError {
        return new SessionSettings(getConfigInputStream());
    }
}
