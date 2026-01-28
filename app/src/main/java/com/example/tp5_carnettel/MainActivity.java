package com.example.tp5_carnettel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.example.tp5_carnettel.Contact;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity 
{
	private static final  int AJOUTER=0;
	private static final  int MODIFIER=1;

	private ListView mainListView ;
	private ArrayAdapter<Contact> listAdapter ;
	private int selectedItem;

    String nomFichier;
    String contenu;

    private EditText editNomFichier;
    private EditText editContenu;

    @Override
	protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        editNomFichier = (EditText) findViewById(R.id.fichier);
        editContenu = (EditText) findViewById(R.id.multitext);
		
		mainListView = (ListView) findViewById( R.id.list );
		listAdapter = new ArrayAdapter<Contact>(this, R.layout.item);
		mainListView.setAdapter( listAdapter );
		selectedItem=-1;



		 mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                 selectedItem = position;
             }
         });

        Button b=(Button)findViewById(R.id.add);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent request = new Intent(MainActivity.this, Dialogue.class);
                startActivityForResult(request, AJOUTER);
            }
        });

        b=(Button)findViewById(R.id.delete);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem > -1 && selectedItem<listAdapter.getCount())
                    listAdapter.remove(listAdapter.getItem(selectedItem));
            }
        });

        b=(Button)findViewById(R.id.modifiy);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem > -1 && selectedItem<listAdapter.getCount())
                {
                    Contact c=listAdapter.getItem(selectedItem);
                    Intent request =new Intent(MainActivity.this, Dialogue.class);
                    request.putExtra("CONTACT", c);
                    startActivityForResult(request, MODIFIER);
                }
            }
        });

        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                nomFichier = editNomFichier.getText().toString();
                contenu = editContenu.getText().toString();

                try {
                    // créer un flux pour écrire dans le fichier
                    OutputStreamWriter out = new OutputStreamWriter(openFileOutput(nomFichier, MODE_PRIVATE));
                    BufferedWriter buf = new BufferedWriter(out);

                    // écrire le texte dans le fichier
                    buf.write(contenu);

                    // fermer le flux
                    buf.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }




            }
        });


        Button load = (Button) findViewById(R.id.load);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomFichier = editNomFichier.getText().toString();

                try {
                    // ouvrir le fichier en lecture
                    InputStream in = openFileInput(nomFichier);
                    InputStreamReader inputReader = new InputStreamReader(in);
                    BufferedReader reader = new BufferedReader(inputReader);

                    // variable pour stocker le texte lu
                    StringBuilder contenuLu = new StringBuilder();
                    String ligne;

                    // lire le fichier ligne par ligne
                    while ((ligne = reader.readLine()) != null) {
                        contenuLu.append(ligne);
                        contenuLu.append("\n"); // ajoute un retour à la ligne
                    }

                    // fermer le flux
                    reader.close();

                    // mettre le texte lu dans l'EditText multiligne
                    editContenu.setText(contenuLu.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }



            }
        });

    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		switch(requestCode)
		{
		case AJOUTER:
			if(resultCode == RESULT_OK)
			{
                Contact c = data.getParcelableExtra("CONTACT");
                listAdapter.add( c );
	   		}
			break;
		case MODIFIER:
			if (resultCode == RESULT_OK)
			{
                Contact c = data.getParcelableExtra("CONTACT");
                listAdapter.remove(listAdapter.getItem(selectedItem));
                listAdapter.insert(c,selectedItem);
			}
			break;
		}
	}	
}
