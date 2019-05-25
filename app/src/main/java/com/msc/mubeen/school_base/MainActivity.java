package com.msc.mubeen.school_base;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.msc.mubeen.school_base.MODEL.STUDENT_MODEL;
import com.msc.mubeen.school_base.STUDENT.ADD_STUDENT;
import com.msc.mubeen.school_base.db.SQ_EMP;
import com.msc.mubeen.school_base.rv.STUDENT_ADOPTER;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String login_id;
    String parent_value;
    FloatingActionButton btn_add;
    RecyclerView de_stu_RV;

    SQ_EMP sq_emp;
    ArrayList<STUDENT_MODEL> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        list = new ArrayList<>();

        login_id = getIntent().getStringExtra(String.valueOf(R.string.teacher_id));
        parent_value = getIntent().getStringExtra(String.valueOf(R.string.parent_result));

        btn_add = findViewById(R.id.de_stu_add_btn);
        de_stu_RV =  findViewById(R.id.de_stu_RV);
        de_stu_RV.setLayoutManager(new LinearLayoutManager(this));


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,ADD_STUDENT.class).putExtra(String.valueOf(R.string.teacher_id),login_id));

            }
        });


    }

    public void makelist()
    {
        STUDENT_ADOPTER student_adopter = new STUDENT_ADOPTER(list,this,parent_value);

        sq_emp = new SQ_EMP(this);
        Cursor res = sq_emp.GET_STUDENT_DETAIL(login_id);

        if (res.getCount()==0)
        {
            Toast.makeText(this, "NO STUDETN DATA FOUND", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (res.moveToNext())
            {


                String str_t_id = res.getString(res.getColumnIndex(SQ_EMP.S_TID));
                String str_s_id = res.getString(res.getColumnIndex(SQ_EMP.S_ID));
                String str_name = res.getString(res.getColumnIndex(SQ_EMP.S_NAME));
                String str_s_address = res.getString(res.getColumnIndex(SQ_EMP.S_ADDRESS));
                String str_number = res.getString(res.getColumnIndex(SQ_EMP.S_PHONE));
                String str_class = res.getString(res.getColumnIndex(SQ_EMP.S_Class));

                STUDENT_MODEL student_model = new STUDENT_MODEL(str_t_id,str_s_id,str_name,str_s_address,str_class,str_number);
                list.add(student_model);
            }
            de_stu_RV.setAdapter(student_adopter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        makelist();
    }
}
