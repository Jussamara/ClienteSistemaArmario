
package controller;

import Main.Principal;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Armario;
import ws.ClienteArmario;


public class CadastroArmarioController implements Initializable {
    private ClienteArmario armarioWS;
    @FXML
    private TextField NumArmario;
    @FXML
    private TextArea LocalicacaoArmario;
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       armarioWS = new ClienteArmario();
    } 
    
        @FXML
        public void handleBtnCadastrarArmario(ActionEvent event) throws IOException{
            String numeroArmario = NumArmario.getText();
            String localicacao = LocalicacaoArmario.getText();
            if(!numeroArmario.isEmpty() && !localicacao.isEmpty()){
                Armario a = new Armario();
                a.setNumero(Integer.parseInt(numeroArmario));
                a.setLocalizacao(localicacao);
                a.setEstaDisponivel(true);
                armarioWS.adicionarArmario(a);
                NumArmario.setText("");
                LocalicacaoArmario.setText("");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cadastrado");
                alert.setHeaderText("Armário Cadastrado");
                alert.setContentText("O armário foi cadastrado com sucesso na base de dados!!!");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setHeaderText("Campos em Branco");
                alert.setContentText("Não podem haver campos em branco no cadastramento de armário!!!");
                alert.showAndWait();
            }
        }
        @FXML
        public void handleBtnCancelar(ActionEvent event) throws IOException{
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(CadastroArmarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }    
}
