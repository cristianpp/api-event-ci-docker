package com.springboot.data.api.external.cpf;

import java.io.Serializable;

public class Status implements Serializable {


    private boolean valid;

    public Status(){}

    public boolean isValid() {
        return valid;
    }

}
