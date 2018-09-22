package jjchu.ca.shopify_winter_2019.RecyclerAdapters;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jjchu.ca.shopify_winter_2019.APIClient.ImageDownloader;
import jjchu.ca.shopify_winter_2019.Models.ProductModel;
import jjchu.ca.shopify_winter_2019.Models.VariantModel;
import jjchu.ca.shopify_winter_2019.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {
    private List<ProductModel> products;

    public static class ProductsViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout productView;
        ProductsViewHolder(@NonNull LinearLayout v) {
            super(v);
            productView = v;
        }
    }

    public ProductsAdapter(List<ProductModel> productsSource) {
        products = productsSource;
    }

    @NonNull
    @Override
    public ProductsAdapter.ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_products, viewGroup, false);

        ProductsViewHolder vh = new ProductsViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductsViewHolder productsViewHolder, final int i) {
        ProductModel product = products.get(i);
        ((TextView) productsViewHolder.productView.findViewById(R.id.productName))
                .setText(product.getTitle());
        ((TextView) productsViewHolder.productView.findViewById(R.id.productInventory))
                .setText("Inventory: " + getInventory(product.getVariants()));
        (productsViewHolder.productView.findViewById(R.id.productImage))
                .setMinimumWidth(product.getImage().getWidth());
        (productsViewHolder.productView.findViewById(R.id.productImage))
                .setMinimumHeight(product.getImage().getHeight());

        OkHttpClient client = ImageDownloader.getClient();
        Request req = new Request.Builder()
                .url(product.getImage().getSrc())
                .build();
        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final Resources res = productsViewHolder.productView.getContext().getResources();

                Handler mainHandler = new Handler(Looper.getMainLooper());
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        ((ImageView) productsViewHolder.productView.findViewById(R.id.productImage))
                                .setImageDrawable(res.getDrawable(R.drawable.ic_error));
                        stopProgress(productsViewHolder);
                    }
                };
                mainHandler.post(runnable);
            }

            @Override
            public void onResponse(Call call, Response response) {
                if(response.code() == 200) {
                    try {
                        InputStream is = response.body().byteStream();
                        final Bitmap bp = BitmapFactory.decodeStream(is);

                        Handler mainHandler = new Handler(Looper.getMainLooper());
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                ((ImageView) productsViewHolder.productView.findViewById(R.id.productImage))
                                        .setImageBitmap(bp);
                                stopProgress(productsViewHolder);
                            }
                        };
                        mainHandler.post(runnable);
                    } catch (Error e){
                        final Resources res = productsViewHolder.productView.getContext().getResources();

                        Handler mainHandler = new Handler(Looper.getMainLooper());
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                ((ImageView) productsViewHolder.productView.findViewById(R.id.productImage))
                                        .setImageDrawable(res.getDrawable(R.drawable.ic_error));
                                stopProgress(productsViewHolder);
                            }
                        };
                        mainHandler.post(runnable);
                    }
                } else{
                    final Resources res = productsViewHolder.productView.getContext().getResources();

                    Handler mainHandler = new Handler(Looper.getMainLooper());
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            ((ImageView) productsViewHolder.productView.findViewById(R.id.productImage))
                                    .setImageDrawable(res.getDrawable(R.drawable.ic_error));
                            stopProgress(productsViewHolder);
                        }
                    };
                    mainHandler.post(runnable);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    private int getInventory(List<VariantModel> variants) {
        int sum = 0;
        for(VariantModel variant : variants) {
            sum += variant.getInventory_quantity();
        }
        return sum;
    }

    private void stopProgress(ProductsViewHolder productsViewHolder) {
        (productsViewHolder.productView.findViewById(R.id.productProgress))
                .setVisibility(View.INVISIBLE);
    }
}
