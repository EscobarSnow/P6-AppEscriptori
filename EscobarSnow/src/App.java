import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application{
    Scene miEscena;
    Pane container;
    Text titol;
    TableView taula;
    TableColumn idCurs, curs, categoria, preu;
    Button btnCursos, btnLlogar, btnCursosLlogats;
    
    public static void main(String[] args) throws Exception {

        Application.launch(args);

    }

    @Override
    public void start(Stage miEscenario) throws Exception {
     

        // Nom de l'empresa / header
        
        

        // Taula
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

        btnCursosLlogats = new Button("Visualitzar cursos llogats");
        btnCursosLlogats.setMinWidth(100);
        btnCursosLlogats.setTranslateX(800);
        btnCursosLlogats.setTranslateY(250);
        

        container = new Pane();
        container.getChildren().addAll(taula, btnCursos, btnLlogar, btnCursosLlogats);
        container.setMinSize(350, 250);

        miEscena = new Scene(container);

        miEscenario.setTitle("Llogar cursos");
        miEscenario.setMinHeight(700);
        miEscenario.setMinWidth(1000);

        miEscenario.setScene(miEscena);

        miEscenario.show();
    }
}
