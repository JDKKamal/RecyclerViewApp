package com.jdkgroup.recyclerviewapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdkgroup.recyclerviewapp.R;

public class ExpandableItemParentViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvParentTitle;
    private final ImageView ivParentExpand;
    private final View parent;

    public ExpandableItemParentViewHolder(View parent, TextView tvParentTitle, ImageView ivParentExpand) {
        super(parent);
        this.tvParentTitle = tvParentTitle;
        this.ivParentExpand = ivParentExpand;
        this.parent = parent;
        tvParentTitle.setSelected(true);
    }

    public static ExpandableItemParentViewHolder newInstance(View parent) {
        TextView tvParentTitle = (TextView) parent.findViewById(R.id.tvParentTitle);
        ImageView ivParentExpand = (ImageView) parent.findViewById(R.id.ivParentExpand);
        return new ExpandableItemParentViewHolder(parent, tvParentTitle, ivParentExpand);
    }

    public void setParentText(CharSequence title) {
        tvParentTitle.setText(title);
    }

    public void setExpandButtonGone(boolean gone) {
        ivParentExpand.setVisibility(gone ? View.GONE : View.VISIBLE);
    }

    public void setExpandClickListener(View.OnClickListener onClickListener) {
        ivParentExpand.setOnClickListener(onClickListener);
    }
}
