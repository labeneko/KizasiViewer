package labeneko.com.kizasiviewer;

import retrofit.Callback;
import retrofit.http.GET;

public interface KizasiApiService {
    @GET("/rss.xml")
    void getRss(Callback<String> cb);
}
