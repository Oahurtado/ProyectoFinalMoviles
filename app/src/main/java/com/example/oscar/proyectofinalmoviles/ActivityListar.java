package com.example.oscar.proyectofinalmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class ActivityListar extends AppCompatActivity {
     Button IrMapa;
     EditText lat,longi,descri,nomb;
     TextView list;

     String cod;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

      IrMapa =(Button)findViewById(R.id.ButIrMapa);
      nomb=(EditText)findViewById(R.id.EditLugar);
      lat =(EditText)findViewById(R.id.EditLati);
      longi=(EditText)findViewById(R.id.EditLongi);
      descri=(EditText)findViewById(R.id.EditDescri);
      list=(TextView) findViewById(R.id.TextListar);

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
        String nombre= nomb.getText().toString().trim();

        Ubicaciones ubi = new Ubicaciones(lati,longit,descrip);
        if(lati.matches("")||longit.matches("")||descrip.matches("")){
            Toast.makeText(this,"Dede llenar todos los campos",Toast.LENGTH_SHORT).show();

        } else{
            myRef.child(cod).child(nombre).setValue(ubi);
            Toast.makeText(this,"Ubicación Ingresada",Toast.LENGTH_LONG).show();


            lat.setText("");
            longi.setText("");
            descri.setText("");

        }

    }
    public void listarUbicaciones(View l){

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> primeras = (Map<String, Object>) dataSnapshot.getValue();


                for (Map.Entry<String, Object> entry : primeras.entrySet()) {

                      Map<String, Object> value2 = (Map<String, Object>) entry.getValue();

                    for (Map.Entry<String, Object> entry1 : value2.entrySet()) {


                        Map<String, Object> value3 = (Map<String, Object>) entry1.getValue();
                        if (value3.get("descripcion") != null) {
                            list.append("\n" + value3.get("descripcion"));


                        }
                    }

                }// este for Recorre las keys de value


            }// termina el onDataChange

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }); // termina el onCancelled
    }

}
