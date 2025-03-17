/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlaq.services;

import com.tlaq.pojo.Category;
import com.tlaq.pojo.JdbcUtils;
import com.tlaq.pojo.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class CategoryServices {
     public List<Category> getCategories() throws SQLException{
        List<Category> cates= new ArrayList<>();
        try (Connection conn= JdbcUtils.getConn()){
            PreparedStatement stm = 
                    conn.prepareCall("select * from category");
          
            
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Category c = new Category(rs.getInt("id"), rs.getString("name"));
                cates.add(c);
            }
            return cates;
        }
    }
}
