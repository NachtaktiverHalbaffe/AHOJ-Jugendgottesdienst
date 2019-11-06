package fragments;                                                                                       //PreAmbel



import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import Json.Person;
import Recyclerview.team_adapter;
import de.eje_esslingen.ahoj_jugendgottesdienst.R;

public class Team extends Fragment {

    static public List<Person> team_database;

    private static final String server_url_alternate="https://script.google.com/macros/s/AKfycbxsIHUVon-jCHfWVg2N2EOwk6aGEVYH_YT1V6uFpWdULpoI5NI/exec?id=";
    private static final String database="Das Team";
    private static final String database_key ="1TrGfgnp8IVYuEpdUqQl-fNFP3p3bErIF0ldGBYabCjE";
    private static final String end="&sheet=Tabellenblatt1";
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){   //Fragment Preambel
        View rootView = inflater.inflate(R.layout.team, container, false);

        //interne Datenbank erstellen
        team_database= new ArrayList<>();
        team_database.add(new Person(R.drawable.bjoern,"Björn Winter","Hauptverantwortung, Predigt, Moderation","07.08.1985","CVJM Esslingen","01.10.2011","Der AHOJ ist für mich ein Ort, an dem Jesus Zeit mit uns verbringen möchte und ich darf den Ort mit Vorbereiten.","Es ist ein sehr cooles Team, mit dem es Spaß macht zu Rocken."));
        team_database.add(new Person(R.drawable.freddy,"Frederic Postic","Hauptverantwortung, Leitungsteam, Predigt","17.12.1989","Oberesslingen","01.04.2016","Noch ein spannendes Projekt, was ich gerne mitgestalten und weiterentwickeln möchte.","Ich möchte Jugendlichen einen Gottesdienst ermöglichen der nichts mit den Sonntagmorgengottsdienst zu tun hat, sondern ein junger, lebendiger und einladender Gottesdienst ist! Wo Gott für Jugendliche auf unterschiedliche Weise erlebbar wird!"));
        team_database.add(new Person(R.drawable.felix, "Felix Brugger", "Technische Leitung, Plakatdesign", "24.08.1997", "Aichwald", "01.12.2013", "Der AHOJ ist ein Jugendgottesdienst für junge Menschen ab 14 Jahren. Der AHOJ besticht durch seinen hervorragenden Einsatz von Veranstaltungstechnik. Es werden modernen Liedern gesungen von ausgezeichneten Bands und Messages, die sowohl an Einsteiger als auch schon im Glauben gefestigte Christen gerichtet sind.", "Der AHOJ ist einer der wenigen Jugendgottesdienste, die über die Jahre im Großraum Esslingen \"überlebt\" haben. Jugendgottesdienste wie z.B. der AHOJ sind wichtig, um Jugendliche den Glauben nahe zu bringen. Von daher ist der Erhalt des Jugendgottesdienstes selbst meine primäre Motivation und es macht unglaublich viel Spaß. Eine unschlagbare Kombo!"));
        team_database.add(new Person(R.drawable.steffen,"Steffen Schmidt","Band, Moderation","06.06.1996","St. Bernhard","01.10.2011","Der AHOJ ist für mich eine gute Gelegenheit, jungen Leuten, den Spaß und die Freude am christlichen Glauben zu vermitteln.","Ich habe bereits den ersten AHOJ mit vorbereitet und habe seitdem große Freude daran den AHOJ weiter zu entwickeln und wachsen zu sehen. Außerdem freue ich mich über die Begegnungen mit den Jugendlichen und mit Gott."));
        team_database.add(new Person(R.drawable.julia,"Julia Rohn","Leitungsteam, Anspiel","07.06.1993","Tamm","01.10.2011","Der AHOJ ist für mich ein Jugendgottesdienst mit guter Musik, einer inspierenden Predigt, vielen tollen und lustigen Ideen, mega leckerem Essen und natürlich ein Ort, an dem ich tolle neue Leute kennen lernen kann und Gott erlebe!","Der AHOJ liegt mir einfach am Herzen. Ich freue mich über das tolle Mitarbeiterteam, in dem wir so viele tolle Talente haben und ich freue mich einfach bei jedem AHOJ darüber, dass es in Esslingen und Umkreis junge Menschen gibt, die Gott erleben möchten. Ich kann mich selbst in vielen Bereichen ausprobieren und gleichzeitig einfach sein wie ich bin - und dabei noch etwas für Gott machen. Besser geht es doch eigentlich nicht ;-)"));
        team_database.add(new Person(R.drawable.henri, "Henriette Scherrieble ", "Leitungsteam, Moderation", "23.04.1999", "Hohenkreuz", "01.05.2014", "Für mich ist der AHOJ ein Ort, an dem ich meine Freunde treffe, viele verschiedene Dinge für mein Leben mitnehmen und den Jugendlichen begegnen kann.", "Ich bin Mitarbeiterin beim AHOJ, weil mir der AHOJ wichtig ist, es mir Spaß macht, weil ich etwas gestalten kann. Aber vorallem, weil ich durch den AHOJ den Jugendlichen zeigen möchte, dass es cool ist Christ zu sein und ich mich freue, den Jugendlichen durch den AHOJ Gott näher bringen zu können. "));
        team_database.add(new Person(R.drawable.sophie, "Sofie Auer ", "Moderation", "16.09.1998", "Plochingen", "01.01.2015", "Eine tolle Möglichkeit, sich zu engagieren und anderen Gott näher zu bringen.", "Weil sowohl Vorbereitung als auch Durchführung im AHOJ-Team richtig Spaß macht."));
        team_database.add(new Person(R.drawable.profilpic_placeholder, "Constantin ", "Auf- und Abbau", "28.05.2016", "Esslingen", "01.01.2013", "Keine Angabe", "Wegen einem coolen AHOJ-Team und wegen den tollen Jugendgottesdiensten"));
        team_database.add(new Person(R.drawable.tobi,"Tobias Rohn","Moderation, Anspiel","01.01.1994","Hegensberg","01.09.2015","Für mich ist AHOJ ein Gottesdienst, der durch seine verrückten und kreativen Einfälle ganz viele Möglichkeiten bietet, Gott näher kennenzulernen.","Ich arbeite gerne beim AHOJ mit, weil mir das Ausdenken, das Entwickeln und die schlussendliche Durchführung der Gottesdienste mega viel Spaß machen."));
        team_database.add(new Person(R.drawable.tom,"Tom Dunkel","Bistro","09.08.1999","Aichwald","01.07.2016","AHOJ bedeutet für mich, Zeit mit coolen Leuten und vor allem Gott zu verbringen.\n" + "Erfahrungen auszutauschen, Spaß zu haben und einfach ne coole Zeit zu haben.","Meine Motivation ist es anderen Leuten zu helfen und sie evt. weiterzubringen.\n" + "Da ich nicht immer an Gott geglaubt hab, weiß ich, dass es sich für manche Menschen vllt. lächerlich anhört.\n" + "Aber wenn man dann mal dabei ist, sieht man Vieles anders."));
        team_database.add(new Person(R.drawable.elena,"Elena Herrmann","Aufbau, Bistro","15.06.2002","Deizisau","01.06.2016","Eine super Gelegenheit für Jugendliche einen altersgerechten Gottesdienst zu langschläferfreundlichen Zeiten zu erleben.","Der AHOJ ist eine wichtige Arbeit, die ich gerne unterstütze und das Mitarbeiterteam ist auch echt cool;)"));
        team_database.add(new Person(R.drawable.jonas,"Jonas Hildebrand","Bistro, Auf- und Abbau","26.11.2000","Aichwald","01.11.2015","Einer der besten Gottesdienste in denen ich je war, immer interessant und nett gestaltet.","Es macht mir Spaß und ich möchte, dass die Besucher den Gottesdienst so erleben können, so wie ich ihn bereits erlebt habe, dass es ihnen gefällt und sie öfters kommen. Außerdem trifft man immer nette Leute und hat ein freundliches Mitarbeiterteam um sich herum."));
        team_database.add(new Person(R.drawable.timo,"Timo Raisch","Auf- und Abbau","09.08.2000","Hegensberg-Liebersbronn","01.07.2016","AHOJ ist für mich eine Gelegenheit Gott anders zu erfahren als es in den normalen Gottesdiensten der Fall ist!","Ich bin Mitarbeiter, weil es mir Spaß macht, ich immer Spaß hatte als Teilnehmer und das jetzt an Konfirmanden und jüngeren Leuten weiter geben möchte."));
        team_database.add(new Person(R.drawable.profilpic_placeholder,"Pauline Schnabl","Vorbereitung","26.07.2001","Aichwald","01.07.2017","Eine Abwecshlung zum normalen Gottesdienst, wo man einfach man selbst sein kann.","Ich damals selbst immer den AHOJ besucht habe und es mir den christlichen Glauben näher gebracht hat. Ich freue mich neue und juge Gesichter zu sehen und vor allem den Glauben näher bringen will."));
        team_database.add(new Person(R.drawable.jonas_r,"Jonas Raisch","Band","09.07.1998","Hegensberg-Liebersbronn","01.12.2015","Ein cooler Jugendgottesdienst, in dem man Glaube erleben kann und zusätzlich Spaß und leckeres Essen hat.","Mir Jugendarbeit wichtig ist und ich finde, dass Jugendliche einen Gottesdienst bekommen sollten, der ihnen Spaß macht."));

        //Recyclerview aufsetze
        RecyclerView mRecyclerview = rootView.findViewById(R.id.team_card);
        final LinearLayoutManager team_llm = new LinearLayoutManager(getActivity());
        mRecyclerview.setLayoutManager(team_llm);
        team_adapter adapter = new team_adapter(getActivity(), team_database);
        mRecyclerview.setAdapter(adapter);
       // updateList(database);

        return rootView;
    }

}

