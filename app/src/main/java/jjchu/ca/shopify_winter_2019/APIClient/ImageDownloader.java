package jjchu.ca.shopify_winter_2019.APIClient;

import okhttp3.OkHttpClient;

public class ImageDownloader {
    private static OkHttpClient client = null;

    public static OkHttpClient getClient() {
        if (client==null) {
            client = new OkHttpClient();
        }
        return client;
    }
}
