/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlaq.services;

import com.tlaq.pojo.Choice;
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
 * @author QUI
 */
public class QuestionServices {
    public List<Question> getQuestions(int num, String kw) throws SQLException{
        List<Question> questions= new ArrayList<>();
        try (Connection conn= JdbcUtils.getConn()){
            PreparedStatement stm;
            if (num==0){
                stm= conn.prepareCall("select * from question where content like concat('%', ? , '%') order by id desc");
                stm.setString(1, kw);
                
            }else {
                stm= conn.prepareCall("select * from question order by rand() limit");
                stm.setInt(1, num);
            }
            
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Question q= new Question(rs.getString("id"),  rs.getString("content"), rs.getInt("category_id"));
                questions.add(q);
            }
            return questions;
        }
    }
    
    public List<Choice> getChoices(String questionId) throws SQLException {
        List<Choice> choices = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareCall("SELECT * FROM choice WHERE question_id=?");
            stm.setString(1, questionId);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Choice c = new Choice(rs.getString("id"), rs.getString("content"), rs.getBoolean("is_correct"), rs.getString("question_id"));
                choices.add(c);
            }
        }
        
        return choices;
    }
}
