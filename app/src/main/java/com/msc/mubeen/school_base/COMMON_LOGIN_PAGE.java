package com.msc.mubeen.school_base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class COMMON_LOGIN_PAGE extends AppCompatActivity {

    Button btn_parent;
    Button btn_teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common__login__page);

        btn_parent = findViewById(R.id.cm_parent_oepn);
        btn_teacher = findViewById(R.id.cm_teacher_open);

        btn_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(COMMON_LOGIN_PAGE.this,Sign_in_EMP.class));
            }
        });

        btn_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(COMMON_LOGIN_PAGE.this,LOGIN_PARENT.class));
            }
        });
    }
}
