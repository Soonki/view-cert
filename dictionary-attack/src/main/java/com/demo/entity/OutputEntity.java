package com.demo.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Collection;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

/**
 * Represents a record to be logged with discovered password.
 */
public class OutputEntity {
    private String loginId;
    private String encryptedPassword;
    private String plaintextPassword;

    public OutputEntity() {
    }

    public OutputEntity(String loginId,
                        String encryptedPassword,
                        String plaintextPassword) {
        this.loginId = loginId;
        this.encryptedPassword = encryptedPassword;
        this.plaintextPassword = plaintextPassword;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getPlaintextPassword() {
        return plaintextPassword;
    }

    public void setPlaintextPassword(String plaintextPassword) {
        this.plaintextPassword = plaintextPassword;
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
