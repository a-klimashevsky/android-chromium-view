package org.chromium.content_shell;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HistoryActivity extends Activity implements OnItemClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hoistory_activity);
		ListView mListView = (ListView) findViewById(R.id.list);
		mListView.setOnItemClickListener(this);
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		String json = pref.getString("history", null);
		if(json != null){
			try {
				JSONArray array = new JSONArray(json);
				List<String> urls = new ArrayList<String>();
				for(int i = 0 ; i < array.length();i++){
					String url = array.getString(i);
					urls.add(url);
				}
				ArrayAdapter adapter = new ArrayAdapter<String>(this, 
				        android.R.layout.simple_list_item_1, urls);
				mListView.setAdapter(adapter);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
		String url = listView.getAdapter().getItem(position).toString();
		Intent data = new Intent();
		data.putExtra("url", url);
		setResult(RESULT_OK, data);
		finish();
	}
}
