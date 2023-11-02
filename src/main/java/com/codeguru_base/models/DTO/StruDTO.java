/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.models.DTO;

import com.codeguru_base.models.Mdl_unit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@Data
public class StruDTO   implements Serializable{
    private long id;
    private String db_name;
    private String db_user;
    private String db_password;
    private int cash_total;
    private String start_time;
    private String delivery_date;
    private String pgm_language;
    private String platform;
    private String entry_date;
    private String User;

}
