package com.turkcell.TurkcellCRM.productService.core.business.abstracts;

public interface MessageService {
    String getMessages(String key);
    String getMessagesWithArgs(String key, Object... args);
}
