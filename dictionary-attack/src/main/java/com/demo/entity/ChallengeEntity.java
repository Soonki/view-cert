package com.demo.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Collection;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

/**
 * Represents a password to try with.
 */
public class ChallengeEntity {
    private String plaintextPassword;
    private String precalculatedEncryptedPassword; // Used by Rainbow attack only.

    public ChallengeEntity(String plaintextPassword) {
        this.plaintextPassword = plaintextPassword;
    }

    public String getPlaintextPassword() {
        return plaintextPassword;
    }

    public void setPlaintextPassword(String plaintextPassword) {
        this.plaintextPassword = plaintextPassword;
    }

    public String getPrecalculatedEncryptedPassword() {
        return precalculatedEncryptedPassword;
    }

    public void setPrecalculatedEncryptedPassword(String precalculatedEncryptedPassword) {
        this.precalculatedEncryptedPassword = precalculatedEncryptedPassword;
    }

    @Override
    public boolean equals(Object obj) {
        return reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return (new ReflectionToStringBuilder(this, SHORT_PREFIX_STYLE) {
            @Override
            protected boolean accept(java.lang.reflect.Field f) {
                return super.accept(f) && !Collection.class.isAssignableFrom(f.getType());
            }
        }).toString();
    }
}
