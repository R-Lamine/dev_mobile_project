package com.example.e2507235.tp_list_etudiant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

    private ListView mainListView ;
    private ArrayAdapter<Etudiant> listAdapter ;
    private int position_var =-1;
    private int ID_AJOUTER =0;
    private int ID_MODIFIER=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupère le widget
        mainListView = (ListView) findViewById( R.id.listetudiant );
        // Initialise la variable qui contient les éléments affichés dans la ListView
        listAdapter = new ArrayAdapter<Etudiant>(this, R.layout.list_etudiant);
        // Associe la ListView à la variable contient les éléments de la ListView
        mainListView.setAdapter(listAdapter);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                                    position_var = position;

                                                    Etudiant e = listAdapter.getItem(position_var);
                                                    String name = e.getName();
                                                    String info = e.getNote_info();
                                                    String math = e.getNote_math();

                                                    EditText name1 = (EditText) findViewById(R.id.nom1);
                                                    EditText info1 = (EditText) findViewById(R.id.info1);
                                                    EditText math1 = (EditText) findViewById(R.id.math1);

                                                    name1.setText(name);
                                                    info1.setText(info);
                                                    math1.setText(math);


                                                }
                                            }
        );


        Button ajouter = (Button) findViewById(R.id.ajouter);
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent("projet2");
                startActivityForResult(i,ID_AJOUTER);

            }
        });

        Button modifier = (Button) findViewById(R.id.modifier);
        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent("projet2");





                startActivityForResult(i,ID_MODIFIER);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
