package com.jhorje18.fingerprintpay;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pro100svitlo.fingerprintAuthHelper.FahErrorType;
import com.pro100svitlo.fingerprintAuthHelper.FahListener;
import com.pro100svitlo.fingerprintAuthHelper.FingerprintAuthHelper;

public class MainActivity extends AppCompatActivity implements FahListener {

    //Variables
    private FingerprintAuthHelper mFAH;

    private ImageView imgResultadoHuella;
    private TextView txtResultadoHuella;
    private AlertDialog dialogHuella;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Genera Lectura de Huellas
        mFAH = new FingerprintAuthHelper
                .Builder(this, this) //(Context inscance of Activity, FahListener)
                .build();

        if (mFAH.isHardwareEnable()) {
            //Dispositivo compatible!
        } else {
            //Dispositivo no compatible
        }

        //Preparamos AlertDialog Pago
        View viewDialogo = getLayoutInflater().inflate(R.layout.dialogo_fingerprint, null);
        txtResultadoHuella = viewDialogo.findViewById(R.id.txtResultadoHuella);
        imgResultadoHuella = viewDialogo.findViewById(R.id.imgResultadoHuella);

        AlertDialog.Builder builder = new AlertDialog.Builder(this, 0)
                .setTitle("Autentificate mediante la huella")
                .setView(viewDialogo)
                .setMessage("Coloque su dedo en el sensor de huellas");
        dialogHuella = builder.create();
    }

    @Override
    public void onFingerprintStatus(boolean authSuccessful, int errorType, CharSequence errorMess) {
        // authSuccessful - boolean that shows auth status
        // errorType - if auth was failed, you can catch error type
        // errorMess - if auth was failed, errorMess will tell you (and user) the reason

        if (authSuccessful) {
            // do some stuff here in case auth was successful
        } else if (mFAH != null) {
            // do some stuff here in case auth failed
            switch (errorType) {
                case FahErrorType.General.LOCK_SCREEN_DISABLED:
                case FahErrorType.General.NO_FINGERPRINTS:
                    mFAH.showSecuritySettingsDialog();
                    break;
                case FahErrorType.Auth.AUTH_NOT_RECOGNIZED:
                    //do some stuff here
                    break;
                case FahErrorType.Auth.AUTH_TO_MANY_TRIES:
                    //do some stuff here
                    break;
            }
        }
    }

    @Override
    public void onFingerprintListening(boolean listening, long milliseconds) {
        // listening - status of fingerprint listen process
        // milliseconds - timeout value, will be > 0, if listening = false & errorType = AUTH_TO_MANY_TRIES

        if (listening) {
            //add some code here
        } else {
            //add some code here
        }
        if (milliseconds > 0) {
            //if u need, u can show timeout for user
        }
    }
}
