package com.hamitmizrak.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

//Entity
@Entity
@Table(name = "register")
public class RegisterEntity extends BaseEntity implements Serializable {
public static final long serialVersionUID=1L;
    private String name;
    private String surname;

    //@Column(name = "email",length = 125,nullable = false,unique = true)
    private String email;
    private String password;


    //javada olsun ancak database bu attribute eklemesin
    //@Transient
    //private String justJava;

    //büyük datalar icin kullanalım.
    //@Lob
    //private Object bigData;
}
