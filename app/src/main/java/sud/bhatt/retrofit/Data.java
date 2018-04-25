package sud.bhatt.retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 10608780 on 16-04-2018.
 */

public class Data {
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;
    @SerializedName("category")
    private String category;
    @SerializedName("title")
    private String title;
    @SerializedName("message")
    private String message;
    @SerializedName("image")
    private String image;

    public Data(String name, String url, String category, String title, String message, String image) {
        this.name = name;
        this.url = url;
        this.category = category;
        this.title = title;
        this.message = message;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
