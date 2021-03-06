package com.example.oscar.proyectofinalmoviles;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class ActivityListar extends AppCompatActivity {
     Button IrMapa;
     Button volver;
     EditText lat,longi,descri,nomb;
     TextView list;
    String iplocal="http://localhost/Coordenadas/insertar_coordenada.php";


     String cod,nombreUbi;
     int cont=0;

    AdView mAdView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        IrMapa = (Button) findViewById(R.id.ButIrMapa);
        volver = (Button) findViewById(R.id.btnVolver);
        nomb = (EditText) findViewById(R.id.EditLugar);
        lat = (EditText) findViewById(R.id.EditLati);
        longi = (EditText) findViewById(R.id.EditLongi);
        descri = (EditText) findViewById(R.id.EditDescri);
        list = (TextView) findViewById(R.id.TextListar);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest=new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);


        Bundle reciCod = getIntent().getExtras();
        cod = reciCod.getString("Codigo");
        IrMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityListar.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        volver.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                volver();

            }
        });
    }


    public void RegistrarUbicacion(View r){
        String lati = lat.getText().toString().trim();
        String longit = longi.getText().toString().trim();
        String descrip = descri.getText().toString().trim();
        String nombre= nomb.getText().toString().trim();

        Ubicaciones ubi = new Ubicaciones(lati,longit);
        if(lati.matches("")||longit.matches("")||descrip.matches("")){
            Toast.makeText(this,"Dede llenar todos los campos",Toast.LENGTH_SHORT).show();

        } else{
            myRef.child(cod).child(nombre).child("descripcion"+cod).setValue(descrip);
            myRef.child(cod).child(nombre).child("latitud").setValue(lati);
            myRef.child(cod).child(nombre).child("longitud").setValue(longit);
            Toast.makeText(this,"Ubicación Ingresada",Toast.LENGTH_LONG).show();


            lat.setText("");
            longi.setText("");
            descri.setText("");
            nomb.setText("");

        }

    }

    public void listarUbicaciones(View l){
        ListarBloques Listar = new ListarBloques();
        Listar.execute();

    }

    private class ListarBloques extends AsyncTask<Void,Integer,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            final String ubi = "descripcion"+cod;
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Map<String, Object> primeras = (Map<String, Object>) dataSnapshot.getValue();


                    for (Map.Entry<String, Object> entry : primeras.entrySet()) {

                        Map<String, Object> value2 = (Map<String, Object>) entry.getValue();


                        for (Map.Entry<String, Object> entry1 : value2.entrySet()) {

                            Map<String, Object> value3 = (Map<String, Object>) entry1.getValue();
                            if ( value3.get("descripcion") != null ) {
                                list.append("\n" + value3.get("descripcion"));

                            }
                            if (value3.get(ubi) != null){
                                list.append("\n" + value3.get(ubi));

                            }


                        }// este for Recorre las keys de value

                    }

                }// termina el onDataChange

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            }); // termina el onCancelled
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            list.append(""+values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(getApplicationContext(),"Tus ubicaciones fueran listadas",Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
        }
    }

    public void volver(){


        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("Esta seguro que desea cerrar sesión ");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                Intent intent = new Intent(ActivityListar.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Gracias por utilizar nuestra aplicación",Toast.LENGTH_SHORT).show();

            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                dialogo1.cancel();

            }
        });
        dialogo1.show();
}


    public void ingresarSQL(View i){
        Toast.makeText(getApplicationContext(),"si entra al boton",Toast.LENGTH_SHORT).show();

        ingreSQL ing = new ingreSQL() ;
        ing.execute(iplocal,lat.getText().toString(),longi.getText().toString(),descri.getText().toString());
       // ing.execute();
    }
    public  class  ingreSQL extends AsyncTask<String,Void,String>{
        String mensaje="";
        @Override

        protected String doInBackground(String... params) {
              URL serviciolocal= null;
            try {
               /* URL serviciolocal = new URL(iplocal);
                HttpURLConnection conexion = (HttpURLConnection) serviciolocal.openConnection();*/
               HttpURLConnection conexion;
                DataOutputStream printout;
                DataInputStream input;
                serviciolocal = new URL(iplocal);
                conexion =(HttpURLConnection) serviciolocal.openConnection();
               conexion.setDoInput(true);
               conexion.setDoOutput(true);
               conexion.setUseCaches(false);
                conexion.setRequestProperty("Content-Type","application/json");
                conexion.setRequestProperty("Accept","application/json");

                conexion.connect();
                JSONObject jsonparametros = new JSONObject();
                jsonparametros.put("latitud",params[1]);
                jsonparametros.put("longitud",params[2]);
                jsonparametros.put("descripcion",params[3]);

                OutputStream os = conexion.getOutputStream();

                BufferedWriter escribir = new BufferedWriter(
                        new OutputStreamWriter(os,"UTF-8"));
                escribir.write(jsonparametros.toString());
                escribir.flush();
                escribir.close();
                int conectado = conexion.getResponseCode();
                StringBuilder resultado = new StringBuilder();

                if (conectado== HttpURLConnection.HTTP_OK){
                    String linea ;

                    // InputStream leer = new BufferedInputStream(conexion.getInputStream());
                    BufferedReader json = new BufferedReader(new InputStreamReader(conexion.getInputStream()));

                    while ((linea=json.readLine())!=null){
                        resultado.append(linea);
                    }
                    JSONObject respuestajson = new JSONObject(resultado.toString());
                    String resulJSON = respuestajson.getString("estado");
                    if(resulJSON=="1"){
                        mensaje ="Coordenada insertado con exito";
                    }else{

                        mensaje ="Coordenada no insertada";

                    }

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();


            }

            return mensaje ;

        }

        @Override
        protected void onPostExecute(String s) {
            list.setText(s);
            Toast.makeText(getApplicationContext(),"si entra al hilo",Toast.LENGTH_SHORT).show();

           // super.onPostExecute(s);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }
    }


}
