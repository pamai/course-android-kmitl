package kmitl.lab09.paniti58070080.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kmitl.lab09.paniti58070080.moneyflow.Adapter.ListViewAdapter;

import static kmitl.lab09.paniti58070080.moneyflow.Constants.FIRST_COLUMN;
import static kmitl.lab09.paniti58070080.moneyflow.Constants.SECOND_COLUMN;
import static kmitl.lab09.paniti58070080.moneyflow.Constants.THIRD_COLUMN;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    MoneyDB moneyDB;
    private ArrayList<HashMap<String, String>> list;
    private List<MoneyInfo> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moneyDB = Room.databaseBuilder(getApplicationContext(), MoneyDB.class, "money").allowMainThreadQueries()
                .build();

        final ListView listView = (ListView) findViewById(R.id.listView);
        list = new ArrayList<HashMap<String, String>>();
        ListViewAdapter adapter = new ListViewAdapter(MainActivity.this, list);
        listView.setAdapter(adapter);

        new AsyncTask<Void, Void, List<MoneyInfo>>(){
            @Override
            protected List<MoneyInfo> doInBackground(Void... voids) {
                List<MoneyInfo> result  = moneyDB.moneyInfoDAO().queryAll();
                Log.d("a", "test xD");
                return result;
            }

            @Override
            protected void onPostExecute(List<MoneyInfo> moneyInfo) {

                data = moneyDB.moneyInfoDAO().queryAll();
                for (int i = 0; i < data.size(); i++){
                    HashMap<String,String> temp=new HashMap<String, String>();
                    temp.put(FIRST_COLUMN, data.get(i).getType());
                    temp.put(SECOND_COLUMN, data.get(i).getDetail());
                    temp.put(THIRD_COLUMN, Integer.toString(data.get(i).getMoney()));
                    list.add(temp);
                }
                Log.d("a", data.get(0).getType());
                Log.d("a", data.get(0).getDetail());
                Log.d("a", Integer.toString(data.get(0).getMoney()));
                listView.invalidateViews();
            }
        }.execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                int pos=position;
                Log.d("a", Integer.toString(pos));
            }

        });

        Button addButton = findViewById(R.id.AddButton);
        addButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.AddButton){
            Intent intent = new Intent(this, AddActivity.class);
            Log.d("intent", "onClick: clicked");
            startActivity(intent);
            finish();
        }
    }
}
