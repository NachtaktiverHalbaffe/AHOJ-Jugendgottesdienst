package fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import de.eje_esslingen.ahoj_jugendgottesdienst.R;

public class Home extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home,container,false);

        //Webview aufsetzen
        WebView mWebView = rootView.findViewById(R.id.view_instagram);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //URL eingeben
        mWebView.loadUrl("https://www.instagram.com/ahoj_jugendgottesdienst/");
        return rootView;
    }
}
