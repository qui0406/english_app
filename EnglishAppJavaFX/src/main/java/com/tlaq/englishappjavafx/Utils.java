/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlaq.englishappjavafx;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author QUI
 */
public class Utils {
    public static Alert getAlert(String content){
        return new Alert(Alert.AlertType.INFORMATION, content, ButtonType.OK);
    }
}
