package com.chris.projects.fx.fog.fix;

import quickfix.Message;

public interface FIxSender {
    boolean send(Message message, String senderCompId, String targetCompId);

}
