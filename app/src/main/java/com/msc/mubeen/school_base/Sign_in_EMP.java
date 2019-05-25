package com.msc.mubeen.school_base;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.msc.mubeen.school_base.db.SQ_EMP;

public class Sign_in_EMP extends AppCompatActivity {

    EditText edt_user_name;
    EditText edt_password;

    Button btn_login,btn_signup;
    SQ_EMP sq_emp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in__emp);
        edt_user_name  =findViewById(R.id.signin_user_name);
        edt_password = findViewById(R.id.signin_password);
        sq_emp = new SQ_EMP(this);
        btn_login = findViewById(R.id.btn_sign_in);
        btn_signup  = findViewById(R.id.btn_sign_in_up);


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sign_in_EMP.this,SignUP_EMP.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String str_user_name = edt_user_name.getText().toString();
                String str_password  = edt_password.getText().toString();

                // ayesha admin
                if (str_password.equals("admin") && str_user_name.equals("admin"))
                {
                    startActivity(new Intent(Sign_in_EMP.this,ADMIN.class));
                }
                else
                {
                    int login_confrom = sq_emp.LOG_IN_EMP(edt_user_name.getText().toString(),edt_password.getText().toString());
                    if (login_confrom>0)
                    {
                        // ALERT_DIALOG("coreect" , "sdf " , "OK");
                        startActivity( new Intent(Sign_in_EMP.this,MainActivity.class)
                                .putExtra("user_name",edt_user_name.getText().toString())
                                .putExtra("passowrd",edt_password.getText().toString())
                                .putExtra(String.valueOf(R.string.teacher_id),login_confrom+"")
                                .putExtra(String.valueOf(R.string.parent_result),"no"));
                    }
                    else
                    {
                        ALERT_DIALOG("ALERT" , " INVALID YOUR DETAIL " , "OK");
                    }
                }



            }
        });



    }

    public void ALERT_DIALOG(String point,String note,String btn_name)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(Sign_in_EMP.this).create();
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
