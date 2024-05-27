/*
 * This file is generated by jOOQ.
 */
package edu.java.scrapper.domain.jooq.tables.pojos;


import jakarta.validation.constraints.Size;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


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
public class Link implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long linkId;
    private String linkName;
    private Long lastCheck;
    private OffsetDateTime lastUpdate;

    public Link() {}

    public Link(Link value) {
        this.linkId = value.linkId;
        this.linkName = value.linkName;
        this.lastCheck = value.lastCheck;
        this.lastUpdate = value.lastUpdate;
    }

    @ConstructorProperties({ "linkId", "linkName", "lastCheck", "lastUpdate" })
    public Link(
        @NotNull Long linkId,
        @NotNull String linkName,
        @Nullable Long lastCheck,
        @Nullable OffsetDateTime lastUpdate
    ) {
        this.linkId = linkId;
        this.linkName = linkName;
        this.lastCheck = lastCheck;
        this.lastUpdate = lastUpdate;
    }

    /**
     * Getter for <code>LINK.LINK_ID</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Long getLinkId() {
        return this.linkId;
    }

    /**
     * Setter for <code>LINK.LINK_ID</code>.
     */
    public void setLinkId(@NotNull Long linkId) {
        this.linkId = linkId;
    }

    /**
     * Getter for <code>LINK.LINK_NAME</code>.
     */
    @jakarta.validation.constraints.NotNull
    @Size(max = 255)
    @NotNull
    public String getLinkName() {
        return this.linkName;
    }

    /**
     * Setter for <code>LINK.LINK_NAME</code>.
     */
    public void setLinkName(@NotNull String linkName) {
        this.linkName = linkName;
    }

    /**
     * Getter for <code>LINK.LAST_CHECK</code>.
     */
    @Nullable
    public Long getLastCheck() {
        return this.lastCheck;
    }

    /**
     * Setter for <code>LINK.LAST_CHECK</code>.
     */
    public void setLastCheck(@Nullable Long lastCheck) {
        this.lastCheck = lastCheck;
    }

    /**
     * Getter for <code>LINK.LAST_UPDATE</code>.
     */
    @Nullable
    public OffsetDateTime getLastUpdate() {
        return this.lastUpdate;
    }

    /**
     * Setter for <code>LINK.LAST_UPDATE</code>.
     */
    public void setLastUpdate(@Nullable OffsetDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Link other = (Link) obj;
        if (this.linkId == null) {
            if (other.linkId != null)
                return false;
        }
        else if (!this.linkId.equals(other.linkId))
            return false;
        if (this.linkName == null) {
            if (other.linkName != null)
                return false;
        }
        else if (!this.linkName.equals(other.linkName))
            return false;
        if (this.lastCheck == null) {
            if (other.lastCheck != null)
                return false;
        }
        else if (!this.lastCheck.equals(other.lastCheck))
            return false;
        if (this.lastUpdate == null) {
            if (other.lastUpdate != null)
                return false;
        }
        else if (!this.lastUpdate.equals(other.lastUpdate))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.linkId == null) ? 0 : this.linkId.hashCode());
        result = prime * result + ((this.linkName == null) ? 0 : this.linkName.hashCode());
        result = prime * result + ((this.lastCheck == null) ? 0 : this.lastCheck.hashCode());
        result = prime * result + ((this.lastUpdate == null) ? 0 : this.lastUpdate.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Link (");

        sb.append(linkId);
        sb.append(", ").append(linkName);
        sb.append(", ").append(lastCheck);
        sb.append(", ").append(lastUpdate);

        sb.append(")");
        return sb.toString();
    }
}