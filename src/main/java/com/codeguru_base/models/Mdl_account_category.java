/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@Entity
@Table(name = "account_category")
@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mdl_account_category implements Serializable {

    @Id
    @Column(name = "id",  nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1, max = 18,  message = " name should not be empty, null and or lengt exceed 18")
    @Column(name = "name", unique = true, length = 20, nullable = false)
    private String name;

    public Mdl_account_category(String name, List<Mdl_Account> o_categoryaccounts) {
        this.name = name;
        this.o_categoryaccounts = o_categoryaccounts;
    }

    @OneToMany(mappedBy = "mdl_account_category",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("mdl_account_category")
    private List<Mdl_Account> o_categoryaccounts;

}
