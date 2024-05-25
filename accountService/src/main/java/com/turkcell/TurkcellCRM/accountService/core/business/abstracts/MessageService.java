package com.turkcell.TurkcellCRM.accountService.core.business.abstracts;

public interface MessageService {
    String getMessages(String key);
    String getMessagesWithArgs(String key, Object... args);
}
