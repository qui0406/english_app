/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlaq.main;

import com.tlaq.pojo.Choice;
import com.tlaq.services.QuestionServices;

/**
 *
 * @author QUI
 */
public class main {
    public static void main(String[] args) {
        Choice c = new Choice();
        QuestionServices s= new QuestionServices();
        System.out.println(c.getId());
    }
}
