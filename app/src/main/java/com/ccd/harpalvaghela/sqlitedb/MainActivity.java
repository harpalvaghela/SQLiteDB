package com.ccd.harpalvaghela.sqlitedb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    Button btn;
    EditText name,email;
    String snm,seid,sid,semail,sname;
    ArrayList<pojo> list;
    helper h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=(EditText)findViewById(R.id.edt_email);
        name=(EditText)findViewById(R.id.edt_name);
        lv=(ListView)findViewById(R.id.lv);
        btn=(Button)findViewById(R.id.btn);

        h=new helper(MainActivity.this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seid=email.getText().toString().trim();
                snm=name.getText().toString().trim();

                if(seid.equals("")||snm.equals("")){
                    Toast.makeText(MainActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    h.insert(snm,seid);

                    Intent i=new Intent(MainActivity.this,MainActivity.class);
                    startActivity(i);
                }



            }
        });

        list=h.getdata();
        adapter adp=new adapter(MainActivity.this,list);
        lv.setAdapter(adp);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                pojo p=(pojo)list.get(position);
                sid=p.getId();
                sname=p.getName();
                semail=p.getEmail();

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Want to delete or update ?");
                builder.setMessage("Select any one operation");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        h.delete(sid);
                        Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(MainActivity.this,MainActivity.class);
                        startActivity(i);
                    }
                });
                builder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent i=new Intent(MainActivity.this,UpdateActivity.class);
                        i.putExtra("id",sid);
                        i.putExtra("name",sname);
                        i.putExtra("email",semail);
                        startActivity(i);
                    }
                });
                builder.show();
            }
        });
    }
}
