
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.ws.rs.core.GenericType;
import model.Armario;
import ws.ClienteArmario;


public class VisualizarController implements Initializable{
    
    @FXML
    private TableView<Armario> tabelaArmarios;
    @FXML
    private TableColumn<Armario,Long> idArmario;
    @FXML
    private TableColumn<Armario,Integer> NumArmario;
    @FXML
    private TableColumn<Armario,String> Disponibilidade;
    @FXML
    private TableColumn<Armario,String> Localizacao;

    
    private ObservableList<Armario> observableListArmarios;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableArmario();
    }  
    
        public void carregarTableArmario(){
        ClienteArmario armarioWS = new ClienteArmario();
        
        List<Armario>listaArmarios=armarioWS.getArmarios(new GenericType<ArrayList<Armario>>(){});
        
        for(Armario a : listaArmarios){
            if(a.isEstaDisponivel()){
                a.setMostrarDisponivel("Disponivel");
            }else{
                a.setMostrarDisponivel("Ocupado");
            }
        }    
            
            
        this.idArmario.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.NumArmario.setCellValueFactory(new PropertyValueFactory<>("numero"));
        this.Disponibilidade.setCellValueFactory(new PropertyValueFactory<>("MostrarDisponivel"));
        this.Localizacao.setCellValueFactory(new PropertyValueFactory<>("localizacao"));

        this.idArmario.setStyle("-fx-alignment: CENTER;");
        this.NumArmario.setStyle("-fx-alignment: CENTER;");
        this.Disponibilidade.setStyle("-fx-alignment: CENTER;");
        this.Localizacao.setStyle("-fx-alignment: CENTER;");
            
        observableListArmarios = FXCollections.observableArrayList(listaArmarios);
        this.tabelaArmarios.setItems(observableListArmarios);
    }
    
}
