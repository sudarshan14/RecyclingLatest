package sud.bhatt.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by 10608780 on 16-04-2018.
 */

public interface Api {

    String BASE_URL = "http://audioturtle.eys.es/sqltutorial/";
  //  String CLASS = "jokes/";

    @GET("v1/jokes")
    Call<Data> loadJokes();

    @GET("v1/facts")
    Call<Data> loadFats();
}
