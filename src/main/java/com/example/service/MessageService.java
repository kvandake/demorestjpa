package com.example.service;

import com.example.CustomerProtos;
import com.example.entity.MessageEntity;
import com.example.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * Сервис бизнес-логики сообщений (тут может быть отправка пушей, статистика итд)
 */
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    /**
     * Добавление сообщения
     * @param message
     * @return
     */
    @Transactional
    public MessageEntity InsertMessage(@NotNull MessageEntity message) {
        messageRepository.saveAndFlush(message);
        return message;
    }

    /**
     * Обновление сообщения
     * @param message
     * @return
     */
    @Transactional
    public MessageEntity UpdateMessage(@NotNull MessageEntity message) {
        messageRepository.saveAndFlush(message);
        return message;
    }

    /**
     * Получение сообщения по Ид
     * @param idMessage
     * @return
     */
    public MessageEntity GetMessage(Integer idMessage) {
        return messageRepository.findOne(idMessage);
    }

    /**
     * Получение всех сообщений
     * @return
     */
    public List<MessageEntity> GetMessages() {
        return messageRepository.findAll();
    }

    /**
     * удаление сообщения
     * @param idMessage
     * @return
     */
    @Transactional
    public boolean DeleteMessage(Integer idMessage) {
        messageRepository.delete(idMessage);
        CustomerProtos.Organization f =  CustomerProtos.Organization.newBuilder().addCustomer(CustomerProtos.Customer.newBuilder().addEmail())

        return true;
    }
}
