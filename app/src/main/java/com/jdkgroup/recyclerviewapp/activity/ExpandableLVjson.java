package com.jdkgroup.recyclerviewapp.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.jdkgroup.recyclerviewapp.R;
import com.jdkgroup.recyclerviewapp.adapter.ExpandablerListViewJsonAdapter;
import com.jdkgroup.recyclerviewapp.model.ModelChild;
import com.jdkgroup.recyclerviewapp.model.ModelParent;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ExpandableLVjson extends AppCompatActivity {

    private Activity act;
    private ExpandableListView mExpandableList;
    private String result;

    private List<ModelChild> alChild;
    private List<ModelParent> alParent;
    private ModelParent modelParent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_listview_json);
        act = this;

        alParent = new ArrayList<>();

        mExpandableList = (ExpandableListView) findViewById(R.id.expandable_list);

        try {
            InputStream is = getResources().getAssets().open("expandable.json");
            result = convertStreamToString(is);

            JSONObject jo_result = new JSONObject(result);

            JSONArray ja_category = jo_result.getJSONArray("category");

            for (int i = 0; i < ja_category.length(); i++) {
                JSONObject jo_category = ja_category.getJSONObject(i);

                String pname = jo_category.getString("name");
                modelParent = new ModelParent();
                modelParent.setPname(pname);

                JSONArray ja_child = jo_category.getJSONArray("childs");

                alChild = new ArrayList<>();
                for (int j = 0; j < ja_child.length(); j++) {
                    JSONObject jo_child = ja_child.getJSONObject(j);
                    String cname = jo_child.getString("name");

                    ModelChild model_child = new ModelChild();
                    model_child.setCname(cname);

                    alChild.add(model_child);
                }

                modelParent.setAl_child(alChild);
                alParent.add(modelParent);
            }
            mExpandableList.setAdapter(new ExpandablerListViewJsonAdapter(act, alParent));
        } catch (Exception ex) {

        }
        Toast.makeText(act, result, Toast.LENGTH_SHORT).show();
        //mExpandableList.setAdapter(new MyCustomAdapter(act, arrayParents));
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




