/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Main.Principal;
import java.awt.TextArea;
import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Armario;
import ws.ClienteArmario;

/**
 *
 * @author juliano
 */
public class MainViewController implements Initializable {
      
     @FXML
     private AnchorPane PaneChamada;
     
     

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    @FXML
    public void HandleBtnCadArmario(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Principal.class.getResource("/view/CadArmario.fxml"));
        PaneChamada.getChildren().setAll(root);
    }
    @FXML
    public void HandleBtnVisualizar(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Principal.class.getResource("/view/Visualizar.fxml"));
        PaneChamada.getChildren().setAll(root);
    }
    @FXML
    public void HandleBtnEditarExcluir(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Principal.class.getResource("/view/EditarExcluir.fxml"));//
        PaneChamada.getChildren().setAll(root);
    }
}
