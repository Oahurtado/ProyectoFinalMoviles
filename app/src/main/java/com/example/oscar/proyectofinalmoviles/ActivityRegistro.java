package com.example.oscar.proyectofinalmoviles;

import android.content.Intent;
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

public class ActivityRegistro extends AppCompatActivity {
    EditText nombre, codigo, contraseña;
    Button botonVolver;
    AdView mAdView;
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
}
