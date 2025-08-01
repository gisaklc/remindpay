package com.remindpay.service;

import com.remindpay.scheduler.Message;

public interface NotificationService <R> {

    R send(Message message);


}
