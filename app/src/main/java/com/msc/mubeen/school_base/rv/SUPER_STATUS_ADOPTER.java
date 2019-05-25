package com.msc.mubeen.school_base.rv;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msc.mubeen.school_base.MODEL.Employee;
import com.msc.mubeen.school_base.R;
import com.msc.mubeen.school_base.db.SQ_EMP;

import java.util.ArrayList;

public class SUPER_STATUS_ADOPTER extends RecyclerView.Adapter<SUPER_STATUS_ADOPTER.SSA_VIEW_HOLDER> {

    ArrayList<Employee> list;
    Context C;

    public SUPER_STATUS_ADOPTER(ArrayList<Employee> list, Context c) {
        this.list = list;
        C = c;
    }

    public ArrayList<Employee> getList() {
        return list;
    }

    public void setList(ArrayList<Employee> list) {
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
    public SSA_VIEW_HOLDER onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cv_emp,viewGroup,false);
        return new SSA_VIEW_HOLDER(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final SSA_VIEW_HOLDER ssa_view_holder, final int i) {

        if (list.get(i).getStutus().equals("1"))
        {
            ssa_view_holder.btn_activate.setText("Activated");
        }
            ssa_view_holder.name.setText(list.get(i).getName());
            ssa_view_holder.btn_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str_detials ;
                    str_detials= "Name :" + list.get(i).getName() +"\n";
                    str_detials= str_detials + "Address : "+list.get(i).getAdddress()+"\n";
                    str_detials = str_detials + "Number :" +list.get(i).getNumber()+"\n";
                    str_detials = str_detials + "CNIC : " +list.get(i).getCnic()+"\n";
                    str_detials = str_detials+ "Degree :" +list.get(i).getDegree()+"\n";
                    str_detials = str_detials + "Subject : " + list.get(i).getSubject();
                    ALERT_DIALOG("Details :",str_detials,"OK");
                }
            });

            ssa_view_holder.btn_activate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (list.get(i).getStutus().equals("1"))
                    {
                        ALERT_DIALOG("NOTE","ALREADY ACTIVATED","OK");

                    }else
                    {
                        SQ_EMP sq_emp = new SQ_EMP(C);
                        sq_emp.Activate_EMP_WITH_ID(list.get(i).getId());
                    }


                }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SSA_VIEW_HOLDER extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView btn_activate;
        TextView btn_view;

        public SSA_VIEW_HOLDER(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.cv_emp_name);
            btn_activate = itemView.findViewById(R.id.cv_emp_activate);
            btn_view  = itemView.findViewById(R.id.cv_emp_view);
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
