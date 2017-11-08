package kmitl.lab09.paniti58070080.moneyflow;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.util.List;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    private MoneyDB moneyDB;
    private ToggleButton income;
    private ToggleButton expenses;
    private int position;
    private EditText detail;
    private EditText money;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_info);

        moneyDB = Room.databaseBuilder(getApplicationContext(), MoneyDB.class, "money").allowMainThreadQueries()
                                .build();
        Button applyButton = findViewById(R.id.applyButton);
        income = findViewById(R.id.toggleIncome);
        expenses = findViewById(R.id.toggleExpenses);
        applyButton.setOnClickListener(this);
        income.setOnClickListener(this);
        expenses.setOnClickListener(this);
        detail = findViewById(R.id.editDetail);
        money = findViewById(R.id.editMoney);

        Intent intent = getIntent();
        position = intent.getIntExtra("position",-1);
        if (position >= 0){
            new AsyncTask<Void, Void, List<MoneyInfo>>() {
                @Override
                protected List<MoneyInfo> doInBackground(Void... voids) {
                    List<MoneyInfo> data = moneyDB.moneyInfoDAO().queryAll();
                    return data;
                }

                @Override
                protected void onPostExecute(List<MoneyInfo> moneyInfos) {
                    detail.setText(moneyInfos.get(position).getDetail());
                    money.setText(Integer.toString(moneyInfos.get(position).getMoney()));
                    if (moneyInfos.get(position).getType().equals("+")){
                        income.setChecked(true);
                        expenses.setChecked(false);
                    }
                    else {
                        income.setChecked(false);
                        expenses.setChecked(true);
                    }
                }
            }.execute();


        }

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.applyButton){
            if (position >= 0){
                Log.d("Edit", "Editing");
                new AsyncTask<Void, Void, List<MoneyInfo>>(){

                    @Override
                    protected List<MoneyInfo> doInBackground(Void... voids) {
                        List<MoneyInfo> result  = moneyDB.moneyInfoDAO().queryAll();
                        return result;
                    }

                    @Override
                    protected void onPostExecute(List<MoneyInfo> moneyInfos) {
                        String type;
                        if (income.isChecked()){
                            type = "+";
                        }
                        else {
                            type = "-";
                        }
                        String detail = ((EditText) findViewById(R.id.editDetail)).getText().toString();
                        int money = Integer.parseInt(((EditText) findViewById(R.id.editMoney)).getText().toString());
                        MoneyInfo moneyInfo = new MoneyInfo();
                        moneyInfo.setType(type);
                        moneyInfo.setDetail(detail);
                        moneyInfo.setMoney(money);
                        moneyInfo.setId(moneyInfos.get(position).getId());
                        moneyDB.moneyInfoDAO().updateData(moneyInfo);
                    }
                }.execute();

            }
            else {
                Log.d("Insert", "Insert");
            new AsyncTask<Void, Void, List<MoneyInfo>>(){

                @Override
                protected List<MoneyInfo> doInBackground(Void... voids) {
                    List<MoneyInfo> result  = moneyDB.moneyInfoDAO().queryAll();
                    return result;
                }

                @Override
                protected void onPostExecute(List<MoneyInfo> moneyInfos) {
                    String type;
                    if (income.isChecked()){
                        type = "+";
                    }
                    else {
                        type = "-";
                    }
                    String detail = ((EditText) findViewById(R.id.editDetail)).getText().toString();
                    int money = Integer.parseInt(((EditText) findViewById(R.id.editMoney)).getText().toString());
                    MoneyInfo moneyInfo = new MoneyInfo();
                    moneyInfo.setType(type);
                    moneyInfo.setDetail(detail);
                    moneyInfo.setMoney(money);
                    moneyDB.moneyInfoDAO().insertInfo(moneyInfo);
                }
            }.execute();
            }
            Intent intent = new Intent(this, MainActivity.class);
            Log.d("intent", "onClick: clicked");
            startActivity(intent);
            finish();
        }
        else if (view.getId() == R.id.toggleIncome){
            if (income.isChecked()) {
                expenses.setChecked(false);
            }
        }
        else if (view.getId() == R.id.toggleExpenses){
            if (expenses.isChecked()) {
                income.setChecked(false);
            }
        }
    }
}
