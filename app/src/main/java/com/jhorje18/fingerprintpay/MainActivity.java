package com.jhorje18.fingerprintpay;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jhorje18.fingerprintpay.Adaptador.AdaptadorCompras;
import com.jhorje18.fingerprintpay.Objetos.Producto;
import com.pro100svitlo.fingerprintAuthHelper.FahErrorType;
import com.pro100svitlo.fingerprintAuthHelper.FahListener;
import com.pro100svitlo.fingerprintAuthHelper.FingerprintAuthHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FahListener {

    //Variables
    public FingerprintAuthHelper mFAH;

    private ImageView imgResultadoHuella;
    private TextView txtResultadoHuella;
    public AlertDialog dialogHuella;
    private ListView listViewProductos;

    private ArrayList<Producto> listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Controlador Vista
        listViewProductos = (ListView) findViewById(R.id.listPrincipal);

        //Iniciar ArrayList
        listaProductos = new ArrayList<Producto>();

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
                .setCancelable(false)
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        mFAH.stopListening();
                    }
                })
                .setMessage("Coloque su dedo en el sensor de huellas");
        dialogHuella = builder.create();

        //Preparamos ListView
        añadirProducto(new Producto("OnePlus 5T", "Móvil OnePlus 5T en color negro con 128Gb", 500, "https://gloimg.gbtcdn.com/gb/pdm-product-pic/Electronic/2017/12/09/goods-img/1513904681072322709.jpg"));
        añadirProducto(new Producto("iPhone X", "Móvil Appel iPhone X con 128Gb", 1000, "https://store.storeimages.cdn-apple.com/4662/as-images.apple.com/is/image/AppleInc/aos/published/images/i/ph/iphone/x/iphone-x-silver-select-2017?wid=305&hei=358&fmt=jpeg&qlt=95&op_usm=0.5,0.5&.v=1515602510472"));
    }

    public void añadirProducto(Producto nuevo){
        listaProductos.add(nuevo);
        recargarLista();
    }

    public void recargarLista() {
        AdaptadorCompras adapter;
        adapter = new AdaptadorCompras(this, listaProductos);
        listViewProductos.setAdapter(adapter);
    }

    @Override
    public void onFingerprintStatus(boolean authSuccessful, int errorType, CharSequence errorMess) {
        // authSuccessful - boolean that shows auth status
        // errorType - if auth was failed, you can catch error type
        // errorMess - if auth was failed, errorMess will tell you (and user) the reason

        if (authSuccessful) {
            // do some stuff here in case auth was successful
            txtResultadoHuella.setText("Huella reconocida");
            imgResultadoHuella.setColorFilter(Color.GREEN);
            imgResultadoHuella.setImageResource(R.drawable.ic_check_black_24dp);
            txtResultadoHuella.setTextColor(Color.GREEN);
        } else if (mFAH != null) {
            // do some stuff here in case auth failed
            switch (errorType) {
                case FahErrorType.General.LOCK_SCREEN_DISABLED:
                    break;
                case FahErrorType.General.NO_FINGERPRINTS:
                    mFAH.showSecuritySettingsDialog();
                    break;
                case FahErrorType.Auth.AUTH_NOT_RECOGNIZED:
                    txtResultadoHuella.setText("Huella no reconocida");
                    imgResultadoHuella.setColorFilter(Color.RED);
                    imgResultadoHuella.setImageResource(R.drawable.ic_close_black_24dp);
                    txtResultadoHuella.setTextColor(Color.RED);
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
            int seconds = (int) (milliseconds / 1000) % 60 ;
            int minutes = (int) ((milliseconds / (1000*60)) % 60);

            txtResultadoHuella.setText("Espere " + minutes + ":" + seconds + " para volver a intentarlo");
            imgResultadoHuella.setColorFilter(Color.GRAY);
            imgResultadoHuella.setImageResource(R.drawable.ic_fingerprint_black_24dp);
            txtResultadoHuella.setTextColor(Color.GRAY);
        }
    }
}
