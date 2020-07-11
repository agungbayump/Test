package com.agungbayump.maintenanceassistance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.agungbayump.maintenanceassistance.R;
import com.agungbayump.maintenanceassistance.TestFaultInterface;
import com.agungbayump.maintenanceassistance.model.TestFaultModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class TestFaultAdapter extends RecyclerView.Adapter<TestFaultAdapter.FaultHolder> {
        private Context context;
        private ArrayList<TestFaultModel> list;
        private TestFaultInterface faultInterface;

    public TestFaultAdapter(Context context, ArrayList<TestFaultModel> testFaultList, TestFaultInterface faultInterface) {
        this.context = context;
        this.list = testFaultList;
        this.faultInterface = faultInterface;
    }

    @NonNull
    @Override
    public FaultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_testfault, parent, false);
        return new FaultHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FaultHolder holder, final int position) {

        Integer Nomer = list.size();
        int number = position+1;
        holder.textOut.setText("Pertanyaan");
        holder.numberOut.setText(""+number);


        holder.addYes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final TestFaultModel faultyes = new TestFaultModel("",position-1,Nomer,true);
                holder.addYes.setVisibility(View.GONE);
                list.add(faultyes);
                notifyDataSetChanged();
            }
        });

        holder.addNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TestFaultModel faultno = new TestFaultModel("",position-1,Nomer,false);
                holder.addNo.setVisibility(View.GONE);
                list.add(faultno);
                notifyDataSetChanged();
            }
        });

        holder.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String text = holder.textInput.getText().toString().trim();
                final Integer numberStep = Integer.valueOf(holder.numberOut.getText().toString().trim());

                holder.textInput.setEnabled(false);
                holder.send.setVisibility(View.GONE);
                faultInterface.onClick(text,numberStep);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(ArrayList<TestFaultModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class FaultHolder extends RecyclerView.ViewHolder{

        LinearLayout lytPertanyaan;
        TextView textOut, numberOut;
        TextInputEditText textInput;
        AppCompatButton addNo, addYes;
        ImageButton send;


        public FaultHolder(@NonNull View itemView) {
            super(itemView);

            lytPertanyaan = itemView.findViewById(R.id.lytPertanyaan);
            textOut = itemView.findViewById(R.id.textOut);
            textInput = itemView.findViewById(R.id.textInput);
            send = itemView.findViewById(R.id.send);
            numberOut = itemView.findViewById(R.id.numberOut);
            addNo = itemView.findViewById(R.id.addNo);
            addYes = itemView.findViewById(R.id.addYes);
        }
    }
}
