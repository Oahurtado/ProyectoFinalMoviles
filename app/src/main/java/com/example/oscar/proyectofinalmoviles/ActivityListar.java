package com.example.oscar.proyectofinalmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ActivityListar extends AppCompatActivity {
     Button IrMapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

      IrMapa =(Button)findViewById(R.id.ButIrMapa);


      IrMapa.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(ActivityListar.this, MapsActivity.class);
              startActivity(intent);
          }
      });
    }
}
