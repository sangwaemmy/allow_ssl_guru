/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.layouts;

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
public class DTOUnitsTuples {

    //unit
    private String name;
    private int number_children;
    private int number_parent;

    //tuple
    private String tuple_name;
    private String data_type;
    private String category;
    private String display_caption;
    private String today_date;
    
    
}
