package service;

import model.Message;
import java.util.List;

public interface MessageService {
    List<Message> getInbox(int userId);
    List<Message> getSentBox(int userId);
    List<Message> getTrash(int userId);
    List<Message> searchMessages(int userId, String keywords);
    void sendMessage(Message msg);
    void deleteMessage(int msgId, int userId);
}