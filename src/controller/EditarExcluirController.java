
package controller;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.ws.rs.core.GenericType;
import model.Armario;
import ws.ClienteArmario;


public class EditarExcluirController implements Initializable {

@FXML
private TableView<Armario> ArmarioTabela;
@FXML
private TableColumn<Armario,Long> ColunaID;
@FXML
private TableColumn<Armario,Integer> ColunaNumero;
@FXML
private TableColumn<Armario,String> ColunaDisponibilidade;
@FXML
private TableColumn<Armario,String> ColunaLocalizacao;
@FXML
private TextField CampoID;
@FXML
private TextField CampoNumero;
@FXML
private TextField CampoDisponivel;
@FXML
private TextArea CampoLocalizacao;
@FXML
private Armario ArmarioSelecionado;


private ObservableList<Armario> observableListArmarios;
ClienteArmario armarioWS = new ClienteArmario();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.ArmarioSelecionado = new Armario();
        carregarArmarioTabela();
        this.ArmarioTabela.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->selecionarItensArmarioTabela(newValue));
    }

     public void carregarArmarioTabela(){
        this.ColunaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.ColunaNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        this.ColunaDisponibilidade.setCellValueFactory(new PropertyValueFactory<>("MostrarDisponivel"));
        this.ColunaLocalizacao.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
        
        List<Armario>listaArmarios=armarioWS.getArmarios(new GenericType<List<Armario>>(){});
        for(Armario a : listaArmarios){
            if(a.isEstaDisponivel()){
                a.setMostrarDisponivel("Disponivel");
            }else{
                a.setMostrarDisponivel("Ocupado");
            }
        }
            
        observableListArmarios = FXCollections.observableArrayList(listaArmarios);
        this.ArmarioTabela.setItems(observableListArmarios);
    }
    public void selecionarItensArmarioTabela(Armario armarioSelecionadoNow){
        if(armarioSelecionadoNow!=null){
            this.CampoID.setText(String.valueOf(armarioSelecionadoNow.getId()));
            this.CampoNumero.setText(String.valueOf(armarioSelecionadoNow.getNumero()));
            this.CampoDisponivel.setText(armarioSelecionadoNow.getMostrarDisponivel());
            String localizacao = armarioSelecionadoNow.getLocalizacao();
            this.CampoLocalizacao.setText(localizacao);
            this.setArmarioSelecionadoAtualizar(armarioSelecionadoNow);
        }else{
            this.CampoID.setText("");
            this.CampoNumero.setText("");
            this.CampoDisponivel.setText("");
            this.CampoLocalizacao.setText("");
        }
    }  

    public void setArmarioSelecionadoAtualizar(Armario armarioSelecionadoAtualizar) {
        this.ArmarioSelecionado = armarioSelecionadoAtualizar;
    }
    
        @FXML
        public void HandleBtnAtualizarArmario(ActionEvent event) throws IOException{
        
        if (ArmarioSelecionado != null) {

            if (!this.CampoID.getText().isEmpty() 
                    && !this.CampoNumero.getText().isEmpty() && !this.CampoDisponivel.getText().isEmpty()
                    && !this.CampoLocalizacao.getText().isEmpty()) {

                    Integer numeroArmario = Integer.parseInt(CampoNumero.getText());
                    if(ArmarioSelecionado.getNumero()!=numeroArmario){
                        this.ArmarioSelecionado.setNumero(numeroArmario);
                    }
                    if(!ArmarioSelecionado.getLocalizacao().equalsIgnoreCase(this.CampoLocalizacao.getText())){
                        this.ArmarioSelecionado.setLocalizacao(this.CampoLocalizacao.getText());
                    }
                    
                    Armario armarioAlterar = armarioWS.getArmario(Armario.class, this.CampoID.getText());
                    armarioAlterar.setNumero(ArmarioSelecionado.getNumero());
                    armarioAlterar.setLocalizacao(ArmarioSelecionado.getLocalizacao());
                    armarioWS.alterarArmario(armarioAlterar, this.CampoID.getText());

                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atualização");
                alert.setHeaderText("Armario Atualizado");
                alert.setContentText("O Armário de Número "+ this.CampoNumero.getText() +" foi atualizado com sucesso na base de dados!!!");
                alert.showAndWait();
                this.carregarArmarioTabela();
                this.CampoID.setText("");
                this.CampoNumero.setText("");
                this.CampoDisponivel.setText("");
                this.CampoLocalizacao.setText("");
                ArmarioSelecionado=null;
            }else{
                Alert alertVazio = new Alert(Alert.AlertType.ERROR);
                alertVazio.setHeaderText("Falha na Atualização");
                alertVazio.setContentText("Não pode haver campos em branco para a atualização!!!"); 
                alertVazio.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um armário na Tabela para atualizar!");
            alert.show();
        }
    }
   @FXML
        public void HandleAlertDelete(ActionEvent event) throws IOException{
        Armario armarioSelc = this.ArmarioTabela.getSelectionModel().getSelectedItem();
        if (armarioSelc != null) {
            boolean buttonConfirmarClicked = showFXMLAlertDeleteArmario(armarioSelc);
            if (buttonConfirmarClicked) {
                this.armarioWS.removerArmario(Armario.class, String.valueOf(armarioSelc.getId()));
                this.carregarArmarioTabela();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Armario Excluido");
                alert.setHeaderText("Exclusão de Armario");
                alert.setContentText("O armário de número "+armarioSelc.getNumero() +" foi excluido com sucesso da base de dados!!!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um armário na Tabela!");
            alert.show();
        }
    }

    public boolean showFXMLAlertDeleteArmario(Armario armario) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AlertDeleteArmarioController.class.getResource("/view/AlertDeleteArmario.fxml"));
        Parent page = (Parent) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Excluir Armario");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        AlertDeleteArmarioController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setArmarioSelecionado(armario);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmationClicked();
    } 
    
}
