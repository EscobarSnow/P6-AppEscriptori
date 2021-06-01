package Classes;

public class CursLlogat {
    protected int idCursLlogat;
    protected int idCurs;
    protected String DNI;
    protected String dataLloguer;
    protected double preu;
    protected double preuTotal;
    protected int hores;
    protected int participants;


    public CursLlogat() {
    }


    public CursLlogat(int idCursLlogat, int idCurs, String DNI, String dataLloguer, double preu, double preuTotal, int hores, int participants) {
        this.idCursLlogat = idCursLlogat;
        this.idCurs = idCurs;
        this.DNI = DNI;
        this.dataLloguer = dataLloguer;
        this.preu = preu;
        this.preuTotal = preuTotal;
        this.hores = hores;
        this.participants = participants;
    }
   

    public int getIdCursLlogat() {
        return this.idCursLlogat;
    }

    public void setIdCursLlogat(int idCursLlogat) {
        this.idCursLlogat = idCursLlogat;
    }

    public int getIdCurs() {
        return this.idCurs;
    }

    public void setIdCurs(String id_Curs) {
        int idCurs = Integer.parseInt(id_Curs);
        this.idCurs = idCurs;
    }

    public String getDNI() {
        return this.DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getDataLloguer() {
        return this.dataLloguer;
    }

    public void setDataLloguer(String dataLloguer) {
        this.dataLloguer = dataLloguer;
    }

    public double getPreu() {
        return this.preu;
    }

    public void setPreu(String preu_T) {
        double preu = Double.parseDouble(preu_T);
        this.preu = preu;
    }

    public double getPreuTotal() {
        return this.preuTotal;
    }

    public void setPreuTotal(String preu_Total) {
        double preuTotal = Double.parseDouble(preu_Total);
        this.preuTotal = preuTotal;
    }

    public int getHores() {
        return this.hores;
    }

    public void setHores(String hora) {
        int hores = Integer.parseInt(hora);
        this.hores = hores;
    }

    public int getParticipants() {
        return this.participants;
    }

    public void setParticipants(String participant) {
        int participants = Integer.parseInt(participant);
        this.participants = participants;
    }

    public String toString() {
        return "{" +
            " idCursLlogat='" + getIdCursLlogat() + "'" +
            ", idCurs='" + getIdCurs() + "'" +
            ", DNI='" + getDNI() + "'" +
            ", dataLloguer='" + getDataLloguer() + "'" +
            ", preu='" + getPreu() + "'" +
            ", preuTotal='" + getPreuTotal() + "'" +
            ", hores='" + getHores() + "'" +
            ", participants='" + getParticipants() + "'" +
            "}";
    }
    

}
