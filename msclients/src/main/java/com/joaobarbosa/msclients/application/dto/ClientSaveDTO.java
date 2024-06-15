package com.joaobarbosa.msclients.application.dto;

import com.joaobarbosa.msclients.domain.Client;
import lombok.Data;

import java.io.Serializable;

@Data
public class ClientSaveDTO implements Serializable {

    private String cpf;
    private String name;
    private Integer age;

    public Client convertToClient() {
        return new Client(cpf, name, age);
    }

}
