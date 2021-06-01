package Classes;

public class Cursos {
    
    private int id_Curs;
    private String curs;
    private String categoria;
    
    private int durada;
    private double preu;
    private String tipusFederacio;


    public Cursos() {
    }

    public Cursos(int id_Curs, String curs, String categoria,  int durada, double preu, String tipusFederacio) {
        this.id_Curs = id_Curs;
        this.curs = curs;
        this.categoria = categoria;
        
        this.durada = durada;
        this.preu = preu;
        this.tipusFederacio = tipusFederacio;
    }

    public int getIdCurs() {
        return this.id_Curs;
    }

    public void setIdCurs(int id_Curs) {
        this.id_Curs = id_Curs;
    }

    public String getCurs() {
        return this.curs;
    }

    public void setCurs(String curs) {
        this.curs = curs;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    

    public int getDurada() {
        return this.durada;
    }

    public void setDurada(int durada) {
        this.durada = durada;
    }

    public double getPreu() {
        return this.preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    public String getTipusFederacio() {
        return this.tipusFederacio;
    }

    public void setTipusFederacio(String tipusFederacio) {
        this.tipusFederacio = tipusFederacio;
    }

    @Override
    public String toString() {
        return "{" +
            " id_Curs='" + id_Curs + "'" +
            ", curs='" + curs + "'" +
            ", categoria='" + categoria + "'" +
            ", durada='" + durada + "'" +
            ", preu='" + preu + "'" +
            ", tipusFederacio='" + tipusFederacio + "'" +
            "}";
    }
    

}