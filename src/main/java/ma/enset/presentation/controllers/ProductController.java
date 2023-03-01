package ma.enset.presentation.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleAction;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import ma.enset.dao.CategoryHandler.CategoryDao;
import ma.enset.dao.CategoryHandler.CategoryDaoImp;
import ma.enset.dao.ProductHandler.ProductDao;
import ma.enset.dao.ProductHandler.ProductDaoImp;
import ma.enset.dao.entites.Category;
import ma.enset.dao.entites.Product;
import ma.enset.metier.OperationCatalog;
import ma.enset.metier.OperationCatalogImp;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProductController implements Initializable{
    OperationCatalog op  ;
    @FXML
    private ComboBox comboCat ;
    @FXML
    private TextField txtRechercher ;
    @FXML
    private TextField txtDesignation;
    @FXML
    private TextField txtRef;
    @FXML
    private TextField txtPrix;
    @FXML
    private Button addBtn ;
    @FXML
    private Button updateBtn ;
    @FXML
    private Button deleteBtn ;
    @FXML
    private  TableView<Product> tableView;
    @FXML private TableColumn<Integer,Product> idProduct;
    @FXML private TableColumn<String,Product> designation;
    @FXML private TableColumn<String,Product> reference;
    @FXML private TableColumn<Float,Product> prix;
    @FXML private TableColumn<Category,Product> categorie;

    private ObservableList<Product> data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        op= new OperationCatalogImp(new CategoryDaoImp(), new ProductDaoImp());
        comboCat.getItems().addAll(op.findAllCategory());
        idProduct.setCellValueFactory((new PropertyValueFactory<>("id")));
        designation.setCellValueFactory((new PropertyValueFactory<>("designation")));
        reference.setCellValueFactory((new PropertyValueFactory<>("reference")));
        prix.setCellValueFactory((new PropertyValueFactory<>("prix")));
        categorie.setCellValueFactory((new PropertyValueFactory<>("cat")));

        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try{
                    Product p = (Product)tableView.getSelectionModel().getSelectedItem();
                    txtDesignation.setText(p.getDesignation());
                    txtRef.setText(p.getReference());
                    txtPrix.setText(String.valueOf(p.getPrix()));
                    comboCat.getSelectionModel().select((Category)p.getCat());
                }catch(Exception ex){
                    System.out.println(ex);
                }

            }
        });
        txtRechercher.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                List<Product> listp =op.findByQueryProduct(txtRechercher.getText());
                data.clear();
                data.addAll(listp);
                tableView.setItems(data);
            }
        });
        loadAll();
        tableView.setItems(data);
    }
    public void searchByTyping(){

    }

    public void updateBtn(){
        Product p = (Product)tableView.getSelectionModel().getSelectedItem();;
        p.setDesignation(txtDesignation.getText());
        p.setReference(txtRef.getText());
        p.setPrix(Float.parseFloat(txtPrix.getText()));
        p.setCat((Category)comboCat.getSelectionModel().getSelectedItem());
        Alert alert = customAlert("Confirme update");
        try{

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                op.updateProduct(p);
                viderText();
                loadAll();
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }


    }

    private void loadAll(){
        data.clear();
        data.addAll(op.findAllProduct());

    }
    public void addBtn(){
        if(txtDesignation.getText().trim().equals("")
                || txtRef.getText().trim().equals("")
                || txtPrix.getText().trim().equals("")||
                comboCat.getSelectionModel().getSelectedIndex()==-1){
            dioalogBox("Warning" , "Entrer les Champs");

        }else{
            Float parsed=Float.parseFloat(txtPrix.getText());
            Product p = new Product();
            p.setDesignation(txtDesignation.getText());
            p.setReference(txtRef.getText());
            p.setPrix(parsed);
            p.setCat((Category)comboCat.getSelectionModel().getSelectedItem());
            op.saveProduct(p);
            loadAll();
            viderText();
        }

    }
    public void delBtn(){
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        if(selectedIndex== -1){
            if(tableView.getItems().isEmpty()){
                dioalogBox("Dialog","La list est vide impossible de selectioner un element");
            }else {

                dioalogBox("Dialog","Selectionner un element");
            }

        }else {
            Alert alert = customAlert("Confirme Delete");
            try{
                Product p =(Product)tableView.getSelectionModel().getSelectedItem();
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    op.deleteProduct(p);
                    loadAll();
                    viderText();
                }
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }


        }
    }
    private void dioalogBox(String dialogTitle , String dialogTest){
        Dialog<String> dialog = new Dialog();
        //Setting the title
        dialog.setTitle(dialogTitle);
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setContentText(dialogTest);
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.show();
    }

    private Alert customAlert(String btnTypeText){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText(btnTypeText);
        return alert ;
    }
    private void viderText(){
        txtDesignation.setText("");
        txtRef.setText("");
        txtPrix.setText("");
        comboCat.getSelectionModel().clearSelection();
    }
}
