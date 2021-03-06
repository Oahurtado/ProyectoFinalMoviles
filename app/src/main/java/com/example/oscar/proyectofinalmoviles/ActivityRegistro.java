package com.example.oscar.proyectofinalmoviles;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ActivityRegistro extends AppCompatActivity {
    EditText nombre, codigo, contraseña;
    Button botonVolver;
    AdView mAdView;
    String iplocal="http://localhost/Coordenadas/insertar_coordenada.php";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
   // DatabaseReference usuario = FirebaseDatabase.getInstance().getReference().child("com.example.oscar.proyectofinalmoviles.Usuario");
    DatabaseReference usuario = FirebaseDatabase.getInstance().getReference().child("Usuarios");
    Hash md5;
    String contraCifrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombre = (EditText)findViewById(R.id.txtNombre);
        codigo = (EditText)findViewById(R.id.txtCodigo);
        contraseña = (EditText)findViewById(R.id.txtContraseña);
        botonVolver= (Button)findViewById(R.id.btnVolver);
        md5 = new Hash();
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActivityRegistro.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }



    public void ingresar(View b){


        String nom = nombre.getText().toString().trim();
        String cod = codigo.getText().toString().trim();
        String contra = contraseña.getText().toString().trim();
       // Toast.makeText(this,"entro 1",Toast.LENGTH_LONG).show();

        contraCifrada=md5.md5(contra);


       Usuario usu= new Usuario(nom,cod,contraCifrada);
       if(nom.matches("")||cod.matches("")||contra.matches("")){
            Toast.makeText(this,"Dede llenar todos los campos",Toast.LENGTH_LONG).show();
            System.out.print("entro 1");
        } else{
            myRef.child("Usuarios").child(cod).setValue(usu);
            Toast.makeText(this,"Usuario ingresado",Toast.LENGTH_LONG).show();
            System.out.print("entro 2");

            nombre.setText("");
            codigo.setText("");
            contraseña.setText("");
        }
    }
    public void ingresarSQL(View i){

        ingreSQL in = new ingreSQL();


        in.execute();
        Toast.makeText(getApplicationContext(),"si entra al boton",Toast.LENGTH_SHORT).show();
    }
    public  class  ingreSQL extends AsyncTask<Void,String,String>{

        @Override

        protected String doInBackground(Void... voids) {
            try {
                URL serviciolocal = new URL(iplocal);
                HttpURLConnection conexion = (HttpURLConnection) serviciolocal.openConnection();
                conexion.setRequestProperty("User-Agent","Mozilla/5.0"+
                        "(Linuz;Android 1.5; es-Es) Ejemplo Uceva Http");
                int conectado = conexion.getResponseCode();
                if (conectado== HttpURLConnection.HTTP_OK){

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getApplicationContext(),"si entra al hilo",Toast.LENGTH_SHORT).show();

            super.onPostExecute(s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

}
