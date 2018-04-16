package sud.bhatt.retrofit;

import java.util.List;

import retrofit2.Call;

/**
 * Created by 10608780 on 16-04-2018.
 */

public interface Api {

    String BASE_URL = "http://thefaceart.com/";
    String CLASS = "images.php";
    Call<List<Data>> loadData();
}
