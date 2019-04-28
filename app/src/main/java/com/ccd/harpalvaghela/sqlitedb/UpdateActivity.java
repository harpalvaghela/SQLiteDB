package com.ccd.harpalvaghela.sqlitedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText name,email;
    Button btnl;
    String sid,sname,semail,newname,newemail;
    helper h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name=(EditText)findViewById(R.id.edtname);
        email=(EditText)findViewById(R.id.edtemail);
        btnl=(Button)findViewById(R.id.btn_update);

        Intent i=getIntent();
        sid=i.getStringExtra("id");
        sname=i.getStringExtra("name");
        semail=i.getStringExtra("email");


        h=new helper(UpdateActivity.this);
        name.setText(sname);
        email.setText(semail);

        btnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newname=name.getText().toString().trim();
                newemail=email.getText().toString().trim();
                h.update(sid,newname,newemail);

                Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                finish();
                Intent i=new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
}
