package com.jdkgroup.recyclerviewapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.jdkgroup.recyclerviewapp.R;
import com.jdkgroup.recyclerviewapp.adapter.ExpandableAdapter;
import com.jdkgroup.recyclerviewapp.model.ModelExpandableRVChild;
import com.jdkgroup.recyclerviewapp.model.ModelExpandableRVParent;

import java.util.ArrayList;
import java.util.List;

public class ExpandableRVActivity extends AppCompatActivity {

    private List<Object> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);

        //I was too lazy to implement parcelable :P
        Object object = getLastCustomNonConfigurationInstance();
        if(object!=null) {
            listItems = (ArrayList<Object>) object;
        } else {
            generateListObjects();
        }

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ExpandableAdapter expandableAdapter = new ExpandableAdapter(listItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(expandableAdapter);
    }

    private void generateListObjects() {
        listItems = new ArrayList<>();
        for(int i=0;i<20;i++) {
            ModelExpandableRVParent expandableItemParent = new ModelExpandableRVParent("Parent title "+i);
            List<Object> childObjects = new ArrayList<>();
            if(i%2==0) {
                for (int j = 0; j < 2; j++) {
                    ModelExpandableRVChild expandableItemChild = new ModelExpandableRVChild("Child title " + j);
                    childObjects.add(expandableItemChild);
                }
            }
            expandableItemParent.setChildObjectList(childObjects);
            listItems.add(expandableItemParent);
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return listItems;
    }
}
