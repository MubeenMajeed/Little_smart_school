package com.msc.mubeen.school_base;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.msc.mubeen.school_base.db.SQ_EMP;

public class LOGIN_PARENT extends AppCompatActivity {

    EditText edt_phone,edt_password;

    Button btn_login,btn_close;
    SQ_EMP sq_emp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__parent);

        sq_emp = new SQ_EMP(this);

        edt_password = findViewById(R.id.parent_password);
        edt_phone = findViewById(R.id.parent_phone);

        btn_close = findViewById(R.id.parent_btn_close);
        btn_login = findViewById(R.id.parent_btn_sign_in);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_phone = edt_phone.getText().toString();
                String str_passowrd = edt_password.getText().toString();

                if (str_passowrd.equals("")|| str_phone.equals(""))
                {
                    Toast.makeText(LOGIN_PARENT.this, "WRITE PHONE AND PASSWORD", Toast.LENGTH_SHORT).show();
                }
                else
                {
                   int result = sq_emp.LOG_IN_PARENT(str_phone,str_passowrd);

                   if (result>0)
                   {
                       startActivity(new Intent(LOGIN_PARENT.this,PARENT_DASHBOARD.class)
                       .putExtra(String.valueOf(R.string.parent_id),result+"")
                       .putExtra(String.valueOf(R.string.parent_phone),str_phone)
                       .putExtra(String.valueOf(R.string.parent_result),"yes"));
                   }
                   else
                   {
                       ALERT_DIALOG("ALERT","INVALID DEATILS","OK");
                   }
                }

            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    public void ALERT_DIALOG(String point,String note,String btn_name)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(LOGIN_PARENT.this).create();
        alertDialog.setTitle(point);
        alertDialog.setMessage(note);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, btn_name,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
