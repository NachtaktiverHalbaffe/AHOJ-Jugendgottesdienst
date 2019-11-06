package Json;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class YouTube {

    private static final String API_URL = "https://www.googleapis.com";

    private static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static final YTService SERVICE = RETROFIT.create(YTService.class);

    public static YTService getService() {
        return SERVICE;
    }
}
