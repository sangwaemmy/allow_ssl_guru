/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnitsTuplesNoIdNoNameDTO {

    public int number_children;
    public int number_parent;
    //the structure name
    public String structure_name;

    //tuple
    public long tupleId;
    public String tuple_name;
    public String data_type;
    public String category;
    public String display_caption;
    public String today_date;
    public String curr_date;

    public UnitsTuplesNoIdNoNameDTO(String structure_name, long tupleId, String tuple_name, String data_type, String category, String display_caption, String today_date, String curr_date) {
        this.structure_name = structure_name;
        this.tupleId = tupleId;
        this.tuple_name = tuple_name;
        this.data_type = data_type;
        this.category = category;
        this.display_caption = display_caption;
        this.today_date = today_date;
        this.curr_date = curr_date;
    }
    
    
    
    
}
