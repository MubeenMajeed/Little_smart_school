package com.msc.mubeen.school_base.rv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msc.mubeen.school_base.MODEL.RESULT_MODEL;
import com.msc.mubeen.school_base.R;

import java.util.ArrayList;

public class RESULT_ADOPTER extends RecyclerView.Adapter<RESULT_ADOPTER.RA_VIEW_HOLDER> {

    ArrayList<RESULT_MODEL> list;
    Context C;

    public RESULT_ADOPTER(ArrayList<RESULT_MODEL> list, Context c) {
        this.list = list;
        C = c;
    }

    public ArrayList<RESULT_MODEL> getList() {
        return list;
    }

    public void setList(ArrayList<RESULT_MODEL> list) {
        this.list = list;
    }

    public Context getC() {
        return C;
    }

    public void setC(Context c) {
        C = c;
    }

    @NonNull
    @Override
    public RA_VIEW_HOLDER onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cv_result, viewGroup, false);
        return new RA_VIEW_HOLDER(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RA_VIEW_HOLDER ra_view_holder, int i) {
        ra_view_holder.subject.setText(list.get(i).getSubject());
        ra_view_holder.gain.setText(list.get(i).getGain());
        ra_view_holder.grade.setText(list.get(i).getGrade());
        ra_view_holder.time.setText(list.get(i).getTime());
        //ra_view_holder.total.setText(list.get(i).ge);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RA_VIEW_HOLDER extends RecyclerView.ViewHolder
    {

        TextView time,total,gain,grade,subject;
        public RA_VIEW_HOLDER(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.cv_res_time);
            total = itemView.findViewById(R.id.cv_res_total);
            gain = itemView.findViewById(R.id.cv_res_gain);
            grade = itemView.findViewById(R.id.cv_res_grade);
            subject = itemView.findViewById(R.id.cv_res_subject);
        }
    }
}
