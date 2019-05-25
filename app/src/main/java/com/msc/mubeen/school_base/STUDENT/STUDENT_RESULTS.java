package com.msc.mubeen.school_base.STUDENT;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.msc.mubeen.school_base.MODEL.RESULT_MODEL;
import com.msc.mubeen.school_base.MODEL.STUDENT_MODEL;
import com.msc.mubeen.school_base.R;
import com.msc.mubeen.school_base.db.SQ_EMP;
import com.msc.mubeen.school_base.rv.RESULT_ADOPTER;
import com.msc.mubeen.school_base.rv.STUDENT_ADOPTER;

import java.util.ArrayList;
import java.util.List;

public class STUDENT_RESULTS extends AppCompatActivity {

    EditText edt_add;
    Button btn_add;
    TextView txt_title;
    RecyclerView RES_RV;

    String str_teacher_id;
    String str_student_id;
    String str_parent_value;

//    ArrayList<STUDENT_MODEL> list;
    ArrayList<RESULT_MODEL> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studeny__results);

        edt_add = findViewById(R.id.res_edt_add);
        btn_add = findViewById(R.id.res_btn_add);
        txt_title = findViewById(R.id.res_txt_add);

        list = new ArrayList<>();



        str_student_id = getIntent().getStringExtra(String.valueOf(R.string.parent_id));
        str_teacher_id = getIntent().getStringExtra(String.valueOf(R.string.teacher_id));
        str_parent_value = getIntent().getStringExtra(String.valueOf(R.string.parent_result));


        RES_RV = findViewById(R.id.RES_RV);
       // STUDENT_ADOPTER student_adopter = new STUDENT_ADOPTER(list,this,str_parent_value);
        RESULT_ADOPTER result_adopter = new RESULT_ADOPTER(list,STUDENT_RESULTS.this);
        RES_RV.setLayoutManager(new LinearLayoutManager(STUDENT_RESULTS.this));
        SQ_EMP sq_emp = new SQ_EMP(STUDENT_RESULTS.this);







        // TODO when parent value is yes than disable the value from the front screen
        if (str_parent_value.equals("yes"))
        {
            edt_add.setVisibility(View.GONE);
            btn_add.setVisibility(View.GONE);
            txt_title.setVisibility(View.GONE);
            Toast.makeText(this, str_student_id, Toast.LENGTH_SHORT).show();
            Cursor res = sq_emp.GET_REULT_WITH_PARENT(str_student_id);

            if (res.getCount()==0)
            {
                Toast.makeText(this, "NO DATA FOUND FOR THIS STUDENT", Toast.LENGTH_SHORT).show();
            }
            else
            {
                while (res.moveToNext())
                {
                    String str_time = res.getString(res.getColumnIndex(SQ_EMP.R_TID));
                    String str_grade = res.getString(res.getColumnIndex(SQ_EMP.R_GRADE));
                    String str_subject = res.getString(res.getColumnIndex("t_subject"));
                    String str_marks = res.getString(res.getColumnIndex(SQ_EMP.R_MARKS));

                    RESULT_MODEL result_model = new RESULT_MODEL(str_time,str_marks,str_grade,str_subject);

                    list.add(result_model);
                }
                RES_RV.setAdapter(result_adopter);
            }
        }
        else
        {
            Cursor res = sq_emp.GET_REULT_WITH_TAECHER(str_teacher_id,str_student_id);
            if (res.getCount()==0)
            {
                Toast.makeText(this, "NO DATA FOUND FOR THIS STUDENT", Toast.LENGTH_SHORT).show();
            }
            else
            {
                while (res.moveToNext())
                {
                    String str_time = res.getString(res.getColumnIndex(SQ_EMP.R_DATE));
                    String str_grade = res.getString(res.getColumnIndex(SQ_EMP.R_GRADE));
                    String str_subject = res.getString(res.getColumnIndex("t_subject"));
                    String str_marks = res.getString(res.getColumnIndex(SQ_EMP.R_MARKS));

                    RESULT_MODEL result_model = new RESULT_MODEL(str_time,str_marks,str_grade,str_subject);

                    list.add(result_model);
                }
                RES_RV.setAdapter(result_adopter);
            }
        }

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_result = edt_add.getText().toString();

                if (str_result.equals(""))
                {
                    Toast.makeText(STUDENT_RESULTS.this, "WRITE RESULT", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String grade = "";
                    SQ_EMP sq_emp = new SQ_EMP(STUDENT_RESULTS.this);

                    int result = Integer.parseInt(str_result);

                    if (result>=80)
                    {
                        grade="A";
                    }
                    else if (result>=60 && result<80 )
                    {
                        grade = "B";
                    }
                    else if (result>=40 && result<60)
                    {
                        grade = "c";
                    }
                    else
                    {
                        grade = "f";
                    }
                    boolean res1 =sq_emp.INSERT_RESULT(str_teacher_id,str_student_id,str_result,grade);
                    if (res1)
                    {
                        Toast.makeText(STUDENT_RESULTS.this, "NEW RESULT IS ADDED", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(STUDENT_RESULTS.this, "NEW RESULT IS NOT ADDED", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
