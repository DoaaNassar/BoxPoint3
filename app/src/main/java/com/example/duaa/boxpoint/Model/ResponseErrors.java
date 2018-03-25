package com.example.duaa.boxpoint.Model;

import java.util.List;

/**
 * Created by AL-Qema on 18/03/18.
 */

public class ResponseErrors {
    private boolean status;
    private List<Error> errors;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public class Error {
        private String fieldname;
        private String message;

        public String getFieldname() {
            return fieldname;
        }

        public void setFieldname(String fieldname) {
            this.fieldname = fieldname;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


}
