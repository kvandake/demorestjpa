package com.example.entity;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by kvandake on 18.07.16.
 */
@Entity
@Table(name = "Messages", schema = "public", catalog = "postgres")
public class MessageEntity {
    private Integer idMessage;
    private String message;
    private Date dateCreated;

    /**
     * Генерация автоинкремента первичного ключа
     * @return
     */
    @Id
    @Column(name = "id_message", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Integer idMessage) {
        this.idMessage = idMessage;
    }

    /**
     * Сообщения
     * @return
     */
    @Basic
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Дата сообщения
     * @return
     */
    @Basic
    @Column(name = "date_created")
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageEntity that = (MessageEntity) o;

        if (idMessage != null ? !idMessage.equals(that.idMessage) : that.idMessage != null)
            return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMessage != null ? idMessage.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        return result;
    }
}
