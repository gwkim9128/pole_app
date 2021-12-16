package com.example.projectfile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    public ArrayList<ListVO> listViewItemList = new ArrayList<ListVO>();

    public Adapter(){

    }

    @Override
    public int getCount() {
        return listViewItemList.size(); //
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, parent, false);
        }

        //추가수정시 건들부분
        TextView number = (TextView) convertView.findViewById(R.id.number);
        TextView business = (TextView) convertView.findViewById(R.id.business);
        TextView location = (TextView) convertView.findViewById(R.id.location);

        ListVO listViewItem = listViewItemList.get(position); //

        number.setText(listViewItem.getNumber());
        business.setText(listViewItem.getBusiness());
        location.setText(listViewItem.getLocation());

        //여기까지

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position); //
    }

    //여기도
    public void addItem(String number, String business, String location){
        ListVO item = new ListVO();

        item.setNumber(number);
        item.setBusiness(business);
        item.setLocation(location);

        listViewItemList.add(item);
    }

}


