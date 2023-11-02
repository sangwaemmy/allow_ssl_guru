/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.models;

import com.codeguru_base.layouts.Mdl_Layout;
import com.codeguru_base.layouts.Mdl_children;
import com.codeguru_base.layouts.Mdl_parents;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@Entity
@Table(name = "unit")
@Setter
@Getter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mdl_unit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Size(min = 1, max = 88, message = " name should not be empty, null and or lengt exceed 30")
    @Column(name = "name", length = 80, nullable = false)
    private String name;
    
    @Size(min = 1, max = 18, message = " number_children should not be empty, null and or lengt exceed 30")
    @Column(name = "number_children",  length = 30, nullable = true)
    private int number_children;
    
    @Size(min = 1, max = 18, message = " number_parent should not be empty, null and or lengt exceed 30")
    @Column(name = "number_parent", length = 30, nullable = true)
    private int number_parent;

    public Mdl_unit(String name, int number_children, int number_parent, Mdl_structure mdl_structure) {
        this.name = name;
        this.number_children = number_children;
        this.number_parent = number_parent;
        this.mdl_structure = mdl_structure;
    }
    @ManyToOne
    @JoinColumn(name = "structure_id", nullable = false)
    Mdl_structure mdl_structure;
    
    
    @ManyToOne
    @JoinColumn(name = "layout_id", nullable = true)
    Mdl_Layout mdl_layout;

    @OneToMany(mappedBy = "mdl_unit", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("mdl_unit")
    private List<Mdl_tuple> o_tuples;

    
    //this is found in parent Mld_unit where me am a child, so we make this an FK
    @OneToMany(mappedBy = "unitChildId", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Mdl_parents> o_childsunits;
    
    
    //this is found in the child unit where me (Mdl_unit ) and a parent, so we make this an FK
    @OneToMany(mappedBy = "unitParentId", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Mdl_children> o_parentsunits;
    
    
    
     
   

    
    
    
}
