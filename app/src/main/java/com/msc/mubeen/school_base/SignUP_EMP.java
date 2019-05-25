package com.msc.mubeen.school_base;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.msc.mubeen.school_base.db.SQ_EMP;

public class SignUP_EMP extends AppCompatActivity {
    EditText name, email ,address , password ,confrom_password,number, cnic,degree,subject;
    Button signup_btn;

    String str_job_type = null;

    Spinner job_type_spin;

    SQ_EMP sq_emp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up__emp);


        sq_emp = new SQ_EMP(this);
        name = findViewById(R.id.signup_name);
        email =findViewById(R.id.signup_emial);
        address =findViewById(R.id.signup_address);
        confrom_password= findViewById(R.id.signup_confrom_password);
        password = findViewById(R.id.signup_password);
        number = findViewById(R.id.signup_no);
        cnic = findViewById(R.id.signup_cnic);
        subject = findViewById(R.id.signup_subject);
        degree = findViewById(R.id.signup_degree);

        signup_btn = findViewById(R.id.signup_btn);







        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_name = name.getText().toString();
                String str_email = email.getText().toString();
                String str_address = address.getText().toString();
                String str_password = password.getText().toString();
                String str_c_password = confrom_password.getText().toString();
                String str_number = number.getText().toString();
                String str_cnic = cnic.getText().toString();
                String str_subject = subject.getText().toString();
                String str_degree = degree.getText().toString();



                if (TextUtils.isEmpty(str_name) || TextUtils.isEmpty(str_email) ||
                        TextUtils.isEmpty(str_address) || TextUtils.isEmpty(str_password)
                        || TextUtils.isEmpty(str_c_password) || TextUtils.isEmpty(str_number)
                        || TextUtils.isEmpty(str_cnic) || str_subject.equals("") || str_degree.equals(""))

                {
                    ALERT_DIALOG("ALERT","PLEASE FILL ALL INFORMATION","OK");
                }
                else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(str_email).matches())
                {
                    ALERT_DIALOG("ALERT","ENTER A VALID EMAIL","OK");
                }
                else if (!str_password.equals(str_c_password))
                {
                    ALERT_DIALOG("ALERT","YOUR PASSWORD IS NOT SAME WRITE AGAIN","OK");
                    password.setText("");
                    confrom_password.setText("");
                }
                else
                {

                    // admin change here
                    sq_emp.INSERT_NEW_TEACHER_DATA(str_name,str_password,str_email,str_address,str_number,str_cnic,str_degree,str_subject);
                    startActivity(new Intent(SignUP_EMP.this,Sign_in_EMP.class).putExtra("SignUp","DONE"));
                }
            }
        });
    }

    public void ALERT_DIALOG(String point,String note,String btn_name)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(SignUP_EMP.this).create();
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
