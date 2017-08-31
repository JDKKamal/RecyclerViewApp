package com.jdkgroup.recyclerviewapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.jdkgroup.recyclerviewapp.R;
import com.jdkgroup.recyclerviewapp.adapter.Section2Adapter;
import com.jdkgroup.recyclerviewapp.model.ModelSection2Child;
import com.jdkgroup.recyclerviewapp.model.ModelSection2Parent;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Section2Activity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView recyclerView;

    private Section2Adapter adapterSection;
    private List<ModelSection2Child> alChild;
    private List<ModelSection2Parent> alParent;
    private String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        alParent = new ArrayList<>();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle("Section RecyclerView");
        }

        populateSampleData();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, null));

        adapterSection = new Section2Adapter(alParent);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterSection);
    }

    private void populateSampleData() {

        try {
            InputStream is = getResources().getAssets().open("expandablelist.json");
            response = convertStreamToString(is);

            JSONObject jo_result = new JSONObject(response);
            JSONArray ja_category = jo_result.getJSONArray("category");

            for (int i = 0; i < ja_category.length(); i++) {
                ModelSection2Parent parent = new ModelSection2Parent();
                JSONObject jo_category = ja_category.getJSONObject(i);

                String pname = jo_category.getString("name");
                parent.setHeaderTitle(pname);

                JSONArray ja_child = jo_category.getJSONArray("childs");

                alChild = new ArrayList<>();
                for (int j = 0; j < ja_child.length(); j++) {
                    JSONObject jo_child = ja_child.getJSONObject(j);
                    String cname = jo_child.getString("name");
                    String cid = jo_child.getString("id");

                    ModelSection2Child model_child = new ModelSection2Child();
                    model_child.setCname(cname);
                    model_child.setCid(cid);

                    alChild.add(model_child);
                }

                parent.setAlChild(alChild);
                alParent.add(parent);
            }


        } catch (Exception ex) {

        }
    }

    public String convertStreamToString(InputStream is) throws Exception {
        Writer writer = new StringWriter();
        char[] buffer = new char[2048];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            is.close();
        }
        String text = writer.toString();
        return text;
    }
}
