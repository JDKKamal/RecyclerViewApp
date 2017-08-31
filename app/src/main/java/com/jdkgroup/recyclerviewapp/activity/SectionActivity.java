package com.jdkgroup.recyclerviewapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.jdkgroup.recyclerviewapp.R;
import com.jdkgroup.recyclerviewapp.adapter.SectionAdapter;
import com.jdkgroup.recyclerviewapp.model.ModelSectionParent;
import com.jdkgroup.recyclerviewapp.model.ModelSectionChild;
import com.jdkgroup.recyclerviewapp.model.MainSection;

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

public class SectionActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private List<ModelSectionChild> al_child;
    private List<MainSection> alSection;
    private String result;

    private RecyclerView recyclerView;
    private SectionAdapter sectionAdapter;
    private GridLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        alSection = new ArrayList<>();

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle("Section RecyclerView");
        }

        populateSampleData();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, null));

        sectionAdapter = new SectionAdapter(alSection);
        manager = new GridLayoutManager(this, 1);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(sectionAdapter);
    }

    private void populateSampleData() {

        try {
            InputStream is = getResources().getAssets().open("expandablelist.json");
            result = convertStreamToString(is);

            JSONObject jo_result = new JSONObject(result);

            JSONArray ja_category = jo_result.getJSONArray("category");
            List<ModelSectionParent> alcategory = new ArrayList<>();
            ModelSectionParent category;

            for (int i = 0; i < ja_category.length(); i++) {
                JSONObject jo_category = ja_category.getJSONObject(i);

                String pname = jo_category.getString("name");
                category = new ModelSectionParent();
                category.setName(pname);
                alcategory.add(category);

                JSONArray ja_child = jo_category.getJSONArray("childs");

                al_child = new ArrayList<>();
                for (int j = 0; j < ja_child.length(); j++) {
                    JSONObject jo_child = ja_child.getJSONObject(j);
                    String cname = jo_child.getString("name");
                    String cid = jo_child.getString("id");

                    ModelSectionChild model_child = new ModelSectionChild();
                    model_child.setName(cname);
                    model_child.setId(cid);

                    al_child.add(model_child);
                }

                MainSection section = new MainSection();
                category.setChilds(al_child);
                section.setCategory(alcategory);

                alSection.add(section);
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
