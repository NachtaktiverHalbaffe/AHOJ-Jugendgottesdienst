package Json;

public class Person {

    public int profilpic;
    public String name;
    public String hauptfunktion;
    public String alter;
    public String wohnort;
    public String mitarbeit_seit;
    public String ahojverständnis;
    public String motivation;

    public Person(int profilpic, String name, String hauptfunktion, String alter, String wohnort, String mitarbeit_seit, String ahojverständnis, String motivation) {
        this.profilpic = profilpic;
        this.name = name;
        this.hauptfunktion = hauptfunktion;
        this.alter = alter;
        this.wohnort = wohnort;
        this.mitarbeit_seit=mitarbeit_seit;
        this.ahojverständnis = ahojverständnis;
        this.motivation = motivation;
    }
}