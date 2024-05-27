/*
 * This file is generated by jOOQ.
 */
package edu.java.scrapper.domain.jooq.tables;


import edu.java.scrapper.domain.jooq.DefaultSchema;
import edu.java.scrapper.domain.jooq.Keys;
import edu.java.scrapper.domain.jooq.tables.records.ChatToLinkRecord;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function2;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


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
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class ChatToLink extends TableImpl<ChatToLinkRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>CHAT_TO_LINK</code>
     */
    public static final ChatToLink CHAT_TO_LINK = new ChatToLink();

    /**
     * The class holding records for this type
     */
    @Override
    @NotNull
    public Class<ChatToLinkRecord> getRecordType() {
        return ChatToLinkRecord.class;
    }

    /**
     * The column <code>CHAT_TO_LINK.CHAT_ID</code>.
     */
    public final TableField<ChatToLinkRecord, Long> CHAT_ID = createField(DSL.name("CHAT_ID"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>CHAT_TO_LINK.LINK_ID</code>.
     */
    public final TableField<ChatToLinkRecord, Long> LINK_ID = createField(DSL.name("LINK_ID"), SQLDataType.BIGINT, this, "");

    private ChatToLink(Name alias, Table<ChatToLinkRecord> aliased) {
        this(alias, aliased, null);
    }

    private ChatToLink(Name alias, Table<ChatToLinkRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>CHAT_TO_LINK</code> table reference
     */
    public ChatToLink(String alias) {
        this(DSL.name(alias), CHAT_TO_LINK);
    }

    /**
     * Create an aliased <code>CHAT_TO_LINK</code> table reference
     */
    public ChatToLink(Name alias) {
        this(alias, CHAT_TO_LINK);
    }

    /**
     * Create a <code>CHAT_TO_LINK</code> table reference
     */
    public ChatToLink() {
        this(DSL.name("CHAT_TO_LINK"), null);
    }

    public <O extends Record> ChatToLink(Table<O> child, ForeignKey<O, ChatToLinkRecord> key) {
        super(child, key, CHAT_TO_LINK);
    }

    @Override
    @Nullable
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    @NotNull
    public List<ForeignKey<ChatToLinkRecord, ?>> getReferences() {
        return Arrays.asList(Keys.CONSTRAINT_5, Keys.CONSTRAINT_53);
    }

    private transient Chat _chat;
    private transient Link _link;

    /**
     * Get the implicit join path to the <code>PUBLIC.CHAT</code> table.
     */
    public Chat chat() {
        if (_chat == null)
            _chat = new Chat(this, Keys.CONSTRAINT_5);

        return _chat;
    }

    /**
     * Get the implicit join path to the <code>PUBLIC.LINK</code> table.
     */
    public Link link() {
        if (_link == null)
            _link = new Link(this, Keys.CONSTRAINT_53);

        return _link;
    }

    @Override
    @NotNull
    public ChatToLink as(String alias) {
        return new ChatToLink(DSL.name(alias), this);
    }

    @Override
    @NotNull
    public ChatToLink as(Name alias) {
        return new ChatToLink(alias, this);
    }

    @Override
    @NotNull
    public ChatToLink as(Table<?> alias) {
        return new ChatToLink(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public ChatToLink rename(String name) {
        return new ChatToLink(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public ChatToLink rename(Name name) {
        return new ChatToLink(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public ChatToLink rename(Table<?> name) {
        return new ChatToLink(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function2<? super Long, ? super Long, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function2<? super Long, ? super Long, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}