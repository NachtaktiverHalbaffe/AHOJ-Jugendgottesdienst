package Json;

import java.util.List;

import fragments.Impulse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface YTService {
    //String channel_id="UCnsHD1yiKw-ERgDldZt_X2Q";
    @GET("/youtube/v3/vidoes")
    Call<Video> getVideos(@Query("key")String key,
                                @Query("channelId")String channel_id,
                                @Query("part")String snippet,
                                @Query("order") String order,
                                @Query("maxResults") String max_results);
}
