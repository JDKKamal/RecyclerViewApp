package com.jdkgroup.recyclerviewapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jdkgroup.customview.SectionedRecyclerViewAdapter;
import com.jdkgroup.recyclerviewapp.R;
import com.jdkgroup.recyclerviewapp.model.ModelSectionChild;
import com.jdkgroup.recyclerviewapp.model.MainSection;

import java.util.List;

public class SectionAdapter extends SectionedRecyclerViewAdapter<RecyclerView.ViewHolder> {

    private List<MainSection> alSection;
    private List<ModelSectionChild> alChild;

    public SectionAdapter(List<MainSection> alSection) {
        this.alSection = alSection;
    }

    @Override
    public int getSectionCount() {
        return alSection.size();
    }

    @Override
    public int getItemCount(int section) {
        return alSection.get(section).getCategory().size();
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int section) {

        String sectionName = alSection.get(section).getCategory().get(section).getName();
        SectionViewHolder sectionViewHolder = (SectionViewHolder) holder;
        sectionViewHolder.tvParentTitle.setText(sectionName);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int section, final int relativePosition, int absolutePosition) {
        alChild = alSection.get(section).getCategory().get(section).getChilds();
        String citemname = alChild.get(relativePosition).getName();
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.tvChildTitle.setText(citemname);

        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alChild = alSection.get(section).getCategory().get(section).getChilds();
                String citemname = alChild.get(relativePosition).getId();
                Toast.makeText(v.getContext(), String.valueOf(citemname), Toast.LENGTH_SHORT).show();
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

        private final View itemView;
        final TextView tvChildTitle;

        public ItemViewHolder(View itemView) {
            super(itemView);

            this.itemView = itemView;
            tvChildTitle = (TextView) itemView.findViewById(R.id.tvChildTitle);
        }
    }
}