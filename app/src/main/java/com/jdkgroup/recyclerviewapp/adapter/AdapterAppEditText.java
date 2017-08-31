package com.jdkgroup.recyclerviewapp.adapter;

import android.app.Activity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdkgroup.recyclerviewapp.model.ModelAppEditText;
import com.jdkgroup.recyclerviewapp.R;

import java.util.List;

/**
 * Created by kamlesh on 8/30/2017.
 */

public class AdapterAppEditText extends RecyclerView.Adapter<AdapterAppEditText.ViewHolder> {
    private ItemClickListener clickListener;
    private Activity activity;
    private List<ModelAppEditText> alAppEditText;

    public AdapterAppEditText(Activity activity, List<ModelAppEditText> alAppEditText) {
        this.activity = activity;
        this.alAppEditText = alAppEditText;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_app_edit_text, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        ModelAppEditText appEditText = alAppEditText.get(position);

        viewHolder.appTvId.setText(String.valueOf(appEditText.getId()));
        viewHolder.appEdtUserName.setText(String.valueOf(appEditText.getUsername()));

        viewHolder.appEdtUserName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence query, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                String userName = cs.toString().toLowerCase();
                clickListener.onUpdateAtUserName(position, userName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != alAppEditText ? alAppEditText.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView appTvId;
        AppCompatEditText appEdtUserName;

        public ViewHolder(View itemView) {
            super(itemView);

            appTvId = (AppCompatTextView) itemView.findViewById(R.id.appTvId);
            appEdtUserName = (AppCompatEditText) itemView.findViewById(R.id.appEdtUserName);
        }

    }

    public interface ItemClickListener {
        void onUpdateAtUserName(int position, String username);
    }
}

