package sud.bhatt.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

/**
 * Created by 10608780 on 24-04-2018.
 */

public interface RetrofitInterface {

    @GET("HD_Videos/IMG_8634.JPG")
    @Streaming
    Call<ResponseBody> downoadFile();

}
