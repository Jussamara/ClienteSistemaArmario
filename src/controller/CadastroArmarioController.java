
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
            }
        }


    
}
