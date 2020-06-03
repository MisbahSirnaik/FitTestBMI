package com.example.fittestbmi;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class UserList extends ArrayAdapter<User> {

    private Activity context;
    private List<User> userList;

    public UserList(Activity context,List<User> userList)
    {
        super(context,R.layout.list_layout,userList);
        this.context=context;
        this.userList=userList;

    }


    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.list_layout,null,true);
        TextView textViewDate=(TextView)listViewItem.findViewById(R.id.tvdateval);
        TextView textViewBmi=(TextView)listViewItem.findViewById(R.id.tvbmival);
        TextView textViewCat=(TextView)listViewItem.findViewById(R.id.tvcategoryval);

        User user=userList.get(position);
        textViewDate.setText(user.getUserDate());
        textViewBmi.setText(user.getUserBmi());
        textViewCat.setText(user.getUserCat());

        return  listViewItem;
    }

}
