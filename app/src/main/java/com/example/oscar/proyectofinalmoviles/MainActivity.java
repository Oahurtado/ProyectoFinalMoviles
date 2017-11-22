package com.example.oscar.proyectofinalmoviles;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    EditText codigoUsu, contraseña;
    Button botonLogin, botonRegistro;
    String cod, contra, nomBD,contraBd="";
    AdView mAdView;
    Hash md5;
    String contraCifrada;

    boolean flagSinc = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        codigoUsu = (EditText) findViewById(R.id.txtUsuario);
        contraseña = (EditText) findViewById(R.id.txtContraseña);
        botonLogin = (Button) findViewById(R.id.btnLogin);
        botonRegistro = (Button) findViewById(R.id.btnRegistro);
        md5 =new Hash();

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest=new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);


        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ActivityRegistro.class);
                startActivity(intent);
            }
        });
    }

    public void ejecutar(View s) {
        cod = codigoUsu.getText().toString().trim();
        contra = contraseña.getText().toString().trim();

        if (cod.matches("") && contra.matches("")) {
            Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_LONG).show();
        } else {
            boolean isMobile;
            boolean isWiFi = true;
            //Datos móviles
            //Toast.makeText(getApplicationContext(),"entro ejecutar ",Toast.LENGTH_SHORT).show();
            boolean isConnected = false;
            ConnectivityManager cm =
                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) {
                isConnected = activeNetwork.isConnectedOrConnecting();
            }

            if (activeNetwork != null && isConnected) {
                isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
                isMobile = activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;

                if (isMobile) {
                    AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
                    dialogo1.setTitle("Importante");
                    dialogo1.setMessage("¿Los datos móviles estan activos desea continuar ellos?");
                    dialogo1.setCancelable(false);
                    dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            //Toast.makeText(getApplicationContext(),"dirigiendose a login ",Toast.LENGTH_SHORT).show();
                            login();
                        }
                    });
                    dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            dialogo1.cancel();
                            Toast.makeText(getApplicationContext(), "Desactiva los datos y activa el wifi", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialogo1.show();
                } else {
                    if (isWiFi) {
                        login();
                        //Toast.makeText(this, "Estamos en login sin machetear : " + isConnected, Toast.LENGTH_LONG).show();
                    }
                }
            } else {

                Toast.makeText(this, "No hay conexión : " + isConnected, Toast.LENGTH_SHORT).show();

            }
        }
    }


    public void login() {


        //Login
        cod = codigoUsu.getText().toString().trim();
        contra = contraseña.getText().toString().trim();
        contraCifrada = md5.md5(contra);
        FirebaseDatabase data = FirebaseDatabase.getInstance();
        DatabaseReference myRef = data.getReference("Usuarios").child(cod);// vamos la USUARIO  y a la identificacion ingresada
        //Toast.makeText(this, "Entro", Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(),"entro antes del listener",Toast.LENGTH_SHORT).show();


            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    contraBd = dataSnapshot.child("contraseña").getValue().toString();/*una vez estamos dentro de los atributos del usuario con la identificacion
                                                                                    ingresada nos vamos al atributo Contraseña y la guardamos en contrBD para luego
                                                                                        compararla con la que se ingreso que seta en Ide*/
                    //     Toast.makeText(getApplicationContext(),"entro antes de comparar",Toast.LENGTH_SHORT).show();
                    nomBD = dataSnapshot.child("nombre").getValue().toString();

                    if (contraCifrada.matches(contraBd)) {

                        Intent inte = new Intent(MainActivity.this, ActivityListar.class);
                        inte.addFlags(inte.FLAG_ACTIVITY_CLEAR_TOP | inte.FLAG_ACTIVITY_CLEAR_TASK);
                        Bundle codigo = new Bundle();
                        codigo.putString("Codigo", cod);
                        inte.putExtras(codigo);
                        startActivity(inte);
                        Toast.makeText(getApplicationContext(), "Bienvenido " + nomBD, Toast.LENGTH_LONG).show();
                        codigoUsu.setText("");
                        contraseña.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        codigoUsu.setText("");
                        contraseña.setText("");
                    }
                }

                @Override
                public void onCancelled(DatabaseError e) {
                    codigoUsu.setText("" + e.toException());
                }
            });

            //Toast.makeText(getApplicationContext(),"finalizo listener",Toast.LENGTH_SHORT).show();

    }






}
