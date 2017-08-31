package com.jdkgroup.recyclerviewapp.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.jdkgroup.recyclerviewapp.R;
import com.jdkgroup.recyclerviewapp.model.ModelParent;

import java.util.List;

public class ExpandablerListViewJsonAdapter extends BaseExpandableListAdapter {

    private LayoutInflater inflater;
    private List<ModelParent> alParent;

    public ExpandablerListViewJsonAdapter(Context context, List<ModelParent> alParent) {
        this.alParent = alParent;
        inflater = LayoutInflater.from(context);
    }

    @Override
    //counts the number of group/parent items so the list knows how many times calls getGroupView() method
    public int getGroupCount() {
        return alParent.size();
    }

    @Override
    //counts the number of children items so the list knows how many times calls getChildView() method
    public int getChildrenCount(int i) {
        return alParent.get(i).getAl_child().size();
    }

    @Override
    //gets the title of each parent/group
    public Object getGroup(int i) {
        return alParent.get(i).getPname();
    }

    @Override
    //gets the name of each item
    public Object getChild(int i, int i1) {
        return alParent.get(i).getAl_child().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    //in this method you must set the text to see the parent/group on the list
    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {

        ViewHolder holder = new ViewHolder();
        holder.groupPosition = groupPosition;

        if (view == null) {
            view = inflater.inflate(R.layout.itemview_expandable_listview_json_parent, viewGroup, false);
        }

        TextView tvParent = (TextView) view.findViewById(R.id.tvParent);
        tvParent.setText(getGroup(groupPosition).toString());

        view.setTag(holder);

        //return the entire view
        return view;
    }

    @Override
    //in this method you must set the text to see the children on the list
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {

        ViewHolder holder = new ViewHolder();
        holder.childPosition = childPosition;
        holder.groupPosition = groupPosition;

        if (view == null) {
            view = inflater.inflate(R.layout.itemview_expandable_listview_json_child, viewGroup, false);
        }

        TextView tvChild = (TextView) view.findViewById(R.id.tvChild);
        tvChild.setText(alParent.get(groupPosition).getAl_child().get(childPosition).getCname());

        view.setTag(holder);

        //return the entire view
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        /* used to make the notifyDataSetChanged() method work */
        super.registerDataSetObserver(observer);
    }

    public void onClick(View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        if (view.getId() == holder.button.getId()) {

            // DO YOUR ACTION
        }
    }


    protected class ViewHolder {
        protected int childPosition;
        protected int groupPosition;
        protected Button button;
    }
}