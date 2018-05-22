package com.example.opilane.mapsgoogle;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
    // log jaoks loome TAGI
    private static final String TAG = "mainActivity";
    // google play service error handler
    private static final int ERROR_DIALOG_REQUEST= 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //loome..
    private void kaivita(){
        Button kaart = findViewById(R.id.btnKaart);
        kaart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaataKaarti = new Intent( MainActivity.this, MapActivity.class);
                startActivity(vaataKaarti);
            }
        });

    }
    public boolean ServicesOK(){
        Log.d(TAG, "ServicesOK: Kontrollime google services versiooni");
        int oigeVersioon = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if (oigeVersioon == ConnectionResult.SUCCESS);{
            // koik on korras ja kasutaja saab teha kaardi päringuid
            Log.d(TAG, "ServicesOK:ServicesOK: Google Play Services töötab");
            return true;

        }
        else if  (GoogleApiAvailability.getInstance().isUserResolvableError(oigeVersioon)){
            //tekkis viga
            Log.d(TAG, "ServicesOK: esines viga ...");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, oigeVersioon, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else   {
            Toast.makeText(this, "kaardi päringute tegemine pole võimalik", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
