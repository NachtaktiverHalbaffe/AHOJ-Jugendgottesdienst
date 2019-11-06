package Json;


public class ListItems_Team {

    private String profilpic;
    private String name;
    private String hauptfunktion;
    private String alter;
    private String wohnort;
    private String ahojverständnis;
    private String motivation;

    public String getProfilpic() {
        return profilpic;
    }
    public void setProfilpic(String profilpic) {
        this.profilpic = profilpic;
    }

    public String getName() {
        return name;
    }
    public String setName(String name) {
        this.name = name;
        return name;
    }

    public String getHauptfunktion() {
        return hauptfunktion;
    }
    public void setHauptfunktion(String hauptfunktion) {
        this.hauptfunktion = hauptfunktion;
    }

    public String getAlter() {return alter;}
    public void setAlter(String alter){this.alter= alter;}

    public String getWohnort(){return wohnort;}
    public void setWohnort(String wohnort){this.wohnort = wohnort;}

    public String getAhojverständnis(){return ahojverständnis;}
    public void setAhojverständnis(String ahojverständnis){this.ahojverständnis = ahojverständnis;}

    public String getMotivation(){return motivation;}
    public void setMotivation(String motivation){this.motivation = motivation;}

}