package com.chris.projects.FXOrderGenerator.fix;

import quickfix.Message;

public interface FixSender {

    boolean send(Message message, String senderCompId, String targetCompId);
}
