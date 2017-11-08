package kmitl.lab09.paniti58070080.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.util.List;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    MoneyDB moneyDB;
    ToggleButton income;
    ToggleButton expenses;

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
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.applyButton){
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
