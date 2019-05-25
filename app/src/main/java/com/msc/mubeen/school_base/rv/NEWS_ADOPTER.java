package com.msc.mubeen.school_base.rv;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.msc.mubeen.school_base.MODEL.NEWS_MODEL;
import com.msc.mubeen.school_base.R;

import java.util.ArrayList;

public class NEWS_ADOPTER  extends RecyclerView.Adapter<NEWS_ADOPTER.NA_VIEW_HOLDER> {

    ArrayList<NEWS_MODEL> list;
    Context C;

    public NEWS_ADOPTER(ArrayList<NEWS_MODEL> list, Context c) {
        this.list = list;
        C = c;
    }

    @NonNull
    @Override
    public NA_VIEW_HOLDER onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_adopter, viewGroup, false);
        return new NA_VIEW_HOLDER(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NA_VIEW_HOLDER na_view_holder, final int i) {

        na_view_holder.txt_title.setText(list.get(i).getTitle());
        na_view_holder.btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_detials ;
                str_detials= "Title :" + list.get(i).getTitle() +"\n\n";
                str_detials= str_detials + "DATE : "+list.get(i).getDate()+"\n\n";
                str_detials = str_detials + "Details :\n" +list.get(i).getDetails();
                ALERT_DIALOG("NEWS Details",str_detials,"DONE");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NA_VIEW_HOLDER extends RecyclerView.ViewHolder
    {
        TextView txt_title;
        Button btn_view;

        public NA_VIEW_HOLDER(@NonNull View itemView) {
            super(itemView);

            txt_title = itemView.findViewById(R.id.cv_news_tittle);
            btn_view = itemView.findViewById(R.id.cv_news_btn_veiw);
        }
    }

    public void ALERT_DIALOG(String point,String note,String btn_name)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(C).create();
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
