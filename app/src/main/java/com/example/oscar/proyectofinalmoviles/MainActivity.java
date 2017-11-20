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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();




    AdView mAdView;
    EditText codigoUsu, contraseña;
    Button botonLogin, botonRegistro;
    String cod, contra, nomBD,contraBd="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        codigoUsu = (EditText) findViewById(R.id.txtUsuario);
        contraseña = (EditText) findViewById(R.id.txtContraseña);
        botonLogin = (Button) findViewById(R.id.btnLogin);
        botonRegistro = (Button) findViewById(R.id.btnRegistro);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
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

     public void login(View g){
        cod=codigoUsu.getText().toString().trim();
        contra = contraseña.getText().toString().trim();
        FirebaseDatabase data = FirebaseDatabase.getInstance();
        DatabaseReference myRef = data.getReference("Usuarios").child(cod);// vamos la USUARIO  y a la identificacion ingresada
        //Toast.makeText(this, "Entro", Toast.LENGTH_LONG).show();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                contraBd = dataSnapshot.child("contraseña").getValue().toString();/*una vez estamos dentro de los atributos del usuario con la identificacion
                                                                                    ingresada nos vamos al atributo Contraseña y la guardamos en contrBD para luego
                                                                                        compararla con la que se ingreso que seta en Ide*/
                nomBD=dataSnapshot.child("nombre").getValue().toString();
                if(contra.matches(contraBd)){

                    Intent inte = new Intent(MainActivity.this,ActivityListar.class);
                    inte.addFlags(inte.FLAG_ACTIVITY_CLEAR_TOP | inte.FLAG_ACTIVITY_CLEAR_TASK);
                    Bundle codigo = new Bundle();
                    codigo.putString("Codigo",cod);
                    inte.putExtras(codigo);

                    startActivity(inte);
                    Toast.makeText(getApplicationContext(),"Bienvenido "+ nomBD,Toast.LENGTH_LONG).show();

                     codigoUsu.setText("");
                     contraseña.setText("");
                }else {
                    Toast.makeText(getApplicationContext(),"Contraseña incorrecta",Toast.LENGTH_SHORT).show();
                    codigoUsu.setText("");
                    contraseña.setText("");
                }
            }

            @Override
            public void onCancelled(DatabaseError e) {
                codigoUsu.setText(""+ e.toException());
            }
        });


    }

}
