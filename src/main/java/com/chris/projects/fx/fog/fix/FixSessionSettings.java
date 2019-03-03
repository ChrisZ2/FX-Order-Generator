package com.chris.projects.fx.fog.fix;

import quickfix.ConfigError;
import quickfix.SessionSettings;

public interface FixSessionSettings {
    SessionSettings create() throws ConfigError;
}
