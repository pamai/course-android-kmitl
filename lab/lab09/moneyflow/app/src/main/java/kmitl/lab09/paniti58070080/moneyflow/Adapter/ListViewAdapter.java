package kmitl.lab09.paniti58070080.moneyflow.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import kmitl.lab09.paniti58070080.moneyflow.R;

import static kmitl.lab09.paniti58070080.moneyflow.Constants.FIRST_COLUMN;
import static kmitl.lab09.paniti58070080.moneyflow.Constants.SECOND_COLUMN;
import static kmitl.lab09.paniti58070080.moneyflow.Constants.THIRD_COLUMN;

public class ListViewAdapter extends BaseAdapter{

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    TextView type;
    TextView detail;
    TextView money;

    public ListViewAdapter(Activity activity,ArrayList<HashMap<String, String>> list){
        super();
        this.activity=activity;
        this.list=list;
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = activity.getLayoutInflater();
        if(view ==null){
            view = inflater.inflate(R.layout.listview_colmn, null);
            type = (TextView) view.findViewById(R.id.type);
            detail = (TextView) view.findViewById(R.id.detail);
            money = (TextView) view.findViewById(R.id.money);
        }

        HashMap<String, String> map = list.get(position);
        type.setText(map.get(FIRST_COLUMN));
        detail.setText(map.get(SECOND_COLUMN));
        money.setText(map.get(THIRD_COLUMN));

        return view;
    }
}
