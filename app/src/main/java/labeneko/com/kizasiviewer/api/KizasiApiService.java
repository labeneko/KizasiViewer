package labeneko.com.kizasiviewer.api;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;

public interface KizasiApiService {
    @GET("/rss.xml")
    void getRss(Callback<Rss> cb);

    @Root(strict=false)
    public static class Rss {
        @Element
        public Channel channel;
    }

    @Root(strict=false)
    public static class Channel {
        @Element
        public String title;
    }
}
