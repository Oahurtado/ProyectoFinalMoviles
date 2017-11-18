package com.example.oscar.proyectofinalmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityListar extends AppCompatActivity {
     Button IrMapa;
     EditText lat,longi,descri,nom;
     String cod;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

      IrMapa =(Button)findViewById(R.id.ButIrMapa);
      lat =(EditText)findViewById(R.id.EditLati);
      longi=(EditText)findViewById(R.id.EditLongi);
      descri=(EditText)findViewById(R.id.EditDescri);
      Bundle reciCod = getIntent().getExtras();
      cod = reciCod.getString("Codigo");
      IrMapa.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(ActivityListar.this, MapsActivity.class);
              startActivity(intent);
          }
      });


    }

    public void RegistrarUbicacion(View r){
        String lati = lat.getText().toString().trim();
        String longit = longi.getText().toString().trim();
        String descrip = descri.getText().toString().trim();


        Ubicaciones ubi = new Ubicaciones(lati,longit,descrip);
        if(lati.matches("")||longit.matches("")||descrip.matches("")){
            Toast.makeText(this,"Dede llenar todos los campos",Toast.LENGTH_SHORT).show();

        } else{
            myRef.child("UbicacionesPersonales").child(cod).setValue(ubi);
            Toast.makeText(this,"Ubicación Ingresada",Toast.LENGTH_LONG).show();


            lat.setText("");
            longi.setText("");
            descri.setText("");

        }

    }

}
