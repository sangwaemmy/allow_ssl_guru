/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.layouts;

import com.codeguru_base.models.Mdl_unit;
import javax.persistence.Column;
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
@Getter
@Setter
public class DTOParent {

    private long id;
    private long unitParentId;
    private Mdl_unit unitChildId;
    private String name;

    public DTOParent(long id, long unitParentId, String name) {
        this.id = id;
        this.unitParentId = unitParentId;
        this.name = name;
    }

    
    
}
