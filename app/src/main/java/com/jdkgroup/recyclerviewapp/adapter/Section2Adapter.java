package com.jdkgroup.recyclerviewapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jdkgroup.customview.SectionedRecyclerViewAdapter;
import com.jdkgroup.recyclerviewapp.R;
import com.jdkgroup.recyclerviewapp.model.ModelSection2Child;
import com.jdkgroup.recyclerviewapp.model.ModelSection2Parent;

import java.util.List;

public class Section2Adapter extends SectionedRecyclerViewAdapter<RecyclerView.ViewHolder> {

    private List<ModelSection2Parent> alParent;
    List<ModelSection2Child> itemsInSection;

    public Section2Adapter(List<ModelSection2Parent> alParent) {
        this.alParent = alParent;
    }

    @Override
    public int getSectionCount() {
        return alParent.size();
    }

    @Override
    public int getItemCount(int section) {
        return alParent.get(section).getAlChild().size();
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int section) {

        String headerTitle = alParent.get(section).getHeaderTitle();
        SectionViewHolder sectionViewHolder = (SectionViewHolder) holder;
        sectionViewHolder.tvParentTitle.setText(headerTitle);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int section, final int relativePosition, int absolutePosition) {
        itemsInSection = alParent.get(section).getAlChild();
        String childCityName = itemsInSection.get(relativePosition).getCname();
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.tvChildTitle.setText(childCityName);

        itemViewHolder.tvChildTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ModelSection2Child> itemsInSection = alParent.get(section).getAlChild();
                Toast.makeText(v.getContext(), String.valueOf(itemsInSection.get(relativePosition).getCid()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, boolean header) {
        View v = null;
        if (header) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_section_parent, parent, false);
            return new SectionViewHolder(v);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_section_child, parent, false);
            return new ItemViewHolder(v);
        }
    }

    public class SectionViewHolder extends RecyclerView.ViewHolder {
        final TextView tvParentTitle;

        public SectionViewHolder(View itemView) {
            super(itemView);
            tvParentTitle = (TextView) itemView.findViewById(R.id.tvParentTitle);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        final TextView tvChildTitle;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvChildTitle = (TextView) itemView.findViewById(R.id.tvChildTitle);
        }
    }

}