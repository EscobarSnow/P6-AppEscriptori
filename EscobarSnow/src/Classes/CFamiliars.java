package Classes;


public class CFamiliars extends CursLlogat{

    private int nrFamNombrosa;


    public CFamiliars() {
    }

    public CFamiliars(int idCursLlogat, int idCurs, String DNI, String dataLloguer, double preu, double preuTotal, int hores, int participants, int nrFamNombrosa) {
        super(idCursLlogat, idCurs, DNI, dataLloguer, preu, preuTotal, hores, participants);
        this.nrFamNombrosa = nrFamNombrosa;
    }

    public int getNrFamNombrosa() {
        return this.nrFamNombrosa;
    }

    public void setNrFamNombrosa(String nrFamNombrs) {
        int nrFamNombrosa = Integer.parseInt(nrFamNombrs);
        this.nrFamNombrosa = nrFamNombrosa;
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
                ", nrFamNombrosa='" + getNrFamNombrosa() + "'" +
                "}";
        
    }

}
