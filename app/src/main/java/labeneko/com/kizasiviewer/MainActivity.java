package labeneko.com.kizasiviewer;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import labeneko.com.kizasiviewer.api.KizasiApiService;
import labeneko.com.kizasiviewer.api.RequestCallback;
import labeneko.com.kizasiviewer.api.RequestListener;
import labeneko.com.kizasiviewer.api.StringConverter;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.converter.SimpleXMLConverter;

public class MainActivity extends ActionBarActivity {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    KizasiApiService service;
    KizasiArticleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SwipeRefreshLayoutの設定
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        mSwipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark);
        adapter = new KizasiArticleAdapter(this);

        // ListViewにデータをセットする
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);



        RestAdapter restAdapter = new RestAdapter.Builder()
                .setConverter(new SimpleXMLConverter())
                .setEndpoint("http://kizasi.jp")
                .build();
        service = restAdapter.create(KizasiApiService.class);

        refresh();
    }

    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mSwipeRefreshLayout.setRefreshing(true);
            refresh();
        }
    };

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

    private void refresh(){
        adapter.clear();
        service.getRss(new RequestCallback<KizasiApiService.Rss>(new RequestListener<KizasiApiService.Rss>() {
            @Override
            public void onSuccess(KizasiApiService.Rss response) {
                adapter.addAll(response.channel.articles);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(RetrofitError error) {
            }
        }));
    }
}
