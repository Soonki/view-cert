package com.demo.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Collection;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

/**
 * Represents an attack target user (login ID only, or login ID and encrypted password).
 */
public class AttackTargetEntity {
    private String loginId;
    private String encryptedPassword;

    public AttackTargetEntity() {
    }

    public AttackTargetEntity(String loginId) {
        this(loginId, null);
    }

    public AttackTargetEntity(String loginId, String encryptedPassword) {
        this.loginId = loginId;
        this.encryptedPassword = encryptedPassword;
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
