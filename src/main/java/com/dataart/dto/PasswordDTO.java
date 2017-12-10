package com.dataart.dto;

import java.io.Serializable;

/**
 * Created by Роман on 19.11.2017.
 */
public class PasswordDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String passwordOld;

    private String passwordNew;

    public PasswordDTO() {
    }

    public PasswordDTO(String passwordOld, String passwordNew) {
        this.passwordOld = passwordOld;
        this.passwordNew = passwordNew;
    }

    public String getPasswordOld() {
        return passwordOld;
    }

    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PasswordDTO that = (PasswordDTO) o;

        if (passwordOld != null ? !passwordOld.equals(that.passwordOld) : that.passwordOld != null) return false;
        return passwordNew != null ? passwordNew.equals(that.passwordNew) : that.passwordNew == null;
    }

    @Override
    public int hashCode() {
        int result = passwordOld != null ? passwordOld.hashCode() : 0;
        result = 31 * result + (passwordNew != null ? passwordNew.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PasswordDTO{" +
                "passwordOld='" + passwordOld + '\'' +
                ", passwordNew='" + passwordNew + '\'' +
                '}';
    }
}
