package ServiceImp;

import dao.MessageDAO;
import model.Message;
import service.MessageService;

import java.util.ArrayList;
import java.util.List;

public class MessageServiceImpl implements MessageService {
    private MessageDAO messageDAO;

    public MessageServiceImpl(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @Override
    public List<Message> getInbox(int userId) {
        return messageDAO.getMessagesByReceiver(userId);
    }

    @Override
    public List<Message> getSentBox(int userId) {
        return messageDAO.getMessagesBySender(userId);
    }

    @Override
    public List<Message> getTrash(int userId) {
        // 实际应有status字段区分，此处假设status为"trash"即为垃圾箱
        List<Message> trash = new ArrayList<>();
        List<Message> inbox = messageDAO.getMessagesByReceiver(userId);
        for (Message m : inbox) {
            if ("trash".equals(m.getStatus())) trash.add(m);
        }
        List<Message> sent = messageDAO.getMessagesBySender(userId);
        for (Message m : sent) {
            if ("trash".equals(m.getStatus())) trash.add(m);
        }
        return trash;
    }

    @Override
    public List<Message> searchMessages(int userId, String keywords) {
        // 简单遍历所有收发件箱消息，实际应由DAO支持模糊查询
        List<Message> result = new ArrayList<>();
        List<Message> all = new ArrayList<>();
        all.addAll(messageDAO.getMessagesByReceiver(userId));
        all.addAll(messageDAO.getMessagesBySender(userId));
        for (Message m : all) {
            if (m.getContent() != null && m.getContent().contains(keywords)) {
                result.add(m);
            }
        }
        return result;
    }

    @Override
    public void sendMessage(Message msg) {
        messageDAO.addMessage(msg);
    }

    @Override
    public void deleteMessage(int msgId, int userId) {
        Message m = messageDAO.getMessageById(msgId);
        if (m != null && (m.getSenderId() == userId || m.getReceiverId() == userId)) {
            messageDAO.deleteMessage(msgId);
        }
    }
}