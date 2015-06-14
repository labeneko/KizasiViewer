package labeneko.com.kizasiviewer;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import labeneko.com.kizasiviewer.api.KizasiApiService;

public class KizasiArticleAdapter extends ArrayAdapter<KizasiApiService.Article> {

    private Context context;
    public KizasiArticleAdapter(Context context){
        super(context, 0);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        KizasiApiService.Article article = getItem(position);
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.adapter_article, parent, false);
        }

        ((TextView)convertView.findViewById(R.id.txt_text)).setText(article.title);

        return convertView;
    }
}
