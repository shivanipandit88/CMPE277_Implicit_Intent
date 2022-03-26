package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText website, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        website = (EditText) findViewById(R.id.editText);
        phone = (EditText) findViewById(R.id.editText2);
    }

    public void go_to_website(View view) {
        String s = website.getText().toString();
        if (!s.startsWith("https://") && !s.startsWith("http://")){
            s = "http://" + s;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
        startActivity(intent);
    }

    public void call_to_phone(View view) {
        String num = phone.getText().toString();
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:".concat(num)));
        //Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:34786742678"));
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        else {
            if (num.trim().length() > 0) {
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
