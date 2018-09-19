package jjchu.ca.shopify_winter_2019.APIClient;

import jjchu.ca.shopify_winter_2019.Models.ProductsModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ShopifyService {
    //TODO: Properly add params
    @GET("admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
    Call<ProductsModel> getProducts();
}
