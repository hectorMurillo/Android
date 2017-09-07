package aplicacion.android.hector.app1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    private TextView textView;
    private ImageButton btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (TextView)findViewById(R.id.textViewMain);
        btnNext = (ImageButton)findViewById(R.id.buttonGoSharing);

        //Tomar los datos del intent, a traves del bundle [caja que los contiene]

        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.getString("getter")!= null){
            String getter = bundle.getString("getter");
            Toast.makeText(SecondActivity.this, getter,Toast.LENGTH_LONG).show();
            textView.setText(getter);
        }else{
            Toast.makeText(SecondActivity.this,"Empty",Toast.LENGTH_LONG).show();
        }
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
}
