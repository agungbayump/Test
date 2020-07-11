package com.agungbayump.maintenanceassistance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.agungbayump.maintenanceassistance.adapter.TestFaultAdapter;
import com.agungbayump.maintenanceassistance.model.TestFaultModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class TestFault extends AppCompatActivity implements TestFaultInterface, View.OnClickListener {

    private DatabaseReference databaseReference;
    private TestFaultAdapter testFaultAdapter;
    private ArrayList<TestFaultModel> list;
    private RecyclerView recyclerView;
    private AppCompatButton show, finish, delete ;
    private TextInputEditText fault, detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fault);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.rvFault);
        delete = findViewById(R.id.delete);
        show = findViewById(R.id.show);
        finish = findViewById(R.id.finish);
        fault = findViewById(R.id.fault);
        detail = findViewById(R.id.detail);

        recyclerView.setVisibility(View.GONE);
        setView();

        show.setOnClickListener(this);
        finish.setOnClickListener(this);

    }
    private void setView(){
        list = new ArrayList<>();
        testFaultAdapter = new TestFaultAdapter(this,list,this);

        recyclerView = findViewById(R.id.rvFault);

        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
        recyclerView.setAdapter(testFaultAdapter);
    }

        private void initiateFirstList() {
        TestFaultModel faulttt = new TestFaultModel("",0,0,true);
        list.add(faulttt);
        testFaultAdapter.updateList(list);
    }



    private void tambahList(TestFaultModel testFaultModel) {
        int Number = list.size();
        String Sfault = fault.getText().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("Troubleshooting");

        reference.child("Palletizer").child(Sfault).child("Question_"+Number).setValue(testFaultModel);

    }

    private void showAndSubmitData() {
        String Sfault = fault.getText().toString();
        String Sdetail = detail.getText().toString();

        HashMap<Object, String> hashMap = new HashMap<>();

        hashMap.put("fault", Sfault);
        hashMap.put("detail", Sdetail);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("Troubleshooting");

        reference.child("Palletizer").child(Sfault).setValue(hashMap);

        recyclerView.setVisibility(View.VISIBLE);
        initiateFirstList();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.show :
                String Sfault = fault.getText().toString();
                String Sdetail = detail.getText().toString();

                if (Sfault.equals("")) {
                    fault.setError("Silahkan isi kode Fault");
                    fault.requestFocus();
                } else if (Sdetail.equals("")) {
                    detail.setError("Silahkan isi detail singkat Fault");
                    detail.requestFocus();
                } else{
                    show.setVisibility(View.GONE);

                    fault.setFocusable(false);
                    detail.setFocusable(false);
                    showAndSubmitData();
                }
                break;


            case R.id.finish :
                finish();
                break;
        }
    }



    @Override
    public void onClick(String text, Integer position) {
        TestFaultModel testFaultModel = list.get(position-1);
        testFaultModel.setQuestion(text);
        testFaultModel.getCondition();
        testFaultModel.setIfQuestion(position-1);
        list.set(position-1,testFaultModel);

        tambahList(testFaultModel);
    }
}
