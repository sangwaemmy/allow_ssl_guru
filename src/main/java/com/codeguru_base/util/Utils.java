/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@Getter
@Setter
public class Utils {

    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String currentDateTime = dateFormatter.format(new Date());

    DateFormat dateFormatter2 = new SimpleDateFormat("yyyy-MM-dd");
    String currentDate = dateFormatter.format(new Date());

    public static String get_CapRemoveUnderscore(String field) {
        String stringOne = field.substring(0, 1).toUpperCase() + field.substring(1);
        String parts[] = stringOne.split("_");
        String newStringCaps = "";
        for (String part : parts) {
            String inString = part
                    .substring(0, 1).toUpperCase() + part.substring(1)
                    .replace("_", " ").replace("-", " ");
            newStringCaps += inString + " ";
        }
        System.out.println(newStringCaps);
        return newStringCaps;

    }

    public void get_writer(String path_file, String content) {
        PrintWriter prnt;
        try {
            prnt = new PrintWriter(new BufferedWriter(new FileWriter(path_file)));
            prnt.println(content);
            prnt.close();
        } catch (IOException ex) {
        }
    }

    public String get_Capitalized(String field) {
        return field.substring(0, 1).toUpperCase() + field.substring(1);
    }

    public String get_Lowecase(String field) {
        return field.substring(0, 1).toLowerCase() + field.substring(1);
    }

    public static String get_uppercase(String field) {
        return field.toUpperCase();
    }

    public String get_short_int(String field) {
        return ("Integer".equals(field) | "int".equals(field)) ? "int" : "String";
    }

    public String get_pk(String field) {
        return ("pk".equals(field)) ? field + "_id" : field;
    }

}
