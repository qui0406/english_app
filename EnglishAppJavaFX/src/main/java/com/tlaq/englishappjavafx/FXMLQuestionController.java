/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.tlaq.englishappjavafx;

import com.tlaq.pojo.Category;
import com.tlaq.pojo.Choice;
import com.tlaq.pojo.Question;
import com.tlaq.services.CategoryServices;
import com.tlaq.services.QuestionServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
    
    @FXML
    RadioButton rdoA;
    @FXML
    RadioButton rdoB;

    @FXML
    RadioButton rdoC;
    @FXML
    RadioButton rdoD;
    
    @FXML TextField txtA;
    @FXML TextField txtB;
    @FXML TextField txtC;
    @FXML TextField txtD;

    @FXML TextArea txtContent;
    @FXML Button btnAdd;

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
    
    public void addHandler(Event event) throws SQLException{
        Question q = new Question(UUID.randomUUID().toString(), this.txtContent.getText(), 
                this.cbBox.getSelectionModel().getSelectedItem().getId());
        
        Choice c1 = new Choice(UUID.randomUUID().toString(), txtA.getText(), rdoA.isSelected(), q.getId());
        Choice c2 = new Choice(UUID.randomUUID().toString(), txtB.getText(), rdoB.isSelected(), q.getId());
        Choice c3 = new Choice(UUID.randomUUID().toString(), txtC.getText(), rdoC.isSelected(), q.getId());
        Choice c4 = new Choice(UUID.randomUUID().toString(), txtD.getText(), rdoD.isSelected(), q.getId());

        List<Choice> choices = new ArrayList<>();
        choices.add(c1);
        choices.add(c2);
        choices.add(c3);
        choices.add(c4);
        
        QuestionServices s = new QuestionServices();
        s.addQuestion(q, choices);
        Utils.getAlert("Add success").show();
        loadTableData("");
    }
}
