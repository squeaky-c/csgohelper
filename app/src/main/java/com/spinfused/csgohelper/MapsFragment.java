package com.spinfused.csgohelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MapsFragment extends Fragment {

    public static WebView mWebView;

    public static boolean canGoBack(){
        return mWebView.canGoBack();
    }

    public static void goBack(){
        mWebView.goBack();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_maps, container, false);
        mWebView = (WebView) v.findViewById(R.id.mapWebView);
        mWebView.loadUrl("http://www.spinfused.com/csgohelper/");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        return v;
    }
}
