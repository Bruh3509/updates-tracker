/*
 * This file is generated by jOOQ.
 */

package edu.java.scrapper.domain.jooq.tables.pojos;

import jakarta.validation.constraints.Size;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import javax.annotation.processing.Generated;
import org.jetbrains.annotations.NotNull;

/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.18.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long chatId;
    private String userName;

    public Chat() {
    }

    public Chat(Chat value) {
        this.chatId = value.chatId;
        this.userName = value.userName;
    }

    @ConstructorProperties({"chatId", "userName"})
    public Chat(
        @NotNull Long chatId,
        @NotNull String userName
    ) {
        this.chatId = chatId;
        this.userName = userName;
    }

    /**
     * Getter for <code>CHAT.CHAT_ID</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Long getChatId() {
        return this.chatId;
    }

    /**
     * Setter for <code>CHAT.CHAT_ID</code>.
     */
    public void setChatId(@NotNull Long chatId) {
        this.chatId = chatId;
    }

    /**
     * Getter for <code>CHAT.USER_NAME</code>.
     */
    @jakarta.validation.constraints.NotNull
    @Size(max = 255)
    @NotNull
    public String getUserName() {
        return this.userName;
    }

    /**
     * Setter for <code>CHAT.USER_NAME</code>.
     */
    public void setUserName(@NotNull String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Chat other = (Chat) obj;
        if (this.chatId == null) {
            if (other.chatId != null) {
                return false;
            }
        } else if (!this.chatId.equals(other.chatId)) {
            return false;
        }
        if (this.userName == null) {
            if (other.userName != null) {
                return false;
            }
        } else if (!this.userName.equals(other.userName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.chatId == null) ? 0 : this.chatId.hashCode());
        result = prime * result + ((this.userName == null) ? 0 : this.userName.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Chat (");

        sb.append(chatId);
        sb.append(", ").append(userName);

        sb.append(")");
        return sb.toString();
    }
}
