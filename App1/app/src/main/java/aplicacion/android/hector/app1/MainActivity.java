package aplicacion.android.hector.app1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity{

    private Button _btn;
    //Todos los widgets heredan del view pero no se obtienen los metodos puntuales
    private final String GETTER ="La compra fue realizada satisfactoriamente!.";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _btn = (Button)findViewById(R.id.buttonMain);

        _btn = (Button)findViewById(R.id.buttonMain);

        _btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Espera un momento...", Toast.LENGTH_LONG).show();
                //Acceder al segundo activity [contexto actual, secondactivity[clase]]
                //Existen 2 formas de hacer el acceso a un activity implicitamente[no sabemos que va a llevar la accion
                // ] o explicitamente[especificos]
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("getter",GETTER);
                startActivity(intent);
            }
        });

    }
}
