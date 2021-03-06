package Classes;

public class CFederats extends CursLlogat{
    private String catFederacio;


    public CFederats() {
    }

    public CFederats(int idCursLlogat, int idCurs, String DNI, String dataLloguer, double preu, double preuTotal, int hores, int participants, String catFederacio) {
        super(idCursLlogat, idCurs, DNI, dataLloguer, preu, preuTotal, hores, participants);
        this.catFederacio = catFederacio;
    }

    public String getCatFederacio() {
        return this.catFederacio;
    }

    public void setCatFederacio(String catFederacio) {
        this.catFederacio = catFederacio;
    }

    @Override
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
                ", catFederacio='" + getCatFederacio() + "'" +
                "}";
    }
    

}
