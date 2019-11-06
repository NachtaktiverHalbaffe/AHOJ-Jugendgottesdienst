package fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import Json.Predigt;
import Json.SCService;
import Json.SoundCloud;
import Json.Track;
import Json.Video;
import Json.YTService;
import Json.YouTube;
import Recyclerview.predigten_adapter;
import de.eje_esslingen.ahoj_jugendgottesdienst.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Predigten extends Fragment {

    static public List<Video> predigt_database;
    predigten_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   //Fragment Preambel
        View rootView = inflater.inflate(R.layout.predigten, container, false);

        //Interne Dataenbank anlegen
        predigt_database = new ArrayList<>();
        final YTService ytService = YouTube.getService();
        ytService.getVideos("AIzaSyAsLOsF0cPWjDaKuYX8gQb2pLySZ5ypo54","UCnsHD1yiKw-ERgDldZt_X2Q","snippet","date","20").enqueue(new Callback<Video>() {
            @Override
            public void onResponse(Call<Video> call, Response<Video> response) {
                if (response.isSuccessful()) {
                    Video videos = response.body();
               //     predigt_database = predigt_database.addAll(videos);
                }
            }@Override
            public void onFailure(Call<Video> call, Throwable t) {
                showMessage("Netzwerkfehler: " +  t.getMessage());
            }
        });

        //Recyclerview Darstellung definieren
        RecyclerView mRecyclerview = rootView.findViewById(R.id.predigten_card);
        final LinearLayoutManager predigten_llm = new LinearLayoutManager(getActivity());
        mRecyclerview.setLayoutManager(predigten_llm);
        predigten_adapter adapter = new predigten_adapter(getActivity(),predigt_database);
        mRecyclerview.setAdapter(adapter);

        return rootView;
    }
    private void loadVideos(List<Video> videos) {
        predigt_database.clear();
        predigt_database.addAll(videos);
        adapter.notifyDataSetChanged();
    }
    private void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}
