package labeneko.com.kizasiviewer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import labeneko.com.kizasiviewer.api.KizasiApiService;
import labeneko.com.kizasiviewer.api.RequestCallback;
import labeneko.com.kizasiviewer.api.RequestListener;
import labeneko.com.kizasiviewer.api.StringConverter;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.converter.SimpleXMLConverter;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setConverter(new SimpleXMLConverter())
                .setEndpoint("http://kizasi.jp")
                .build();
        KizasiApiService service = restAdapter.create(KizasiApiService.class);
        service.getRss(new RequestCallback<KizasiApiService.Rss>(new RequestListener<KizasiApiService.Rss>() {
            @Override
            public void onSuccess(KizasiApiService.Rss response) {
                System.out.println(response.channel.articles.get(0).title);
            }

            @Override
            public void onFailure(RetrofitError error) {
                System.out.println(error);
            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
