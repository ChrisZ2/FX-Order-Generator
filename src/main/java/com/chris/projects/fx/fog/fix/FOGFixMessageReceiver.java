package com.chris.projects.fx.fog.fix;

import quickfix.MessageCracker;
import quickfix.SessionID;
import quickfix.fix44.ExecutionReport;

public class FOGFixMessageReceiver extends MessageCracker {

    @Handler
    public void onMessage(ExecutionReport message, SessionID sessionID) {
        //handle message
    }

}
