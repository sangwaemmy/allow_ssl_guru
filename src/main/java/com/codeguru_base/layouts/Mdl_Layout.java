/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.layouts;

import com.codeguru_base.models.Mdl_unit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@Entity
@Table(name = "layout")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Mdl_Layout implements Serializable {
              
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1, max = 80, message = " layout should not be empty, null and or lengt exceed 18")
    @Column(name = "name", nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "mdl_layout", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Mdl_unit> o_layoutUnits;

}
