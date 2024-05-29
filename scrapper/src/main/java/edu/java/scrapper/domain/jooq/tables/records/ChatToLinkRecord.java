/*
 * This file is generated by jOOQ.
 */

package edu.java.scrapper.domain.jooq.tables.records;

import edu.java.scrapper.domain.jooq.tables.ChatToLink;
import java.beans.ConstructorProperties;
import javax.annotation.processing.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;

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
public class ChatToLinkRecord extends TableRecordImpl<ChatToLinkRecord> implements Record2<Long, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>CHAT_TO_LINK.CHAT_ID</code>.
     */
    public void setChatId(@Nullable Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>CHAT_TO_LINK.CHAT_ID</code>.
     */
    @Nullable
    public Long getChatId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>CHAT_TO_LINK.LINK_ID</code>.
     */
    public void setLinkId(@Nullable Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>CHAT_TO_LINK.LINK_ID</code>.
     */
    @Nullable
    public Long getLinkId() {
        return (Long) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    @NotNull
    public Row2<Long, Long> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    @NotNull
    public Field<Long> field1() {
        return ChatToLink.CHAT_TO_LINK.CHAT_ID;
    }

    @Override
    @NotNull
    public Field<Long> field2() {
        return ChatToLink.CHAT_TO_LINK.LINK_ID;
    }

    @Override
    @Nullable
    public Long component1() {
        return getChatId();
    }

    @Override
    @Nullable
    public Long component2() {
        return getLinkId();
    }

    @Override
    @Nullable
    public Long value1() {
        return getChatId();
    }

    @Override
    @Nullable
    public Long value2() {
        return getLinkId();
    }

    @Override
    @NotNull
    public ChatToLinkRecord value1(@Nullable Long value) {
        setChatId(value);
        return this;
    }

    @Override
    @NotNull
    public ChatToLinkRecord value2(@Nullable Long value) {
        setLinkId(value);
        return this;
    }

    @Override
    @NotNull
    public ChatToLinkRecord values(@Nullable Long value1, @Nullable Long value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ChatToLinkRecord
     */
    public ChatToLinkRecord() {
        super(ChatToLink.CHAT_TO_LINK);
    }

    /**
     * Create a detached, initialised ChatToLinkRecord
     */
    @ConstructorProperties({"chatId", "linkId"})
    public ChatToLinkRecord(@Nullable Long chatId, @Nullable Long linkId) {
        super(ChatToLink.CHAT_TO_LINK);

        setChatId(chatId);
        setLinkId(linkId);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised ChatToLinkRecord
     */
    public ChatToLinkRecord(edu.java.scrapper.domain.jooq.tables.pojos.ChatToLink value) {
        super(ChatToLink.CHAT_TO_LINK);

        if (value != null) {
            setChatId(value.getChatId());
            setLinkId(value.getLinkId());
            resetChangedOnNotNull();
        }
    }
}
