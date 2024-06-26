/*
 * This file is generated by jOOQ.
 */

package edu.java.scrapper.domain.jooq.tables.records;

import edu.java.scrapper.domain.jooq.tables.Link;
import jakarta.validation.constraints.Size;
import java.beans.ConstructorProperties;
import java.time.OffsetDateTime;
import javax.annotation.processing.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;

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
public class LinkRecord extends UpdatableRecordImpl<LinkRecord> implements Record4<Long, String, Long, OffsetDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>LINK.LINK_ID</code>.
     */
    public void setLinkId(@NotNull Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>LINK.LINK_ID</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Long getLinkId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>LINK.LINK_NAME</code>.
     */
    public void setLinkName(@NotNull String value) {
        set(1, value);
    }

    /**
     * Getter for <code>LINK.LINK_NAME</code>.
     */
    @jakarta.validation.constraints.NotNull
    @Size(max = 255)
    @NotNull
    public String getLinkName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>LINK.LAST_CHECK</code>.
     */
    public void setLastCheck(@Nullable Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>LINK.LAST_CHECK</code>.
     */
    @Nullable
    public Long getLastCheck() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>LINK.LAST_UPDATE</code>.
     */
    public void setLastUpdate(@Nullable OffsetDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>LINK.LAST_UPDATE</code>.
     */
    @Nullable
    public OffsetDateTime getLastUpdate() {
        return (OffsetDateTime) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Row4<Long, String, Long, OffsetDateTime> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    @NotNull
    public Row4<Long, String, Long, OffsetDateTime> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    @NotNull
    public Field<Long> field1() {
        return Link.LINK.LINK_ID;
    }

    @Override
    @NotNull
    public Field<String> field2() {
        return Link.LINK.LINK_NAME;
    }

    @Override
    @NotNull
    public Field<Long> field3() {
        return Link.LINK.LAST_CHECK;
    }

    @Override
    @NotNull
    public Field<OffsetDateTime> field4() {
        return Link.LINK.LAST_UPDATE;
    }

    @Override
    @NotNull
    public Long component1() {
        return getLinkId();
    }

    @Override
    @NotNull
    public String component2() {
        return getLinkName();
    }

    @Override
    @Nullable
    public Long component3() {
        return getLastCheck();
    }

    @Override
    @Nullable
    public OffsetDateTime component4() {
        return getLastUpdate();
    }

    @Override
    @NotNull
    public Long value1() {
        return getLinkId();
    }

    @Override
    @NotNull
    public String value2() {
        return getLinkName();
    }

    @Override
    @Nullable
    public Long value3() {
        return getLastCheck();
    }

    @Override
    @Nullable
    public OffsetDateTime value4() {
        return getLastUpdate();
    }

    @Override
    @NotNull
    public LinkRecord value1(@NotNull Long value) {
        setLinkId(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord value2(@NotNull String value) {
        setLinkName(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord value3(@Nullable Long value) {
        setLastCheck(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord value4(@Nullable OffsetDateTime value) {
        setLastUpdate(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord values(
        @NotNull Long value1,
        @NotNull String value2,
        @Nullable Long value3,
        @Nullable OffsetDateTime value4
    ) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached LinkRecord
     */
    public LinkRecord() {
        super(Link.LINK);
    }

    /**
     * Create a detached, initialised LinkRecord
     */
    @ConstructorProperties({"linkId", "linkName", "lastCheck", "lastUpdate"})
    public LinkRecord(
        @NotNull Long linkId,
        @NotNull String linkName,
        @Nullable Long lastCheck,
        @Nullable OffsetDateTime lastUpdate
    ) {
        super(Link.LINK);

        setLinkId(linkId);
        setLinkName(linkName);
        setLastCheck(lastCheck);
        setLastUpdate(lastUpdate);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised LinkRecord
     */
    public LinkRecord(edu.java.scrapper.domain.jooq.tables.pojos.Link value) {
        super(Link.LINK);

        if (value != null) {
            setLinkId(value.getLinkId());
            setLinkName(value.getLinkName());
            setLastCheck(value.getLastCheck());
            setLastUpdate(value.getLastUpdate());
            resetChangedOnNotNull();
        }
    }
}
