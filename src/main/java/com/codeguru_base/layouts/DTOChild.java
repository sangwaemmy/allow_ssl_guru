/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codeguru_base.layouts;

import com.codeguru_base.models.Mdl_unit;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter

public class DTOChild {
 
    private long id;
     
    private long unitChildId;

    private Mdl_unit unitParentId;
    private String name;
    
    public DTOChild(long unitChildId, Mdl_unit unitParentId) {
        this.unitChildId = unitChildId;
        this.unitParentId = unitParentId;
    }

    public DTOChild(long id, long unitChildId, String name) {
        this.id = id;
        this.unitChildId = unitChildId;
        this.name = name;
    }


    
    
}
