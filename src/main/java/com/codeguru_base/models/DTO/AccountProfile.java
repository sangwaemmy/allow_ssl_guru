/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.models.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@Setter @Getter
public class AccountProfile {

    private long profile_id;
    private String name;
    private String surname;
    private String date_birth;
    private String gender;
    private String tel;
    private String email;
    private String residence;
    private int image;
    //account
    private int account_category;
    private String username;
    private String password;

    public AccountProfile(long profile_id, String name, String surname, String tel, String username, String password) {
        this.profile_id = profile_id;
        this.name = name;
        this.surname = surname;
        this.tel = tel;
        this.username = username;
        this.password = password;
    }
    
    

}
