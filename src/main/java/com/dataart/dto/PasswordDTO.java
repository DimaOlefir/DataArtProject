package com.dataart.dto;

import java.io.Serializable;

/**
 * Created by Роман on 19.11.2017.
 */
public class PasswordDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String paswwordOld;

    private String paswwordNew;

    public PasswordDTO(String paswwordOld, String paswwordNew) {
        this.paswwordOld = paswwordOld;
        this.paswwordNew = paswwordNew;
    }

    public String getPaswwordOld() {
        return paswwordOld;
    }

    public void setPaswwordOld(String paswwordOld) {
        this.paswwordOld = paswwordOld;
    }

    public String getPaswwordNew() {
        return paswwordNew;
    }

    public void setPaswwordNew(String paswwordNew) {
        this.paswwordNew = paswwordNew;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PasswordDTO that = (PasswordDTO) o;

        if (paswwordOld != null ? !paswwordOld.equals(that.paswwordOld) : that.paswwordOld != null) return false;
        return paswwordNew != null ? paswwordNew.equals(that.paswwordNew) : that.paswwordNew == null;
    }

    @Override
    public int hashCode() {
        int result = paswwordOld != null ? paswwordOld.hashCode() : 0;
        result = 31 * result + (paswwordNew != null ? paswwordNew.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PasswordDTO{" +
                "paswwordOld='" + paswwordOld + '\'' +
                ", paswwordNew='" + paswwordNew + '\'' +
                '}';
    }
}
