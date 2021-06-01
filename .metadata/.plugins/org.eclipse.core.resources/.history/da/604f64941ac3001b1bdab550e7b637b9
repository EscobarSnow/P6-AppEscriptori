package Applicacio;

import Classes.Cursos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
// import java.util.Collections.list;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GuardarArray {
    static ObservableList<Cursos> listAL = FXCollections.observableArrayList();
    
    static Connection conn = null;
    
    public static boolean Cursos(){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/escobarsnow2","root","");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM cursos");
            ResultSet rs = ps.executeQuery();
            
            int i=0;
                while(rs.next()){
                    Cursos cursos = new Cursos();
                    cursos.setIdCurs(rs.getInt("id_curs"));
                    cursos.setCurs(rs.getString("curs"));
                    cursos.setCategoria(rs.getString("categoria"));
                    cursos.setDurada(rs.getInt("hora"));
                    cursos.setPreu(rs.getInt("preu"));
                    listAL.add(cursos);

                    i++;
                }
                 for(int j=0; j<listAL.size(); j++){
                 System.out.print(listAL);
        }
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return true;
    }
}
