package com.example.root.testlistapais;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.app.ListActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList <Pais> datos = new ArrayList<Pais>();
    String [] nombrePais;
    String [] dominioPais;
    String [] descripcion;

    ArrayList <Integer> imagenes = new ArrayList();
    ImageView imgBand ;


    ListView listita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nombrePais = getResources().getStringArray(R.array.array_paises);
        dominioPais = getResources().getStringArray(R.array.array_dominiosPaises);
        descripcion = getResources().getStringArray(R.array.array_descripcion);
        imagenes.add(R.drawable.alemania);
        imagenes.add(R.drawable.brasil);
        imagenes.add(R.drawable.canada);
        imagenes.add(R.drawable.china);
        imagenes.add(R.drawable.croacia);
        imagenes.add(R.drawable.cuba);
        imagenes.add(R.drawable.finlandia);
        imagenes.add(R.drawable.jamaica);
        imagenes.add(R.drawable.japon);
        imagenes.add(R.drawable.mexico);
        imagenes.add(R.drawable.noruega);
        imagenes.add(R.drawable.suiza);


        for (int i = 0; i < nombrePais.length; i++) {

            datos.add(new Pais(imagenes.get(i), nombrePais[i], dominioPais[i],descripcion[i]));
        }




        AdaptadorPaises adaptador = new AdaptadorPaises(this);

        listita = findViewById(R.id.listas);

        listita.setAdapter(adaptador);

        listita.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Pais selectedBandera = datos.get(position);

                Intent intent = new Intent(getApplicationContext(), descripcion.class);
                intent.putExtra("icono", selectedBandera.getIcono());
                intent.putExtra("nombre", selectedBandera.getNombre());
                intent.putExtra("dominio", selectedBandera.getDominio());
                intent.putExtra("descripcion",selectedBandera.getDescripcion());
                startActivity(intent);
            }
        });
    }

    class AdaptadorPaises extends ArrayAdapter<Pais> {

        public AdaptadorPaises(Context context) {
            super(context, R.layout.molde_lista, datos);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.molde_lista,parent,false);

            //Obtener Imagen y asignar a componente
            imgBand = (ImageView) item.findViewById(R.id.icono);
            imgBand.setImageDrawable(ContextCompat.getDrawable(getContext(),datos.get(position).getIcono()));


            TextView lblNombre = (TextView)item.findViewById(R.id.nombrePais);
            lblNombre.setText(datos.get(position).getNombre());

            TextView lblDomio = (TextView)item.findViewById(R.id.dominioPais);
            lblDomio.setText(datos.get(position).getDominio());

            return(item);
        }
    }
}
