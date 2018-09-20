package jjchu.ca.shopify_winter_2019.RecyclerTools;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import jjchu.ca.shopify_winter_2019.R;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.TagsViewHolder> {
    private String[] tags;

    public static class TagsViewHolder extends RecyclerView.ViewHolder {
        public TextView tagView;
        public TagsViewHolder(@NonNull TextView v) {
            super(v);
            tagView = v;
        }
    }

    public TagsAdapter(String[] tagsSource) {
        tags = tagsSource;
    }

    @NonNull
    @Override
    public TagsAdapter.TagsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TextView v = (TextView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_tag, viewGroup, false);

        TagsViewHolder vh = new TagsViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TagsAdapter.TagsViewHolder tagsViewHolder, int i) {
        tagsViewHolder.tagView.setText(tags[i]);
    }

    @Override
    public int getItemCount() {
        return tags.length;
    }
}
