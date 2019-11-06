package fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.eje_esslingen.ahoj_jugendgottesdienst.R;


public class Einstellungen extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   //Fragment Preambel
        android.view.View rootView = inflater.inflate(R.layout.einstellungen, container, false);


        return rootView;
    }
}