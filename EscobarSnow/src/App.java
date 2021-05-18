import java.io.FileInputStream;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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


// Cambieu només la part de la taula/table, lo altre mantenir per tenir-ho igual
public class App extends Application{
    Scene miEscenaLloguer;
    Pane containerLloguer;
    Text titol;
    Image logo;
    ImageView vistaLogo;
    // TableView i TableColumn cambiar-ho amb la vostra apartat 
    TableView taula;
    TableColumn idCurs, curs, categoria, preu;

    Button btnCursos, btnLlogar, btnCursosLlogats, btnSortir;
    DropShadow sombra;
    Lighting illuminacio;
    
    public static void main(String[] args) throws Exception {

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
        logo = new Image(new FileInputStream("C:/Users/DAM/Desktop/P6-AppEscriptori/EscobarSnow/src/Imatges/logo.png"));
        vistaLogo = new ImageView(logo);

        vistaLogo.setTranslateX(50);
        vistaLogo.setTranslateY(20);

        vistaLogo.setFitHeight(85);
        vistaLogo.setFitWidth(85);

        // Configuració de la proporció de conservació de la visualització de la imatge
        vistaLogo.setPreserveRatio(true);

        // Taula de lloguer *****Cambiar al vostre*****
        taula = new TableView();
        idCurs = new TableColumn<>("ID curs");
        curs = new TableColumn<>("Curs");
        categoria = new TableColumn<>("Categoria");
        preu = new TableColumn<>("Preu");

        taula.getColumns().addAll(idCurs, curs, categoria, preu);
         
        idCurs.setCellValueFactory(new PropertyValueFactory<>("ID curs"));
        curs.setCellValueFactory(new PropertyValueFactory<>("Curs"));
        categoria.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
        preu.setCellValueFactory(new PropertyValueFactory<>("Preu"));

        taula.setMinWidth(700);
        taula.setMinHeight(500);
        taula.setTranslateX(50);
        taula.setTranslateY(125);

        // Botons navegadors
        btnCursos = new Button("Visualitzar cursos");
        btnCursos.setMinWidth(100);
        btnCursos.setTranslateX(800);
        btnCursos.setTranslateY(150);

        
        btnLlogar = new Button("Llogar Cursos");
        btnLlogar.setMinWidth(100);
        btnLlogar.setTranslateX(800);
        btnLlogar.setTranslateY(200);

        // Event del botó llogar
        btnLlogar.setOnAction(e->containerLloguer.getChildren());


        btnCursosLlogats = new Button("Visualitzar cursos llogats");
        btnCursosLlogats.setMinWidth(100);
        btnCursosLlogats.setTranslateX(800);
        btnCursosLlogats.setTranslateY(250);

        

        btnSortir = new Button("Sortir");
        btnSortir.setMinWidth(100); 
        btnSortir.setTranslateX(850);
        btnSortir.setTranslateY(600);

        // Event del botó sortir
        btnSortir.setOnAction(e->Platform.exit());
        
        estilsBotons();

        containerLloguer = new Pane();
        containerLloguer.getChildren().addAll(titol, vistaLogo, taula, btnCursos, btnLlogar, btnCursosLlogats, btnSortir);
        containerLloguer.setMinSize(350, 250);
        containerLloguer.setStyle(" -fx-base: #152348");

        miEscenaLloguer = new Scene(containerLloguer);

        miEscenario.setTitle("Llogar cursos");
        miEscenario.setMinHeight(700);
        miEscenario.setMinWidth(1000);

        miEscenario.setScene(miEscenaLloguer);

        miEscenario.show();
    }

    public void estilsBotons(){
        sombra = new DropShadow();
        illuminacio = new Lighting();

        btnCursos.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> btnCursos.setEffect(sombra));
        btnCursos.addEventHandler(MouseEvent.MOUSE_EXITED, e -> btnCursos.setEffect(null));

        btnLlogar.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> btnLlogar.setEffect(sombra));
        btnLlogar.addEventHandler(MouseEvent.MOUSE_EXITED, e -> btnLlogar.setEffect(null));

        btnCursosLlogats.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> btnCursosLlogats.setEffect(sombra));
        btnCursosLlogats.addEventHandler(MouseEvent.MOUSE_EXITED, e -> btnCursosLlogats.setEffect(null));

        btnSortir.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> btnSortir.setEffect(illuminacio));
        btnSortir.addEventHandler(MouseEvent.MOUSE_EXITED, e -> btnSortir.setEffect(null));
        btnSortir.setStyle(" -fx-base: #FF0E0E");
        
    }

}
