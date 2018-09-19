package jjchu.ca.shopify_winter_2019.RecyclerTools;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import jjchu.ca.shopify_winter_2019.Models.ProductModel;
import jjchu.ca.shopify_winter_2019.Models.ProductsModel;
import jjchu.ca.shopify_winter_2019.R;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.TagsViewHolder> {
    private ProductModel[] products;

    public static class TagsViewHolder extends RecyclerView.ViewHolder {
        public TextView tagView;
        public TagsViewHolder(@NonNull TextView v) {
            super(v);
            tagView = v;
        }
    }

    public TagsAdapter(ProductsModel productsSource) {
        products = productsSource.products.toArray(new ProductModel[productsSource.products.size()]);
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
        tagsViewHolder.tagView.setText(products[i].getTitle());
    }

    @Override
    public int getItemCount() {
        return products.length;
    }
}
