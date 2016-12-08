
package controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Armario;


public class AlertDeleteArmarioController implements Initializable {

   @FXML
   private Label nomeExclusaoLabel;
   @FXML
   private Button OpSim;
   @FXML
   private Button OpNao;
   
   private Stage dialogStage;
   private boolean buttonConfirmationClicked=false;
   private Armario armarioSelecionado;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmationClicked() {
        return buttonConfirmationClicked;
    }

    public void setButtonConfirmationClicked(boolean buttonConfirmationClicked) {
        this.buttonConfirmationClicked = buttonConfirmationClicked;
    }

    public Armario getArmarioSelecionado() {
        return armarioSelecionado;
    }

    public void setArmarioSelecionado(Armario armarioSelecionado) {
        this.armarioSelecionado = armarioSelecionado;
        this.nomeExclusaoLabel.setText(String.valueOf(this.armarioSelecionado.getNumero()));
    }
   
   
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    } 
    public void HandleOpSimDeletar(ActionEvent event) throws IOException {
        this.buttonConfirmationClicked=true;
        this.dialogStage.close();
     }
    public void HandleOpNaoDeletar(ActionEvent event) throws IOException {
        dialogStage.close();
    }
}
