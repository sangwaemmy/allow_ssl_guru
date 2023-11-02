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
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@Entity
@Table(name = "parent")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Mdl_parents implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
 
    @Column(name = "unit_parent_id", nullable = false)
    private long unitParentId;

    
    public Mdl_parents(long unitParentId, Mdl_unit unitChildId) {
        this.unitParentId = unitParentId;
        this.unitChildId = unitChildId;
    }
    
    // me= child , others are parents
    @ManyToOne
    @JoinColumn(name = "unit_child_id")
    private Mdl_unit unitChildId;

}
