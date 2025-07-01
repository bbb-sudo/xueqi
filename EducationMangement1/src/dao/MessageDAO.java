package dao;
import java.util.List;

import model.*;
public interface MessageDAO {
    Message getMessageById(int id);
    boolean addMessage(Message message);
    boolean deleteMessage(int id);
    List<Message> getMessagesByReceiver(int receiverId);
    List<Message> getMessagesBySender(int senderId);
}