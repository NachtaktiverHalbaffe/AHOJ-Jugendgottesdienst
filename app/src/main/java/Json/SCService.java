package Json;

import java.util.List;

import fragments.Impulse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

 public interface SCService {

     @GET("/users/303463938/tracks?client_id="/*"/tracks?client_id="*/+ Impulse.CLIENT_ID)
        Call<List<Track>> getRecentTracks(@Query("created_at") String date);
    }

