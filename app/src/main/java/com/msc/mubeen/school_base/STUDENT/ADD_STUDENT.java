package com.msc.mubeen.school_base.STUDENT;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.msc.mubeen.school_base.R;
import com.msc.mubeen.school_base.Sign_in_EMP;
import com.msc.mubeen.school_base.db.SQ_EMP;

public class ADD_STUDENT extends AppCompatActivity {

    EditText edt_name,edt_number ,edt_address,edt_class;

    Button btn_Add;
    String teacher_id;

    SQ_EMP sq_emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__student);

        teacher_id = getIntent().getStringExtra(String.valueOf(R.string.teacher_id));
        sq_emp = new SQ_EMP(this);
        edt_address  =(EditText)  findViewById(R.id.s_address);
        edt_class = (EditText)  findViewById(R.id.s_class);
        edt_name = (EditText)  findViewById(R.id.s_name);
        edt_number = findViewById(R.id.s_no);


        btn_Add = findViewById(R.id.s_btn_add);

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_name = edt_name.getText().toString();
                String str_class = edt_class.getText().toString();
                String str_number =edt_number.getText().toString();
                String str_address = edt_address.getText().toString();


                if (str_address.equals("") || str_class.equals("") || str_name.equals("") ||  str_number.equals(""))
                {
                    Toast.makeText(ADD_STUDENT.this, "Write Full details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean res = sq_emp.INSERT_STUDENT(teacher_id,str_name,str_address,str_class,"1234",str_number);
                    if (res)
                    {
                        AlertDialog alertDialog = new AlertDialog.Builder(ADD_STUDENT.this).create();
                        alertDialog.setTitle("STUDENT ADDED");
                        alertDialog.setMessage("YOU WANT TO ADD MORE STUDENT");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "YES",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        edt_number.setText("");
                                        edt_name.setText("");
                                        edt_address.setText("");
                                        edt_class.setText("");
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                dialog.dismiss();

                            }
                        });
                        alertDialog.show();
                    }
                    else
                    {
                        Toast.makeText(ADD_STUDENT.this, "NOT ADDED", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });







    }

}
