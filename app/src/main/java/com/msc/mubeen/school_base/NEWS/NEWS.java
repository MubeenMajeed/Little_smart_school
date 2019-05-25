package com.msc.mubeen.school_base.NEWS;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.msc.mubeen.school_base.ADMIN;
import com.msc.mubeen.school_base.COMMON_LOGIN_PAGE;
import com.msc.mubeen.school_base.MODEL.NEWS_MODEL;
import com.msc.mubeen.school_base.R;
import com.msc.mubeen.school_base.SignUP_EMP;
import com.msc.mubeen.school_base.Sign_in_EMP;
import com.msc.mubeen.school_base.db.SQ_EMP;
import com.msc.mubeen.school_base.rv.NEWS_ADOPTER;

import java.util.ArrayList;

public class NEWS extends AppCompatActivity {
    RecyclerView NEWS_RV;

    ArrayList<NEWS_MODEL> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        list = new ArrayList<>();
        NEWS_RV = findViewById(R.id.news_rv);
        NEWS_ADOPTER news_adopter = new NEWS_ADOPTER(list,NEWS.this);
        NEWS_RV.setLayoutManager(new LinearLayoutManager(this));

        SQ_EMP sq = new SQ_EMP(this);

        Cursor res = sq.GET_NEWS_DETAIL();

        if (res.getCount()==0)
        {
            Toast.makeText(this, "NO NEWS IS HERE", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (res.moveToNext())
            {
                String str_title = res.getString(res.getColumnIndex(SQ_EMP.N_TITLE));
                String str_date = res.getString(res.getColumnIndex(SQ_EMP.N_DATE));
                String str_details = res.getString(res.getColumnIndex(SQ_EMP.N_DETAILS));

                NEWS_MODEL news_model = new NEWS_MODEL(str_title,str_details,str_date);

                list.add(news_model);
            }
            NEWS_RV.setAdapter(news_adopter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.news_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            startActivity(new Intent(NEWS.this,COMMON_LOGIN_PAGE.class));
            return true;
        }
        else if (id == R.id.action_signup) {
            startActivity(new Intent(NEWS.this,SignUP_EMP.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
