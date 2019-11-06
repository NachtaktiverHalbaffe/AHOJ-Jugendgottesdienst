package Json;

import com.google.gson.annotations.SerializedName;



public class Video {
    @SerializedName("title")
    private String mTitle;

    @SerializedName("videoId")
    private String mID;

    @SerializedName("publishedAt")
    private String mDate;

    @SerializedName("artwork_url")
    private String mArtworkURL;



    public String getDate() {
        return mDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getID() {return mID;}

    public String getArtworkURL() {
        return mArtworkURL;
    }
}
