package com.jdkgroup.recyclerviewapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jdkgroup.recyclerviewapp.model.ModelAppEditText;
import com.jdkgroup.recyclerviewapp.R;
import com.jdkgroup.recyclerviewapp.adapter.AdapterAppEditText;

import java.util.ArrayList;
import java.util.List;

public class AppEditTextActivity extends AppCompatActivity implements AdapterAppEditText.ItemClickListener, View.OnClickListener {

    RecyclerView recyclerView;
    AppCompatButton appBtnSubmit;
    AdapterAppEditText adapterAppEditText;
    List<ModelAppEditText> alAppEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_edittext);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        appBtnSubmit = (AppCompatButton) findViewById(R.id.appBtnSubmit);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        adapterAppEditText = new AdapterAppEditText(this, getListAppEditText());
        recyclerView.setAdapter(adapterAppEditText);
        adapterAppEditText.setClickListener(this);
        appBtnSubmit.setOnClickListener(this);
    }

    public List<ModelAppEditText> getListAppEditText() {
        alAppEditText = new ArrayList<>();
        alAppEditText.add(new ModelAppEditText(1, ""));
        alAppEditText.add(new ModelAppEditText(2, ""));
        alAppEditText.add(new ModelAppEditText(3, ""));
        alAppEditText.add(new ModelAppEditText(4, ""));
        alAppEditText.add(new ModelAppEditText(5, ""));
        alAppEditText.add(new ModelAppEditText(6, ""));
        alAppEditText.add(new ModelAppEditText(7, ""));
        alAppEditText.add(new ModelAppEditText(8, ""));
        alAppEditText.add(new ModelAppEditText(9, ""));
        alAppEditText.add(new ModelAppEditText(10, ""));

        return alAppEditText;
    }

    @Override
    public void onUpdateAtUserName(int position, String username) {
        alAppEditText.set(position, new ModelAppEditText(position, username));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.appBtnSubmit:
                for (ModelAppEditText appEditText : alAppEditText)
                {
                    System.out.println("Tag " + appEditText.getId() + " - " + appEditText.getUsername());
                }
                break;
        }
    }
}
