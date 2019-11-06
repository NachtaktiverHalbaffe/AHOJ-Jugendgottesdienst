package Json;

import android.app.ProgressDialog;

import org.json.JSONObject;

public interface AsyncResult

{
    void onResult(JSONObject object);
}
