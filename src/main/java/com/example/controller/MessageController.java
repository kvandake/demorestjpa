package com.example.controller;

import com.example.entity.MessageEntity;
import com.example.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для работы с сообщениями
 */
@RestController
@RequestMapping(value = "/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * Добавление сообщения
     * @param message
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public MessageEntity InsertMessage(@RequestBody MessageEntity message) {
        return messageService.InsertMessage(message);
    }

    /**
     * Обновление сообщения, нужен Ид
     * @param message
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public MessageEntity UpdateMessage(@RequestBody MessageEntity message) {
        return messageService.UpdateMessage(message);
    }

    /**
     * Получение сообщения по Ид
     * @param idMessage
     * @return
     */
    @RequestMapping(value = "/{idMessage}", method = RequestMethod.GET)
    public MessageEntity GetMessage(@PathVariable Integer idMessage) {
        return messageService.GetMessage(idMessage);
    }

    /**
     * Получение всех сообщений
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<MessageEntity> GetMessages() {
        return messageService.GetMessages();
    }

    /**
     * Удаление сообщения
     * @param idMessage
     * @return
     */
    @RequestMapping(value = "/{idMessage}", method = RequestMethod.DELETE)
    public boolean DeleteMessage(@PathVariable Integer idMessage) {
        return messageService.DeleteMessage(idMessage);
    }
}
