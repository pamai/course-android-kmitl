package kmitl.lab09.paniti58070080.moneyflow;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
    private int moneyIn = 0;
    private int moneyOut = 0;
    private int money = 0;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        builder = new AlertDialog.Builder(this);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Edit");
        arrayAdapter.add("Delete");
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });

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
                    if (data.get(i).getType().equals("+")){
                        moneyIn += data.get(i).getMoney();
                    }
                    else {
                        moneyOut += data.get(i).getMoney();
                    }
                }
                money = moneyIn - moneyOut;
                TextView moneyView = findViewById(R.id.moneyView);
                if (data.size() == 0){
                    moneyView.setTextColor(Color.rgb(0, 0, 0));
                }
                else if (money > (moneyIn * 0.5)){
                    moneyView.setTextColor(Color.rgb(0, 255, 0));
                }
                else if (money >= (moneyIn * 0.25)){
                    moneyView.setTextColor(Color.rgb(255, 255, 0));
                }
                else {
                    moneyView.setTextColor(Color.rgb(255, 0, 0));
                }
                moneyView.setText("เงินคงเหลือ\n"+Integer.toString(money));
                listView.invalidateViews();
            }
        }.execute();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {

                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0){
                            Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                            intent.putExtra("position", position);
                            startActivity(intent);
                        }
                        else {
                            new AsyncTask<Void, Void, List<MoneyInfo>>(){
                                @Override
                                protected List<MoneyInfo> doInBackground(Void... voids) {
                                    List<MoneyInfo> result  = moneyDB.moneyInfoDAO().queryAll();
                                    return result;
                                }

                                @Override
                                protected void onPostExecute(List<MoneyInfo> moneyInfo) {
                                    Log.d("delete", "delete");
                                    moneyDB.moneyInfoDAO().delete(moneyInfo.get(position).getId());
                                    Intent intent = getIntent();
                                    overridePendingTransition(0, 0);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    finish();
                                    overridePendingTransition(0, 0);
                                    startActivity(intent);
                                }
                            }.execute();
                        }

                    }
                });
                builder.show();

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
