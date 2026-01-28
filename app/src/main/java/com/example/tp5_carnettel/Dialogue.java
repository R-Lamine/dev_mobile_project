package com.example.tp5_carnettel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.tp5_carnettel.Contact;

public class Dialogue extends Activity 
{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dlg);

		Intent iin= getIntent();
        Contact c = iin.getParcelableExtra("CONTACT");
        if (c!=null)
        {
            EditText nom = (EditText) findViewById(R.id.Nom);
            nom.setText(c.getNom());

            EditText prenom = (EditText) findViewById(R.id.Prenom);
            prenom.setText(c.getPrenom());

            EditText numero = (EditText) findViewById(R.id.Tel);
            numero.setText(c.getNumero());
        }

        Button btn=(Button)findViewById(R.id.Ok);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent;
                myIntent = new Intent();

                TextView nom = (TextView)findViewById(R.id.Nom);
                String strNom=nom.getText().toString();

                TextView prenom = (TextView)findViewById(R.id.Prenom);
                String strPrenom=prenom.getText().toString();

                TextView numero = (TextView)findViewById(R.id.Tel);
                String strNum=numero.getText().toString();

                Contact c = new Contact(strNom, strPrenom, strNum);
                myIntent.putExtra("CONTACT", c);

                setResult(RESULT_OK, myIntent);
                finish();
            }
        });

        btn=(Button)findViewById(R.id.Annuler);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
