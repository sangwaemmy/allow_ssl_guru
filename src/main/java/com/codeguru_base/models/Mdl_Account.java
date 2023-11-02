/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import javax.validation.constraints.Size;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mdl_Account implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    @Size(min = 1, max = 18, message = " username should not be empty, null and or lengt exceed 18")
    @Column(name = "username",length = 30, nullable = false)
    private String username;

    @Size(min = 1, max = 30, message = " password should not be empty, null and or lengt exceed 18")
    @Column(name = "password", length = 30,nullable = false)
    private String password;

    public Mdl_Account(String username, String password, Mdl_profile mdl_profile, Mdl_account_category mdl_account_category) {
        this.username = username;
        this.password = password;
        this.mdl_profile = mdl_profile;
        this.mdl_account_category = mdl_account_category;
    }
    
    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    Mdl_profile mdl_profile;

    
    @ManyToOne()
    @JoinColumn(name = "category_id", nullable = false)
    Mdl_account_category mdl_account_category;
    

}
