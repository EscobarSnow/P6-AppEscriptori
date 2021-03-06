package Applicacio;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.mysql.cj.jdbc.Driver;


import Classes.CFamiliars;
import Classes.CFederats;
import Classes.CursLlogat;
import Classes.Cursos;
import Classes.ExcepcioFederacio;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


// Cambieu nom�s la part de la taula/table, lo altre mantenir per tenir-ho igual
	public class App extends Application {
    Scene miEscena, miEscenaLloguer, miEscenaProcedimentLloguer;
    Pane containerLloguer, containerProcedimentLloguer;
    Text titol, titolLloguer;
    Image logo;
    ImageView vistaLogo;
    // TableView i TableColumn cambiar-ho amb la vostra apartat
    TableView taula;
    TableColumn idCurs, curs, categoria, dni, dataLloguer, preuTotal, hores;

    Button btnCursos, btnCursosLlogats, btnSortir, btnLlogarCurs;
    DropShadow sombra;
    Lighting illuminacio;

    // Labels lloguer
    TextField txtIdCurs, txtHora, txtPreu, txtPreuTotal, txtDNI, txtParticipants, txtData;

    TextField txtFamNombr = null;
    TextField txtFederacio = null;

    Label lblIdCurs, lblHora, lblPreu, lblPreuTotal, lblDNI, lblParticipants, lblFamNombr,
            lblFederacio, lblData;

    // Checkbox
    CheckBox chkFamNombr, chkFederacio;
    Button btnConfirmar, btnCancelar, btnCalcularPreu, btnAplicarDescompte, btnValidarFederacio;

    Alert alertaVerificacio;

    CursLlogat cursLlogat;
    CFamiliars cursFam;
    CFederats cursFed;
    
    ObservableList<Cursos> list = FXCollections.observableArrayList();
    ObservableList<CursLlogat> cursLlogatAL = FXCollections.observableArrayList();
    ArrayList<CFamiliars> cursFamiliar = new ArrayList<CFamiliars>();
    ArrayList<CFederats> cursFederat = new ArrayList<CFederats>();
    
        
    public static void main(String[] args) {
        
        GuardarArray.Cursos();
        Application.launch(args);

    }

    @Override
    public void start(Stage miEscenario) throws Exception {


        // Nom de l'empresa / header
        titol = new Text();
        titol.setText("EscobarSnow");
        titol.setTranslateX(150);
        titol.setTranslateY(80);
        // titol.setStyle(" -fx-text-inner-color: #FFFFFF");
        titol.setFill(Color.web("#FFFFFF"));

        // Estils nom empresa
        titol.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));

        // Logotip de l'empresa 
        // cambiar cada vegada
        logo = new Image(new FileInputStream("D:\\Work\\EclipseWorkspace\\EscobarSnow\\src\\Imatges\\logo.png"));
        vistaLogo = new ImageView(logo);
        vistaLogo.setTranslateX(50);
        vistaLogo.setTranslateY(20);
        vistaLogo.setFitHeight(85);
        vistaLogo.setFitWidth(85);

        // Configuraci� de la proporci� de conservaci� de la visualitzaci� de la imatge
        vistaLogo.setPreserveRatio(true);

        // Taula de lloguer *****Cambiar al vostre*****
        TableColumn<Cursos, String> idCursColumn = new TableColumn<>("idCurs");
        idCursColumn.setMinWidth(100);
        idCursColumn.setCellValueFactory(new PropertyValueFactory<>("idCurs"));
            
        //Curs column
        TableColumn<Cursos, String> cursColumn = new TableColumn<>("curs");
        cursColumn.setMinWidth(100);
        cursColumn.setCellValueFactory(new PropertyValueFactory<>("curs"));
        
        //Categoria column
        TableColumn<Cursos, String> categoriaColumn = new TableColumn<>("categoria");
        categoriaColumn.setMinWidth(100);
        categoriaColumn.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        
        //Hora column
        TableColumn<Cursos, String> horaColumn = new TableColumn<>("durada");
        horaColumn.setMinWidth(100);
        horaColumn.setCellValueFactory(new PropertyValueFactory<>("durada"));
        
        //Preu column
        TableColumn<Cursos, String> preuColumn = new TableColumn<>("preu");
        preuColumn.setMinWidth(100);
        preuColumn.setCellValueFactory(new PropertyValueFactory<>("preu"));

        taula = new TableView<>();
        taula.setItems(getCursos());
        taula.getColumns().addAll(idCursColumn, cursColumn, categoriaColumn, horaColumn, preuColumn);
        
        taula.setMinWidth(700);
        taula.setMinHeight(500);
        taula.setTranslateX(50);
        taula.setTranslateY(125);


        // Botons navegadors
        btnCursos = new Button("Visualitzar cursos");
        btnCursos.setMinWidth(100);
        btnCursos.setTranslateX(800);
        btnCursos.setTranslateY(150);

        btnCursosLlogats = new Button("Visualitzar cursos llogats");
        btnCursosLlogats.setMinWidth(100);
        btnCursosLlogats.setTranslateX(800);
        btnCursosLlogats.setTranslateY(250);
        btnCursosLlogats.setOnAction(e -> miEscenario.setScene(miEscenaLloguer));

        btnLlogarCurs = new Button("Llogar curs");
        btnLlogarCurs.setMinWidth(100);
        btnLlogarCurs.setTranslateX(800);
        btnLlogarCurs.setTranslateY(200);

        // Event del bot� del procediment de lloguer
        btnLlogarCurs.setOnAction(e -> miEscenario.setScene(miEscenaProcedimentLloguer));

        btnSortir = new Button("Sortir");
        btnSortir.setMinWidth(100);
        btnSortir.setTranslateX(850);
        btnSortir.setTranslateY(600);

        // Event del bot� sortir
        btnSortir.setOnAction(e -> 
        {
            inserirBD();
            inserirBDOO();
            Platform.exit();
        }
        );

        estilsBotons();

        containerLloguer = new Pane();
        containerLloguer.getChildren().addAll(titol, vistaLogo, taula, btnCursos, btnCursosLlogats,
                btnLlogarCurs, btnSortir);
        containerLloguer.setMinSize(1000, 700);
        containerLloguer.setStyle(" -fx-base: #152348");

        miEscena = new Scene(containerLloguer);

        miEscenario.setTitle("Llogar cursos");
        miEscenario.setMinHeight(350);
        miEscenario.setMinWidth(250);

        // Donem el parametre del stage al m�tode que volem cridar
        pantallaCursosLlogats(miEscenario);
        pantallaLloguer(miEscenario);

        miEscenario.setScene(miEscena);
        miEscenario.show();

    }
    
    public void pantallaLloguer(Stage miEscenario) throws Exception{
        // Pantalla del procediment de llogar cursos
        // Titol de la pantalla del procediment de llogar cursos
        titolLloguer = new Text();
        titolLloguer.setText("LLOGUER DE CURS");
        titolLloguer.setTranslateX(10);
        titolLloguer.setTranslateY(80);
        titolLloguer.setFill(Color.web("#FFFFFF"));
        titolLloguer.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

        // Label amb les dades
        // Dades cursos
        lblIdCurs = new Label("ID Curs");
        lblIdCurs.setTranslateX(20);
        lblIdCurs.setTranslateY(100);

        txtIdCurs = new TextField();
        txtIdCurs.setTranslateX(90);
        txtIdCurs.setTranslateY(100);
        
        lblHora = new Label("Hores");
        lblHora.setTranslateX(20);
        lblHora.setTranslateY(150);

        txtHora = new TextField();
        txtHora.setTranslateX(90);
        txtHora.setTranslateY(150);

        lblPreu = new Label("Preu");
        lblPreu.setTranslateX(20);
        lblPreu.setTranslateY(300);

        txtPreu = new TextField();
        txtPreu.setTranslateX(90);
        txtPreu.setTranslateY(300);

        // Bot� per calcular el preu total
        btnCalcularPreu = new Button("Calcular Preu");
        btnCalcularPreu.setMinWidth(100);
        btnCalcularPreu.setTranslateX(250);
        btnCalcularPreu.setTranslateY(275);

        btnCalcularPreu.setOnAction(e -> calcularPreuTotal());

        lblPreuTotal = new Label("Preu total");
        lblPreuTotal.setTranslateX(370);
        lblPreuTotal.setTranslateY(255);

        txtPreuTotal = new TextField();
        txtPreuTotal.setTranslateX(370);
        txtPreuTotal.setTranslateY(275);
        txtPreuTotal.setEditable(false);

        // Checkbox de opcions
        chkFamNombr = new CheckBox("Familia nombrosa");
        chkFamNombr.setTranslateX(20);
        chkFamNombr.setTranslateY(350);
        chkFamNombr.setOnAction(e -> {
            txtFederacio.disableProperty().bind(chkFamNombr.selectedProperty());
            btnAplicarDescompte.setVisible(true);
            txtFamNombr.setEditable(true);
        }
        );
        
        chkFederacio = new CheckBox("N� federaci�");
        chkFederacio.setTranslateX(20);
        chkFederacio.setTranslateY(370);
        chkFederacio.setOnAction(e -> {
            txtFamNombr.disableProperty().bind(chkFederacio.selectedProperty());
            btnValidarFederacio.setVisible(true);
            txtFederacio.setEditable(true);
        }
        );

        // Dades clients
        lblDNI = new Label("DNI");
        lblDNI.setTranslateX(20);
        lblDNI.setTranslateY(200);

        txtDNI = new TextField();
        txtDNI.setTranslateX(90);
        txtDNI.setTranslateY(200);

        lblParticipants = new Label("Participants");
        lblParticipants.setTranslateX(20);
        lblParticipants.setTranslateY(250);

        txtParticipants = new TextField();
        txtParticipants.setTranslateX(90);
        txtParticipants.setTranslateY(250);

        lblFamNombr = new Label("N� familia nombrosa");
        lblFamNombr.setTranslateX(300);
        lblFamNombr.setTranslateY(100);

        txtFamNombr = new TextField();
        txtFamNombr.setTranslateX(370);
        txtFamNombr.setTranslateY(100);
        txtFamNombr.setEditable(false);
        
        // Bot� per aplicar descompte
        btnAplicarDescompte = new Button("Aplicar descompte");
        btnAplicarDescompte.setMinWidth(100);
        btnAplicarDescompte.setTranslateX(530);
        btnAplicarDescompte.setTranslateY(100);
        btnAplicarDescompte.setVisible(false);
        btnAplicarDescompte.setOnAction(e -> aplicarDescompte());

        lblFederacio = new Label("N� Federaci�");
        lblFederacio.setTranslateX(300);
        lblFederacio.setTranslateY(150);

        txtFederacio = new TextField();
        txtFederacio.setTranslateX(370);
        txtFederacio.setTranslateY(150);
        txtFederacio.setEditable(false);

        // Bot� per validar la federacio

        btnValidarFederacio = new Button("Validar federaci�");
        btnValidarFederacio.setMinWidth(100);
        btnValidarFederacio.setTranslateX(530);
        btnValidarFederacio.setTranslateY(150);
        btnValidarFederacio.setVisible(false);
        btnValidarFederacio.setOnAction(e -> validarFederacio());

        lblData = new Label("Data");
        lblData.setTranslateX(300);
        lblData.setTranslateY(200);

        txtData = new TextField();
        txtData.setTranslateX(370);
        txtData.setTranslateY(200);

        // Botons Confirmaci� i cancel�laci�
        btnConfirmar = new Button("Confirmar Lloguer");
        btnConfirmar.setMinWidth(100);
        btnConfirmar.setTranslateX(130);
        btnConfirmar.setTranslateY(400);

        btnConfirmar.setOnAction(e -> inserirArray());
        
        btnCancelar = new Button("Cancelar");
        btnCancelar.setMinWidth(100);
        btnCancelar.setTranslateX(20);
        btnCancelar.setTranslateY(400);
        btnCancelar.setOnAction(e -> 
        {

            txtIdCurs.clear();
            txtIdCurs.clear();
            txtHora.clear(); 
            txtPreu.clear();
            txtPreuTotal.clear(); 
            txtDNI.clear(); 
            txtParticipants.clear(); 
            txtFamNombr.clear();
            txtFederacio.clear(); 
            txtData.clear();

            miEscenario.setScene(miEscena);
            miEscenario.show();
        }
        );
        
        estilsBotonsLloguer();

        containerProcedimentLloguer = new Pane();
        containerProcedimentLloguer.getChildren().addAll(titolLloguer, lblIdCurs, txtIdCurs, lblHora, txtHora, lblPreu, txtPreu, lblPreuTotal, txtPreuTotal, lblDNI,
                txtDNI, lblParticipants, txtParticipants, lblFamNombr, txtFamNombr, lblFederacio, txtFederacio, lblData,
                txtData, chkFamNombr, chkFederacio, btnCalcularPreu, btnAplicarDescompte, btnValidarFederacio, btnConfirmar, btnCancelar);
        containerProcedimentLloguer.setMinSize(650, 550);
        containerProcedimentLloguer.setStyle(" -fx-base: #152348");

        miEscenaProcedimentLloguer = new Scene(containerProcedimentLloguer);
    }

    public void pantallaCursosLlogats(Stage miEscenario) throws Exception {

    	 titol = new Text();
         titol.setText("EscobarSnow");
         titol.setTranslateX(150);
         titol.setTranslateY(80);
         titol.setStyle(" -fx-text-inner-color: #FFFFFF");
         titol.setFill(Color.web("#FFFFFF"));

         // Estils nom empresa
         titol.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));

         // Logotip de l'empresa 
         // cambiar cada vegada
         logo = new Image(new FileInputStream("D:\\Work\\EclipseWorkspace\\EscobarSnow\\src\\Imatges\\logo.png"));
         vistaLogo = new ImageView(logo);
         vistaLogo.setTranslateX(50);
         vistaLogo.setTranslateY(20);
         vistaLogo.setFitHeight(85);
         vistaLogo.setFitWidth(85);

         // Configuraci� de la proporci� de conservaci� de la visualitzaci� de la imatge
         vistaLogo.setPreserveRatio(true);

                 
         TableColumn<CursLlogat, String> idCursColumn = new TableColumn<>("Id Curs");
         idCursColumn.setMinWidth(100);
         idCursColumn.setCellValueFactory(new PropertyValueFactory<>("idCurs"));
         
         TableColumn<CursLlogat, String> dniColumn = new TableColumn<>("DNI");
         dniColumn.setMinWidth(100);
         dniColumn.setCellValueFactory(new PropertyValueFactory<>("DNI"));
         
         TableColumn<CursLlogat, String> dataColumn = new TableColumn<>("Data");
         dataColumn.setMinWidth(100);
         dataColumn.setCellValueFactory(new PropertyValueFactory<>("dataLloguer"));
         
         TableColumn<CursLlogat, String> preuColumn = new TableColumn<>("Preu");
         preuColumn.setMinWidth(100);
         preuColumn.setCellValueFactory(new PropertyValueFactory<>("preu"));
         
         TableColumn<CursLlogat, String> preuTotalColumn = new TableColumn<>("Preu total");
         preuTotalColumn.setMinWidth(100);
         preuTotalColumn.setCellValueFactory(new PropertyValueFactory<>("preuTotal"));
                 
         TableColumn<CursLlogat, String> horesColumn = new TableColumn<>("Hores");
         horesColumn.setMinWidth(100);
         horesColumn.setCellValueFactory(new PropertyValueFactory<>("hores"));
         
         TableColumn<CursLlogat, String> personesColumn = new TableColumn<>("N� persones");
         personesColumn.setMinWidth(100);
         personesColumn.setCellValueFactory(new PropertyValueFactory<>("participants"));
         
         taula = new TableView<>();
         taula.setItems(cursLlogatAL);
         taula.getColumns().addAll(idCursColumn, dniColumn, dataColumn, preuColumn, preuTotalColumn, horesColumn, personesColumn);
         
         taula.setMinWidth(700);
         taula.setMinHeight(500);
         taula.setTranslateX(50);
         taula.setTranslateY(125);

         // Botons navegadors
         btnCursos = new Button("Visualitzar cursos");
         btnCursos.setMinWidth(100);
         btnCursos.setTranslateX(800);
         btnCursos.setTranslateY(150);
         btnCursos.setOnAction(e -> miEscenario.setScene(miEscena));
         
         btnCursosLlogats = new Button("Visualitzar cursos llogats");
         btnCursosLlogats.setMinWidth(100);
         btnCursosLlogats.setTranslateX(800);
         btnCursosLlogats.setTranslateY(250);
         btnCursosLlogats.setOnAction(e -> miEscenario.setScene(miEscenaLloguer));
         
         btnLlogarCurs = new Button("Llogar curs");
         btnLlogarCurs.setMinWidth(100);
         btnLlogarCurs.setTranslateX(800);
         btnLlogarCurs.setTranslateY(200);
         
         // Event del bot� del procediment de lloguer
         btnLlogarCurs.setOnAction(e -> miEscenario.setScene(miEscenaProcedimentLloguer));

         btnSortir = new Button("Sortir");
         btnSortir.setMinWidth(100);
         btnSortir.setTranslateX(850);
         btnSortir.setTranslateY(600);

         // Event del bot� sortir
         btnSortir.setOnAction(e -> 
         {
             inserirBD();
             inserirBDOO();
             Platform.exit();
         }
         );

         estilsBotons();

         containerLloguer = new Pane();
         containerLloguer.getChildren().addAll(titol, vistaLogo, taula, btnCursos, btnCursosLlogats,
                 btnLlogarCurs, btnSortir);
         containerLloguer.setMinSize(1000, 700);
         containerLloguer.setStyle(" -fx-base: #152348");

         miEscenaLloguer = new Scene(containerLloguer);

    }

    public void estilsBotons() {
        sombra = new DropShadow();
        illuminacio = new Lighting();

        btnCursos.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> btnCursos.setEffect(sombra));
        btnCursos.addEventHandler(MouseEvent.MOUSE_EXITED, e -> btnCursos.setEffect(null));

        btnCursosLlogats.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> btnCursosLlogats.setEffect(sombra));
        btnCursosLlogats.addEventHandler(MouseEvent.MOUSE_EXITED, e -> btnCursosLlogats.setEffect(null));

        btnSortir.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> btnSortir.setEffect(illuminacio));
        btnSortir.addEventHandler(MouseEvent.MOUSE_EXITED, e -> btnSortir.setEffect(null));
        btnSortir.setStyle(" -fx-base: #FF0E0E");

    }

    public void estilsBotonsLloguer() {

        sombra = new DropShadow();
        illuminacio = new Lighting();

        btnConfirmar.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> btnConfirmar.setEffect(sombra));
        btnConfirmar.addEventHandler(MouseEvent.MOUSE_EXITED, e -> btnConfirmar.setEffect(null));
        btnConfirmar.setStyle("-fx-base: #4FD554");

        btnCancelar.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> btnCancelar.setEffect(illuminacio));
        btnCancelar.addEventHandler(MouseEvent.MOUSE_EXITED, e -> btnCancelar.setEffect(null));
        btnCancelar.setStyle(" -fx-base: #FF0E0E");

    }

    Connection conn = null;
    PreparedStatement ps = null;
    PreparedStatement ps1 = null;
    PreparedStatement ps2 = null;
        
    public boolean inserirArray(){

        cursLlogat = new CursLlogat();
        cursLlogat.setIdCurs(txtIdCurs.getText());
        cursLlogat.setHores(txtHora.getText());
        cursLlogat.setPreu(txtPreuTotal.getText());
        cursLlogat.setPreuTotal(txtPreuTotal.getText());
        cursLlogat.setDNI(txtDNI.getText());
        cursLlogat.setParticipants(txtParticipants.getText());
        cursLlogat.setDataLloguer(txtData.getText());
        cursLlogatAL.add(cursLlogat);

        for (int i = 0; i < cursLlogatAL.size(); i++) {
            System.out.println(cursLlogatAL.get(i));
        }

        if (txtFamNombr.getText() != null) {
            cursFam = new CFamiliars();
            cursFam.setIdCurs(txtIdCurs.getText());
            cursFam.setHores(txtHora.getText());
            cursFam.setPreu(txtPreuTotal.getText());
            cursFam.setPreuTotal(txtPreuTotal.getText());
            cursFam.setDNI(txtDNI.getText());
            cursFam.setParticipants(txtParticipants.getText());
            cursFam.setDataLloguer(txtData.getText());
            cursFam.setNrFamNombrosa(txtFamNombr.getText());
            cursFamiliar.add(cursFam);

            for (int i = 0; i < cursFamiliar.size(); i++) {
                System.out.println(cursFamiliar.get(i));
            }
        } 
       
        if (txtFederacio.getText() != null){
            cursFed = new CFederats();
            cursFed.setIdCurs(txtIdCurs.getText());
            cursFed.setHores(txtHora.getText());
            cursFed.setPreu(txtPreuTotal.getText());
            cursFed.setPreuTotal(txtPreuTotal.getText());
            cursFed.setDNI(txtDNI.getText());
            cursFed.setParticipants(txtParticipants.getText());
            cursFed.setDataLloguer(txtData.getText());
            cursFed.setCatFederacio(txtFederacio.getText());
            cursFederat.add(cursFed);

            for (int i = 0; i < cursFederat.size(); i++) {
                System.out.println(cursFederat.get(i));
            }
        }

        

        
        return true;
    }

    public boolean connexBD(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/escobarsnow2", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean inserirBD(){

        try {
            connexBD();
            if(conn != null){
                    ps = conn.prepareStatement("INSERT INTO cursllogat (DNI, id_curs, dataLloguer, preutotal, hores, participants) VALUES (?, ?, ?, ?, ?, ?)");
                    if(ps != null){
                        for (int j = 0; j < cursLlogatAL.size(); j++) {
                            ps.setString(1, cursLlogatAL.get(j).getDNI());
                            ps.setInt(2, cursLlogatAL.get(j).getIdCurs());
                            ps.setString(3, cursLlogatAL.get(j).getDataLloguer());
                            ps.setDouble(4, cursLlogatAL.get(j).getPreuTotal());
                            ps.setInt(5, cursLlogatAL.get(j).getHores());
                            ps.setInt(6, cursLlogatAL.get(j).getParticipants()); 
                            
                            ps.addBatch();
                        }    
                        ps.executeBatch();
                    }

                    if(cursFamiliar.size() >= 1) {
	                    ps1 = conn.prepareStatement("INSERT INTO cursfamiliar VALUES (?, ?)");
	                    
	                    if(ps1 != null){
	                        for (int k = 0; k < cursFamiliar.size(); k++) {
	                            ps1.setInt(1, cursFamiliar.get(k).getNrFamNombrosa());
	                            ps1.setInt(2, cursFamiliar.get(k).getIdCurs());
	                            
	                            ps1.addBatch();
	                        }
	                        ps1.executeBatch();
	                    }
                    }
                    
                    if(cursFederat.size() >= 1) {
	                    ps2 = conn.prepareStatement("INSERT INTO cursfederat catFederacio, id_curs VALUES (?, ?)");
	                    
	                    if (ps2 != null) {
	                        for (int l = 0; l < cursFederat.size(); l++) {
	                            ps2.setString(1, cursFederat.get(l).getCatFederacio());
	                            ps2.setInt(2, cursFederat.get(l).getIdCurs());
	                    
	                            ps2.addBatch();
	                        }
	                        ps2.executeBatch();
	                    }     
                    }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (ps != null && ps1 != null && ps2 != null) {
                    ps.close();
                    ps1.close();
                    ps2.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
           
        }   
        return true;
    }

    public boolean inserirBDOO() {
    	
    	ObjectContainer db = Db4oEmbedded.openFile("cursosLlogatsDelDia.db4o");
    	
    	HashMap<CursLlogat, Integer> duplicat = new HashMap<CursLlogat, Integer>();
    	
	    	for(CursLlogat CL : cursLlogatAL) {
	    		if(duplicat.containsKey(CL)) {
	    			duplicat.put(CL, duplicat.get(duplicat) +1);
	    		} else {
	    			duplicat.put(CL, 1);
	    		}
	    	}
    	
    	db.store(duplicat);
    	    	
    	CursLlogat cl = new CursLlogat();
    	ObjectSet<CursLlogat> result = db.queryByExample(cl);
    	
    	
    	db.close();
    	return true;
    }
    
    
    public boolean calcularPreuTotal(){

        String hores = txtHora.getText();
        int hora = Integer.parseInt(hores);

        String participants = txtParticipants.getText();
        int persones = Integer.parseInt(participants);

        String preuCurs = txtPreu.getText();
        double preuT = Double.parseDouble(preuCurs);

        if (persones != 0) {

            double preuHora = hora * preuT;

            double preuFinal = persones * preuHora;

            String preuTot = Double.toString(preuFinal);
            txtPreuTotal.setText(preuTot);

        }

        return true;
    }

    public boolean aplicarDescompte(){

        int descompte = 40;

        String nrFam = txtFamNombr.getText();

        String preuTo = txtPreuTotal.getText();
        double preuTota = Double.parseDouble(preuTo);

        if (nrFam != null) {
            
            double preuFinalNo = preuTota - (preuTota * 0.4);

            String preuFinalNombrosa = Double.toString(preuFinalNo);
            txtPreuTotal.setText(preuFinalNombrosa);
        }

        return true;
    }

    public boolean validarFederacio(){

        String idcurs = txtIdCurs.getText();
        int id_curs = Integer.parseInt(idcurs);

        String federacio = txtFederacio.getText();

        System.out.println(federacio);
        if (federacio != null) {
            try {
                connexBD();
                    if (conn != null) {

                    ps = conn.prepareStatement("SELECT tipusFederacio FROM cursos WHERE id_curs = " + id_curs);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        String tipusFederacio = rs.getString("tipusFederacio");
                        System.out.println(tipusFederacio);
                        
                        if (!federacio.equals(tipusFederacio)) {
                            alertaVerificacio = new Alert(AlertType.ERROR);
                            alertaVerificacio.setTitle("Error de validaci�");
                            alertaVerificacio.setHeaderText("Inserir el n� de federaci� correcta!");
                            alertaVerificacio.setContentText("El n� de federaci� del client no �s apta per aquest curs");
                            alertaVerificacio.showAndWait();
                        //* * Excepci� pr�pia
                        throw new ExcepcioFederacio("El nivell del client no coincideix amb el curs");
                        } else{
                            System.out.println("El nivell de federaci� �s correcte");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public ObservableList<Cursos> getCursos(){
        ObservableList<Cursos> cursos = FXCollections.observableArrayList();
        try{      
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/escobarsnow2","root","");
        String SQL = "Select * from cursos";            
        ResultSet rs = con.createStatement().executeQuery(SQL);  
        while(rs.next()){
            Cursos cm = new Cursos();
            cm.setIdCurs(rs.getInt("id_curs"));                       
            cm.setCurs(rs.getString("curs"));
            cm.setCategoria(rs.getString("categoria"));
            cm.setDurada(rs.getInt("durada"));
            cm.setPreu(rs.getInt("preu"));
            cm.setTipusFederacio(rs.getString("tipusFederacio"));
            cursos.add(cm);  
            taula.setItems(cursos);
        }
    }
    catch(Exception e){
          e.printStackTrace();
          System.out.println("Error on Building Data");            
    }
        return cursos;   
    }

}