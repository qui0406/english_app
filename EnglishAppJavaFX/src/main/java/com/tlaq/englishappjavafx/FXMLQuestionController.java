/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.tlaq.englishappjavafx;

import com.tlaq.pojo.Category;
import com.tlaq.pojo.Question;
import com.tlaq.services.CategoryServices;
import com.tlaq.services.QuestionServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class FXMLQuestionController implements Initializable {
    @FXML ComboBox<Category> cbBox;
    @FXML TableView<Question> tbQuestions;
    @FXML TextField txtSearch;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CategoryServices s = new CategoryServices();
        
        try {
            this.cbBox.setItems(FXCollections.observableList(s.getCategories()));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.loadColumns();
        try {
            loadTableData("");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        txtSearch.textProperty().addListener(e -> {
            try {
                loadTableData(txtSearch.getText());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLQuestionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void loadColumns(){
        TableColumn colContent = new TableColumn("Nội dung câu hỏi");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(400);
        
        TableColumn colCates = new TableColumn("Danh mục");
        colCates.setCellValueFactory(new PropertyValueFactory("categoryId"));
        colCates.setPrefWidth(200);
        
        this.tbQuestions.getColumns().addAll(colContent, colCates);
    }
    
    public void loadTableData(String kw) throws SQLException{
        QuestionServices s = new QuestionServices();
        
        this.tbQuestions.setItems(FXCollections.observableList(s.getQuestions(0, kw)));
        
    }
}
