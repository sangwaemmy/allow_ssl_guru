/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@Entity
@Table(name = "profile")
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mdl_profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1, max = 18, message = " name should not be empty, null and or lengt exceed 18")
    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Size(min = 1, max = 18, message = " surname should not be empty, null and or lengt exceed 18")
    @Column(name = "surname", length = 30, nullable = false)
    private String surname;

    @Size(min = 1, max = 18, message = " date_birth should not be empty, null and or lengt exceed 18")
    @Column(name = "date_birth", nullable = true)
    private String date_birth;

    @Size(min = 1, max = 18, message = " gender should not be empty, null and or lengt exceed 18")
    @Column(name = "gender", length = 30, nullable = true)
    private String gender;

    @Size(min = 1, max = 18, message = " tel should not be empty, null and or lengt exceed 18")
    @Column(name = "Stel", nullable = true)
    private String tel;

    @Size(min = 1, max = 18, message = " email should not be empty, null and or lengt exceed 18")
    @Column(name = "email", length = 30, nullable = true)
    private String email;

    @Size(min = 1, max = 18, message = " residence should not be empty, null and or lengt exceed 18")
    @Column(name = "residence", length = 30, nullable = true)
    private String residence;

    @Size(min = 1, max = 18, message = " image should not be empty, null and or lengt exceed 18")
    @Column(name = "image", length = 30, nullable = true)
    private int image;

    public Mdl_profile(String name, String surname, String gender, String tel, String email) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.tel = tel;
        this.email = email;

    }

//    @OneToMany(mappedBy = "mdl_profile")
//    @JsonIgnoreProperties("mdl_profile")
//    private List<Mdl_Account> o_accountsprofile;
}
