/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ProgramaController implements Initializable {

    @FXML
    private javafx.scene.control.TextField dni;
    @FXML
    private javafx.scene.control.TextField nom;
    @FXML
    private javafx.scene.control.TextField cognom;
    @FXML
    private javafx.scene.control.TextField mail;
    @FXML
    private javafx.scene.control.TextField direccio;
    @FXML
    private javafx.scene.control.TextField codipostal;
    @FXML
    private javafx.scene.control.TextField telefon;
    @FXML
    private javafx.scene.control.TextField cuota;
    @FXML
    private javafx.scene.control.TextField contrasenya;
    
    @FXML
    private javafx.scene.control.Button modificar;
    @FXML
    private javafx.scene.control.Button agregar;
    @FXML
    private javafx.scene.control.Button eliminar;
    
    @FXML
    private TableView<Persona> tblpersonas;
    @FXML
    private TableColumn coldni;
    @FXML
    private TableColumn colnom;
    @FXML
    private TableColumn colcognom;
    @FXML
    private TableColumn colmail;
    @FXML
    private TableColumn coldireccio;
    @FXML
    private TableColumn colcodipostal;
    @FXML
    private TableColumn coltelefon;
    @FXML
    private TableColumn colcuota;
    @FXML
    private TableColumn colcontrasenya;
    

    private ObservableList<Persona> personas;
    private ObservableList<Persona> filtroPersonas;

    @FXML
    private TextField filtrarnombre;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        personas = FXCollections.observableArrayList();
        filtroPersonas = FXCollections.observableArrayList();
        
        this.coldni.setCellValueFactory(new PropertyValueFactory("dni"));
        this.colnom.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colcognom.setCellValueFactory(new PropertyValueFactory("apellido"));
        this.colmail.setCellValueFactory(new PropertyValueFactory("mail"));
        this.coldireccio.setCellValueFactory(new PropertyValueFactory("direccion"));
        this.colcodipostal.setCellValueFactory(new PropertyValueFactory("codipostal"));
        this.coltelefon.setCellValueFactory(new PropertyValueFactory("telefon"));
        this.colcuota.setCellValueFactory(new PropertyValueFactory("cuota"));
        this.colcontrasenya.setCellValueFactory(new PropertyValueFactory("contrasenya"));
        
    }    

    @FXML
    public void agregar(javafx.event.ActionEvent event) {
        
        try {
        int dni = Integer.parseInt(this.dni.getText());
        String nom = this.nom.getText();
        String cognom = this.cognom.getText();
        String mail = this.mail.getText();
        String direccio = this.direccio.getText();
        int codipostal = Integer.parseInt(this.codipostal.getText());
        int telefon = Integer.parseInt(this.telefon.getText());
        int cuota = Integer.parseInt(this.cuota.getText());
        String contrasenya = this.contrasenya.getText();
        
        Persona p = new Persona (dni, nom, cognom, mail, direccio, codipostal, telefon, cuota, contrasenya);
        
        if(!this.personas.contains(p)){
            this.personas.add(p);
            this.tblpersonas.setItems(personas);
            
            this.dni.clear();
            this.nom.clear();
            this.cognom.clear();
            this.mail.clear();
            this.direccio.clear();
            this.codipostal.clear();
            this.telefon.clear();
            this.cuota.clear();
            this.contrasenya.clear();
            
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Has añadido una nueva persona");
            alert.showAndWait();
            
        }else{
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("La persona ya existe");
            alert.showAndWait();
            
            this.dni.clear();
            this.nom.clear();
            this.cognom.clear();
            this.mail.clear();
            this.direccio.clear();
            this.codipostal.clear();
            this.telefon.clear();
            this.cuota.clear();
            this.contrasenya.clear();
        }
        
        p.agregar();
    
        }catch (NumberFormatException e){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }
        
    }
    
    @FXML
    public void eliminar(javafx.event.ActionEvent event) {
        
        Persona p = this.tblpersonas.getSelectionModel().getSelectedItem();
        
        if(p == null){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Debe seleccionar alguna persona");
            alert.showAndWait();   
        }else{
            
            this.personas.remove(p);
            this.tblpersonas.refresh();
            
            this.dni.clear();
            this.nom.clear();
            this.cognom.clear();
            this.mail.clear();
            this.direccio.clear();
            this.codipostal.clear();
            this.telefon.clear();
            this.cuota.clear();
            this.contrasenya.clear();
            
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("INFORMACION");
            alert.setContentText("Has eliminado una persona");
            alert.showAndWait();   
        }
        
        p.eliminar();
    
    }
    
    @FXML
    public void modificar(javafx.event.ActionEvent event) {
        
         Persona p = this.tblpersonas.getSelectionModel().getSelectedItem();
        
        if(p == null){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Debe seleccionar alguna persona");
            alert.showAndWait();   
        }else{
            
            try {
                int dni = Integer.parseInt(this.dni.getText());
                String nom = this.nom.getText();
                String cognom = this.cognom.getText();
                String mail = this.mail.getText();
                String direccio = this.direccio.getText();
                int codipostal = Integer.parseInt(this.dni.getText());
                int telefon = Integer.parseInt(this.telefon.getText());
                int cuota = Integer.parseInt(this.cuota.getText());
                String contrasenya = this.contrasenya.getText();

                Persona aux = new Persona (dni, nom, cognom, mail, direccio, codipostal, telefon, cuota, contrasenya);

                if(!this.personas.contains(aux)){
                    
                    p.setDni(aux.getDni());
                    p.setNombre(aux.getNombre());
                    p.setApellido(aux.getApellido());
                    p.setMail(aux.getMail());
                    p.setDireccion(aux.getDireccion());
                    p.setCodipostal(aux.getCodipostal());
                    p.setTelefon(aux.getTelefon());
                    p.setCuota(aux.getCuota());
                    p.setContrasenya(aux.getContrasenya());
                    
                    this.tblpersonas.refresh();
                    
                    this.dni.clear();
                    this.nom.clear();
                    this.cognom.clear();
                    this.mail.clear();
                    this.direccio.clear();
                    this.codipostal.clear();
                    this.telefon.clear();
                    this.cuota.clear();
                    this.contrasenya.clear();
                    
                    Alert alert = new Alert (Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("INFORMACION");
                    alert.setContentText("Se ha modificado una persona");
                    alert.showAndWait();
                    
                    aux.modificar();
                    
                    
                }else{
                    Alert alert = new Alert (Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("ERROR");
                    alert.setContentText("La persona ya existe");
                    alert.showAndWait();
                }
    
            }catch (NumberFormatException e){
                Alert alert = new Alert (Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("ERROR");
                alert.setContentText("Formato incorrecto");
                alert.showAndWait();
            }
            
        }
    
    }
    
    @FXML
    private void seleccionar(MouseEvent event) {
        
        Persona p = this.tblpersonas.getSelectionModel().getSelectedItem();
        
        if(p != null){
            this.dni.setText(p.getDni() + "");
            this.nom.setText(p.getNombre());
            this.cognom.setText(p.getApellido());
            this.mail.setText(p.getMail());
            this.direccio.setText(p.getDireccion());
            this.codipostal.setText(p.getCodipostal() + "");
            this.telefon.setText(p.getTelefon() + "");
            this.cuota.setText(p.getCuota() + "");
            this.contrasenya.setText(p.getContrasenya());
            
        }
    }

    @FXML
    private void filtrarnombre(KeyEvent event) {
        
        String filtroNom = this.filtrarnombre.getText();
        
        if(filtroNom.isEmpty()){
            this.tblpersonas.setItems(personas);
        }else{
            this.filtroPersonas.clear();
            
            for (Persona p: this.personas) {
                if(p.getNombre().toLowerCase().contains(filtroNom.toLowerCase())){
                    this.filtroPersonas.add(p);
                }
            }
            this.tblpersonas.setItems(filtroPersonas);
        }
    }

    
    }

