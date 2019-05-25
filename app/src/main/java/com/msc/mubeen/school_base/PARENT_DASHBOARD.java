package com.msc.mubeen.school_base;

import android.app.Dialog;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.msc.mubeen.school_base.MODEL.STUDENT_MODEL;
import com.msc.mubeen.school_base.db.SQ_EMP;
import com.msc.mubeen.school_base.rv.STUDENT_ADOPTER;

import java.util.ArrayList;

public class PARENT_DASHBOARD extends AppCompatActivity {

    SQ_EMP sq_emp;
    String str_in_phone;
    String str_in_id;

    String login_id;
    String parent_value;
    FloatingActionButton btn_add;
    RecyclerView de_stu_RV;


    ArrayList<STUDENT_MODEL> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent__dashboard);
        sq_emp = new SQ_EMP(this);
        str_in_id = getIntent().getStringExtra(String.valueOf(R.string.parent_id));
        str_in_phone = getIntent().getStringExtra(String.valueOf(R.string.parent_phone));

        list = new ArrayList<>();

        //login_id = getIntent().getStringExtra(String.valueOf(R.string.teacher_id));
        parent_value = getIntent().getStringExtra(String.valueOf(R.string.parent_result));

        de_stu_RV =  findViewById(R.id.de_stu_p_RV);
        de_stu_RV.setLayoutManager(new LinearLayoutManager(this));

        makelist();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.parent_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.parent_pw_change) {
//            startActivity(new Intent(PARENT_DASHBOARD.this,NEWS.class));
            final Dialog dialog = new Dialog(PARENT_DASHBOARD.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_change_password);

            final EditText edt_password = (EditText) dialog.findViewById(R.id.parent_pw_edt);


            Button dialogButton = (Button) dialog.findViewById(R.id.parent_pw_btn);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String str_password = edt_password.getText().toString();
                    sq_emp = new SQ_EMP(PARENT_DASHBOARD.this);

                    int update =sq_emp.UPDATE_PHONE_NO_PARENT(str_password,str_in_phone);
                    if (update>0)
                    {
                        Toast.makeText(PARENT_DASHBOARD.this, "YOUR NEW PASSWORD IS SET"+update, Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(PARENT_DASHBOARD.this, "NEW PASSWORD IS NOT SET"+update, Toast.LENGTH_SHORT).show();
                    }



                    dialog.dismiss();
                }
            });

            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void makelist()
    {
        STUDENT_ADOPTER student_adopter = new STUDENT_ADOPTER(list,parent_value,str_in_id,this);

        sq_emp = new SQ_EMP(this);
        Cursor res = sq_emp.GET_STUDENT_DETAIL_WITH_PHONE(str_in_phone);

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
