/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.models.DTO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UnitsTuplesDTO extends UnitsTuplesNoIdNoNameDTO implements Serializable{

    private long id;
    private String name;

    public UnitsTuplesDTO(long id, String name, String structure_name, long tupleId, String tuple_name, String data_type, String category, String display_caption, String today_date, String curr_date) {
        this.id = id;
        this.name = name;
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
