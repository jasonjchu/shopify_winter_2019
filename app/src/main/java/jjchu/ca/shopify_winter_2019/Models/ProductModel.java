package jjchu.ca.shopify_winter_2019.Models;

public class ProductModel {
    private String id;
    private String title;
    private String tags;
    private ImageModel image;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTags() {
        return tags;
    }

    public ImageModel getImage() {
        return image;
    }
}
