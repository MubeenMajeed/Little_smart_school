package com.msc.mubeen.school_base.rv;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.msc.mubeen.school_base.MODEL.STUDENT_MODEL;
import com.msc.mubeen.school_base.R;
import com.msc.mubeen.school_base.STUDENT.STUDENT_RESULTS;

import java.util.ArrayList;

public class STUDENT_ADOPTER extends RecyclerView.Adapter<STUDENT_ADOPTER.SA_VIEW_HOLDER> {


    ArrayList<STUDENT_MODEL> list;
    String parent_value;
    String str_parent_login_id;

    Context C;

    public STUDENT_ADOPTER(ArrayList<STUDENT_MODEL> list, String parent_value, String str_parent_login_id, Context c) {
        this.list = list;
        this.parent_value = parent_value;
        this.str_parent_login_id = str_parent_login_id;
        C = c;
    }

    public STUDENT_ADOPTER(ArrayList<STUDENT_MODEL> list, Context c, String parent_value) {
        this.list = list;
        C = c;
        this.parent_value =parent_value;
    }

    @NonNull
    @Override
    public SA_VIEW_HOLDER onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cv_student, viewGroup, false);
        return new SA_VIEW_HOLDER(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SA_VIEW_HOLDER sa_view_holder, final int i) {

        sa_view_holder.address.setText(list.get(i).getAddress());
        sa_view_holder.number.setText(list.get(i).getNumber());
        sa_view_holder.class_name.setText(list.get(i).getClass_name());
        sa_view_holder.name.setText(list.get(i).getName());
        Toast.makeText(C, list.get(i).getStudent_id(), Toast.LENGTH_SHORT).show();

        sa_view_holder.btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                C.startActivity(new Intent(C,STUDENT_RESULTS.class)
                .putExtra(String.valueOf(R.string.teacher_id),list.get(i).getTeacher_id())
                        //parent id is used as name of student for saving option of detection
                .putExtra(String.valueOf(R.string.parent_id),list.get(i).getName())
                .putExtra(String.valueOf(R.string.parent_result),parent_value));
//               TODO parenmt value is use as yes or no

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SA_VIEW_HOLDER extends RecyclerView.ViewHolder
    {
        TextView name,class_name,number,address;
        Button btn_result;

        public SA_VIEW_HOLDER(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.cv_stu_name);
            class_name = itemView.findViewById(R.id.cv_stu_class);
            number = itemView.findViewById(R.id.cv_stu_number);
            address = itemView.findViewById(R.id.cv_stu_address);
            btn_result = itemView.findViewById(R.id.cv_stu_result);

        }
    }
}
