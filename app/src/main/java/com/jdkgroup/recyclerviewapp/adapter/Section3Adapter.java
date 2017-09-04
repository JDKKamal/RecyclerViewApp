package com.jdkgroup.recyclerviewapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jdkgroup.recyclerviewapp.R;
import com.jdkgroup.recyclerviewapp.model.ModelSection3;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class Section3Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int SECTION_VIEW = 0;
    public static final int CONTENT_VIEW = 1;

    ArrayList<ModelSection3> mCountriesModelList;
    WeakReference<Context> mContextWeakReference;

    public Section3Adapter(ArrayList<ModelSection3> mCountriesModelList, Context context) {

        this.mCountriesModelList = mCountriesModelList;
        this.mContextWeakReference = new WeakReference<Context>(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = mContextWeakReference.get();
        if (viewType == SECTION_VIEW) {
            return new SectionHeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_section_parent, parent, false));
        }
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_section_child, parent, false), context);
    }

    @Override
    public int getItemViewType(int position) {
        if (mCountriesModelList.get(position).isSection) {
            return SECTION_VIEW;
        } else {
            return CONTENT_VIEW;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        Context context = mContextWeakReference.get();
        if (context == null) {
            return;
        }
        if (SECTION_VIEW == getItemViewType(position)) {

            SectionHeaderViewHolder sectionHeaderViewHolder = (SectionHeaderViewHolder) holder;
            ModelSection3 sectionItem = mCountriesModelList.get(position);
            sectionHeaderViewHolder.tvParentTitle.setText(sectionItem.name);
            return;
        }

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        ModelSection3 currentUser = ((ModelSection3) mCountriesModelList.get(position));
        itemViewHolder.tvChildTitle.setText(currentUser.name);

    }


    @Override
    public int getItemCount() {
        return mCountriesModelList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView tvChildTitle;

        public ItemViewHolder(View itemView, final Context context) {
            super(itemView);
            tvChildTitle = (TextView) itemView.findViewById(R.id.tvChildTitle);
        }
    }

    public class SectionHeaderViewHolder extends RecyclerView.ViewHolder {
        TextView tvParentTitle;

        public SectionHeaderViewHolder(View itemView) {
            super(itemView);
            tvParentTitle = (TextView) itemView.findViewById(R.id.tvParentTitle);
        }
    }
}