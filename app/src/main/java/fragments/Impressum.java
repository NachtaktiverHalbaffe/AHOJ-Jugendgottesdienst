package fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.Calendar;

import de.eje_esslingen.ahoj_jugendgottesdienst.R;
import threads.BootCompleteReciever;
import threads.backgroundtask_push;

import static android.content.Context.MODE_PRIVATE;

public class Impressum extends Fragment{

    public static Switch sw;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.impressum, container, false);

        Button lic = rootView.findViewById(R.id.licence);

        //Lizenzen aufrufen button
        lic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String lizenzen = "Lizenzen\n" +
                        "\n" +
                        "Copyright (C) 2011 The Android Open Source Project\n" +
                        "\n" +
                        "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                        "you may not use this file except in compliance with the License.\n" +
                        "You may obtain a copy of the License at\n" +
                        "\n" +
                        "http://www.apache.org/licenses/LICENSE-2.0\n" +
                        "\n" +
                        "Unless required by applicable law or agreed to in writing, software\n" +
                        "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                        "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                        "See the License for the specific language governing permissions and\n" +
                        "limitations under the License.\n" +
                        "\n" +
                        "Joda Time\n" +
                        "\n" +
                        "Copyright 2016 Joda.org\n" +
                        "\n" +
                        "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                        "you may not use this file except in compliance with the License.\n" +
                        "You may obtain a copy of the License at\n" +
                        "\n" +
                        "http://www.apache.org/licenses/LICENSE-2.0\n" +
                        "\n" +
                        "Unless required by applicable law or agreed to in writing, software\n" +
                        "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                        "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                        "See the License for the specific language governing permissions and\n" +
                        "limitations under the License.\n"+"\n"+
                                "Blurry\n" +
                        "\n"+ "Copyright 2017 Daichi Furiya"+"\n"+ "Licensed under the Apache License, Version 2.0 (the \" License\");you may not use this file except in compliance with the License. You may obtain a copy of the License at" +"\n"+"\n"+ "http://www.apache.org/licenses/LICENSE-2.0" +"\n" + "\n"+"Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.";
                AlertDialog.Builder builder_lic = new AlertDialog.Builder(getActivity());
                builder_lic.setMessage(lizenzen);
                AlertDialog dialog = builder_lic.create();
                dialog.show();

            }
        });

        //Datenschutzerklärung aufrufen Button
        Button dat = rootView.findViewById(R.id.datenschutzbestimmung);
        dat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String datenschutzbestimmung =
                        "Datenschutzerklärung\n" +
                                "\n" +
                                "Die Nutzung unserer Applikation ist ohne eine Angabe von personenbezogenen Daten möglich. Für die Nutzung einzelner Services unserer Seite können sich hierfür abweichende Regelungen ergeben, die in diesem Falle nachstehend gesondert erläutert werden. Ihre personenbezogenen Daten (z.B. Name, Anschrift, E-Mail, Telefonnummer, u.ä.) werden von uns nur gemäß den Bestimmungen des deutschen Datenschutzrechts verarbeitet. Daten sind dann personenbezogen, wenn sie eindeutig einer bestimmten natürlichen Person zugeordnet werden können. Die rechtlichen Grundlagen des Datenschutzes finden Sie im Bundesdatenschutzgesetz (BDSG) und dem Telemediengesetz (TMG). Nachstehende Regelungen informieren Sie insoweit über die Art, den Umfang und Zweck der Erhebung, die Nutzung und die Verarbeitung von personenbezogenen Daten durch den Anbieter\n" +
                                "\n" +
                                "Evang. Jugendwerk Bezirk Esslingen\n" +
                                "Berliner Strasse 27\n" +
                                "D-73728 Esslingen\n" +
                                "\n" +
                                "Tel: +49 711 396941-0\n" +
                                "\n" +
                                "E-Mail: info@eje-esslingen.de\n" +
                                "\n" +
                                "Wir weisen darauf hin, dass die internetbasierte Datenübertragung Sicherheitslücken aufweist, ein lückenloser Schutz vor Zugriffen durch Dritte somit unmöglich ist.\n" +
                                "\n" +
                                "Einsatz von Instagram\n" +
                                "\n" +
                                "Wir setzen in unserer Applikation den Dienst Instagram ein. Instagram ist ein Service der Instagram Inc. Durch den eingebundenen „Insta“-Button auf unsere Seite erhält Instagram die Information, dass Sie die entsprechende Seite unseres Internetauftritts aufgerufen haben. Sind Sie bei Instagram eingeloggt, kann Instagram diesen Besuch auf unserer Seite Ihrem Instagram -Konto zuordnen und die Daten somit verknüpfen. Die durch Anklicken des „Insta“-Buttons übermittelten Daten werden von Instagram gespeichert. Zu Zweck und Umfang der Datenerhebung, deren Verarbeitung und Nutzung sowie Ihren diesbezüglichen Rechten und Einstellungsmöglichkeiten zum Schutz Ihrer Privatsphäre erhalten Sie weitere Informationen in den Instagram -Datenschutzhinweisen, die Sie über https://help.instagram.com/155833707900388 abrufen können.\n" +
                                "\n" +
                                "Um zu verhindern, dass Instagram bei der Benutzung unserer Applikation Ihrem Instagram-Konto zuordnen kann, dürfen Sie sich innerhalb unserer Applikation auf Instagram nicht anmelden. \n" +
                                "\n" +
                                "Auskunft/Widerruf/Löschung\n" +
                                "\n" +
                                "Sie können sich aufgrund des Bundesdatenschutzgesetzes bei Fragen zur Erhebung, Verarbeitung oder Nutzung Ihrer personenbezogenen Daten und deren Berichtigung, Sperrung, Löschung oder einem Widerruf einer erteilten Einwilligung unentgeltlich an uns wenden. Wir weisen darauf hin, dass Ihnen ein Recht auf Berichtigung falscher Daten oder Löschung personenbezogener Daten zusteht, sollte diesem Anspruch keine gesetzliche Aufbewahrungspflicht entgegenstehen.";
                AlertDialog.Builder builder_dat = new AlertDialog.Builder(getActivity());
                builder_dat.setMessage(datenschutzbestimmung);
                AlertDialog dialog = builder_dat.create();
                dialog.show();

            }
        });

        //Kontakt aufrufen Button
        Button kon = rootView.findViewById(R.id.kontakt);
        kon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String kontakt = "Kontakt\n" +
                        "\n" +
                        "Evang. Jugendwerk Bezirk Esslingen\n" +
                        "Berliner Straße 27\n" +
                        "D-73728 Esslingen\n" +
                        "\n" +
                        "Telefon: +49 711 396941-0\n" +
                        "Fax: +49 711 396941-39\n" +
                        "\n" +
                        "E-Mail: info@eje-esslingen.de\n" +
                        "Internet: http://www.eje-esslingen.de";
                AlertDialog.Builder builder_kon = new AlertDialog.Builder(getActivity());
                builder_kon.setMessage(kontakt);
                AlertDialog dialog = builder_kon.create();
                dialog.show();
            }
        });

        //Nächster Ahoj notification an/ausschalten
        sw = rootView.findViewById(R.id.push_switch);
        final Intent startservice = new Intent(getActivity(), backgroundtask_push.class);
        final PendingIntent startPendingIntent = PendingIntent.getService(getActivity(), 0, startservice, 0);
        final Calendar calendar = Calendar.getInstance();
        SharedPreferences sharedPrefs = getActivity().getSharedPreferences("fragments.Impressum", MODE_PRIVATE);
        sw.setChecked(sharedPrefs.getBoolean("switch_on", true));

        //Switch Notification an aus
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("fragments.Impressum", MODE_PRIVATE).edit();
                    editor.putBoolean("switch_on", true);
                    editor.apply();
                    AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(getActivity().ALARM_SERVICE);
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 216000000, startPendingIntent);
                    PackageManager pm  = getActivity().getPackageManager();
                    ComponentName componentName = new ComponentName(getActivity(), BootCompleteReciever.class);
                    pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                } else {
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("fragments.Impressum", MODE_PRIVATE).edit();
                    editor.putBoolean("switch_on", false);
                    editor.apply();
                    PendingIntent.getBroadcast(getActivity(), 0, startservice, PendingIntent.FLAG_UPDATE_CURRENT).cancel();
                    PackageManager pm  = getActivity().getPackageManager();
                    ComponentName componentName = new ComponentName(getActivity(), BootCompleteReciever.class);
                    pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                }
            }
        });

        return rootView;
    }}