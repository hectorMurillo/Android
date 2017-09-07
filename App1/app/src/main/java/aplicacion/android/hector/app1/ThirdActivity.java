package aplicacion.android.hector.app1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    private EditText editTextPhone;
    private EditText editTextWeb;
    private ImageButton imgBtnPhone;
    private ImageButton imgBtnWeb;
    private ImageButton imgBtnCamera;
    //Se hace para poder manipular que servicio corresponde el numero es al azar
    private final int PHONE_CALL_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextWeb = (EditText) findViewById(R.id.editTextWeb);
        imgBtnPhone = (ImageButton) findViewById(R.id.imageButtonPhone);
        imgBtnWeb = (ImageButton) findViewById(R.id.imageButtonWeb);
        imgBtnCamera = (ImageButton) findViewById(R.id.imageButtonCamera);

        imgBtnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editTextPhone.getText().toString();
                if (phoneNumber != null) {
                    //Comprobar version de android que se esta corriendo
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                    } else {
                        OlderVersions(phoneNumber);
                    }
                }
            }

            private void OlderVersions(String phoneNumber) {
                //Accion, URI
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                if (checkPermission(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentCall);
                } else {
                    Toast.makeText(ThirdActivity.this, "Tu cel no tiene permiso", Toast.LENGTH_SHORT).show();
                }
            }
            /*private void NewerVersions(){
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},PHONE_CALL_CODE);
            }*/
        });

    }

    //Este metodo permite forzar que la accion del permiso funcione ya que el requestPermision es asincrono


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Estamos en el caso del telefono
        switch (requestCode) {
            case PHONE_CALL_CODE:
                String permission = permissions[0];
                int result = grantResults[0];
                if (permission.equals(Manifest.permission.CALL_PHONE)) {
                    //comprobar si ha sido aceptado o denegado la peticion de permiso
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        //Concedió su permiso
                        String phoneNumber = editTextPhone.getText().toString();
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(intentCall);
                    }else{
                        //No concedió su permiso
                        Toast.makeText(ThirdActivity.this,"No concedido",Toast.LENGTH_LONG);
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode,permissions,grantResults);
                break;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private boolean checkPermission(String permission){
        int resultado = this.checkCallingOrSelfPermission(permission);
        return resultado == PackageManager.PERMISSION_GRANTED;
    }
}
