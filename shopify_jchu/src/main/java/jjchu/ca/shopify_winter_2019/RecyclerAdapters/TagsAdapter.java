package jjchu.ca.shopify_winter_2019.RecyclerAdapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import jjchu.ca.shopify_winter_2019.R;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.TagsViewHolder> {
    private String[] tags;
    private View.OnClickListener listener;

    public static class TagsViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout tagView;
        public TagsViewHolder(@NonNull LinearLayout v) {
            super(v);
            tagView = v;
        }
    }

    public TagsAdapter(String[] tagsSource, View.OnClickListener listener) {
        tags = tagsSource;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TagsAdapter.TagsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_tag, viewGroup, false);

        TagsViewHolder vh = new TagsViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TagsAdapter.TagsViewHolder tagsViewHolder, int i) {
        ((TextView) tagsViewHolder.tagView.findViewById(R.id.tagTextView)).setText(tags[i]);
        tagsViewHolder.tagView.setOnClickListener(this.listener);
    }

    @Override
    public int getItemCount() {
        return tags.length;
    }
}
