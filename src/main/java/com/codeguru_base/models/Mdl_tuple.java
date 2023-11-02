/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.models;

import com.fasterxml.jackson.annotation.JsonInclude;
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
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@Entity
@Table(name = "tuple")
@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mdl_tuple implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Size(min = 1, max = 80, message = " name should not be empty, null and or lengt exceed 80")
    @Column(name = "name",  length = 80, nullable = false)
    private String name;
    @Size(min = 1, max = 18, message = " data_type should not be empty, null and or lengt exceed 30")
    @Column(name = "data_type", length = 30, nullable = false)
    private String data_type;
    @Size(min = 1, max = 18, message = " category should not be empty, null and or lengt exceed 30")
    @Column(name = "category", length = 30, nullable = false)
    private String category;
    @Size(min = 1, max = 18, message = " display_caption should not be empty, null and or lengt exceed 30")
    @Column(name = "display_caption", length = 30, nullable = false)
    private String display_caption;
    @Size(min = 1, max = 18, message = " today_date should not be empty, null and or lengt exceed 30")
    @Column(name = "today_date", length = 30, nullable = true)
    private String today_date;
    @Size(min = 1, max = 18, message = " curr_date should not be empty, null and or lengt exceed 30")
    @Column(name = "curr_date", length = 30, nullable = false)
    private String curr_date;

    public Mdl_tuple(String name, String data_type, String category, String display_caption, String today_date, String curr_date, Mdl_unit mdl_unit) {
        this.name = name;
        this.data_type = data_type;
        this.category = category;
        this.display_caption = display_caption;
        this.today_date = today_date;
        this.curr_date = curr_date;
        this.mdl_unit = mdl_unit;
    }
    @ManyToOne
    @JoinColumn(name = "unit_id")
    Mdl_unit mdl_unit;

}
