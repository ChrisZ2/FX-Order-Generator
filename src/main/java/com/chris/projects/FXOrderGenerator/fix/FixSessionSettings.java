package com.chris.projects.FXOrderGenerator.fix;

import quickfix.ConfigError;
import quickfix.SessionSettings;

public interface FixSessionSettings {
    SessionSettings create() throws ConfigError;
}
