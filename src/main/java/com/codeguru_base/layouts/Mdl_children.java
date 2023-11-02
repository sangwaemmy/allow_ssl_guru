/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.layouts;

import com.codeguru_base.models.Mdl_unit;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@Entity
@Table(name = "child")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Mdl_children implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    
    
    @Column(name = "unit_child_id", nullable = false)
    private long unitChildId;

    public Mdl_children(long unitChildId, Mdl_unit unitParentId) {
        this.unitChildId = unitChildId;
        this.unitParentId = unitParentId;
    }
    @ManyToOne
    @JoinColumn(name = "unit_parent_id")
    private Mdl_unit unitParentId;

}
