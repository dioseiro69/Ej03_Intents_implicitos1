package com.android.alejandro.ej03_intents_implicitos1;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private EditText textourl,textonumero,textobuscar;
    private Button botonURL,botonllamar,botonfoto,botonbuscar;
    private ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializar();
        accionBotones();


    }
    public void inicializar()
    {
        textourl= (EditText) findViewById(R.id.editTextURL);
        botonURL= (Button) findViewById(R.id.buttonAbrirURL);
        textonumero= (EditText) findViewById(R.id.editTextNumero);
        botonllamar= (Button) findViewById(R.id.buttonTelefono);
        botonfoto= (Button) findViewById(R.id.buttonFoto);
        textobuscar= (EditText) findViewById(R.id.editTextBuscar);
        botonbuscar= (Button) findViewById(R.id.buttonBuscar);
        imagen = (ImageView) findViewById(R.id.imagen);

    }




    public void accionBotones()
    {
        botonURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://"+textourl.getText().toString()));
                startActivity(i);
            }
        });

        botonllamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel://"+textonumero.getText().toString()));
                startActivity(i);
            }
        });

        botonfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(i,1234);
            }
        });

        botonbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_WEB_SEARCH);
                        i.putExtra(SearchManager.QUERY,textobuscar.getText().toString());
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1234&&resultCode==RESULT_OK)
        {
            Bitmap foto=(Bitmap)data.getExtras().get("data");
            imagen.setImageDrawable(new BitmapDrawable(foto));
        }

    }
}
