package Json;


public class ListItems_Veranstaltungen{

    private String plakat;
    private String veranstaltungen;
    private String motto;
    private String datum;
    private String ort;

    public String getPlakat() {
        return plakat;
    }
    public void setPlakat(String profilpic) {
        this.plakat = profilpic;
    }

    public String getVeranstaltungen() {
        return veranstaltungen;
    }
    public String setVeranstaltungen(String veranstaltungen) {
        this.veranstaltungen = veranstaltungen;
        return veranstaltungen;
    }

    public String getMotto() {
        return motto;
    }
    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getDatum() {return datum;}
    public void setDatum(String datum){this.datum= datum;}

    public String getOrt(){return ort;}
    public void setOrt(String ort){this.ort = ort;}

}