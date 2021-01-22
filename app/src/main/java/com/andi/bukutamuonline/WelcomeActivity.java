package com.andi.bukutamuonline;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    Button btnKeluar;
    EditText nama,tujuan,tgl;
    TextView textView;
    DB_Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnKeluar = (Button) findViewById(R.id.btnKeluar);
        nama = (EditText)findViewById(R.id.nama_input);
        tujuan = (EditText)findViewById(R.id.tujuan_input);
        tgl = (EditText)findViewById(R.id.tgl_input);
        textView = (TextView)findViewById(R.id.textView);

        controller = new DB_Controller(this,"",null,1);

        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //perintah untuk mengakhiri aplikasi
                finish();
            }
        });
    }

    public void btn_click(View view){
        switch(view.getId()){
            case R.id.btn_add:
                try {
                    controller.insert_tamu(nama.getText().toString(),tujuan.getText().toString(),tgl.getText().toString());
                }catch (SQLiteException e){
                    Toast.makeText(WelcomeActivity.this, "ALREADY EXIST", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_delete:
                controller.delete_tamu(nama.getText().toString());
                break;
            case R.id.btn_update:
                AlertDialog.Builder dialog = new AlertDialog.Builder(WelcomeActivity.this);
                dialog.setTitle("MASUKAN NAMA BARU ");

                final EditText new_nama = new EditText(this);
                dialog.setView(new_nama);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        controller.update_tamu(nama.getText().toString(),new_nama.getText().toString());
                    }
                });
                dialog.show();
                break;
            case R.id.btn_list:
                controller.list_all_tamu(textView);
                break;
        }

    }
}