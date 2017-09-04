package com.jdkgroup.recyclerviewapp.activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.jdkgroup.recyclerviewapp.R;
import com.jdkgroup.recyclerviewapp.adapter.Section3Adapter;
import com.jdkgroup.recyclerviewapp.model.ModelSection3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Section3Activity extends AppCompatActivity {

    private Section3Adapter mAdapter;
    private ArrayList<ModelSection3> mSectionList;
    private String[] mCountries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        mCountries = getApplicationContext().getResources().getStringArray(R.array.countries);
        ArrayList<ModelSection3> countriesModels = new ArrayList<>();
        for (int j = 0; j < mCountries.length ; j++) {
            countriesModels.add(new ModelSection3(mCountries[j].toString(),false));
        }

        mSectionList = new ArrayList<>();
        getHeaderListLatter(countriesModels);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new Section3Adapter(mSectionList, this);
        recyclerView.setAdapter(mAdapter);
    }

    private void getHeaderListLatter(ArrayList<ModelSection3> usersList) {

        Collections.sort(usersList, new Comparator<ModelSection3>() {
            @Override
            public int compare(ModelSection3 user1, ModelSection3 user2) {
                return String.valueOf(user1.name.charAt(0)).toUpperCase().compareTo(String.valueOf(user2.name.charAt(0)).toUpperCase());
            }
        });

        String lastHeader = "";

        int size = usersList.size();

        for (int i = 0; i < size; i++) {

            ModelSection3 user = usersList.get(i);
            String header = String.valueOf(user.name.charAt(0)).toUpperCase();

            if (!TextUtils.equals(lastHeader, header)) {
                lastHeader = header;
                mSectionList.add(new ModelSection3(header,true));
            }

            mSectionList.add(user);
        }
    }
}
