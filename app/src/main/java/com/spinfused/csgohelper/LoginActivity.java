package com.spinfused.csgohelper;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LoginActivity extends AppCompatActivity {

    // The string will appear to the user in the login screen
    // you can put your app's name
    final String REALM_PARAM = "CSGO Helper";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String url = "https://steamcommunity.com/openid/login?" +
                "openid.claimed_id=http://specs.openid.net/auth/2.0/identifier_select&" +
                "openid.identity=http://specs.openid.net/auth/2.0/identifier_select&" +
                "openid.mode=checkid_setup&" +
                "openid.ns=http://specs.openid.net/auth/2.0&" +
                "openid.realm=https://" + REALM_PARAM + "&" +
                "openid.return_to=https://" + REALM_PARAM + "/signin/";

        final WebView webView = (WebView) findViewById(R.id.loginWebView);
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);


        final Activity activity = this;

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url,
                                      Bitmap favicon) {

                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("example_text","");
                editor.commit();


                setTitle("Steam Community");
                Uri Url = Uri.parse(url);

                if(Url.getAuthority().equals(REALM_PARAM.toLowerCase())){
                    // That means that authentication is finished and the url contains user's id.
                    webView.stopLoading();

                    // Extracts user id.
                    Uri userAccountUrl = Uri.parse(Url.getQueryParameter("openid.identity"));
                    String userId = userAccountUrl.getLastPathSegment();

                    Log.d("Steam","Got SteamID64: "+userId);
                    // Do whatever you want with the user's steam id

                    editor.putString("example_text",userId);
                    editor.commit();

                    activity.finish();

                }
            }
        });
    }
}