package edu.java.scrapper.service.interfaces;

public interface ChatService {
    void register(long chatId, String userName);

    void unregister(long chatId);
}
