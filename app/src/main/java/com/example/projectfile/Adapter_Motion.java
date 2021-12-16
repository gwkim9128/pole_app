package com.example.projectfile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter_Motion extends BaseAdapter {



    public ArrayList<MotionVO> list = new ArrayList<MotionVO>();
    public Adapter_Motion(){

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;
        final Context context = parent.getContext();

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row, parent, false);
        }

        TextView sensor_value = (TextView) convertView.findViewById(R.id.sensor_value);
        TextView content = (TextView) convertView.findViewById(R.id.content);
        TextView day = (TextView) convertView.findViewById(R.id.day);

        MotionVO bVO = list.get(position);

        sensor_value.setText(bVO.getMotion()+"");
        content.setText(bVO.getContent()+"");
        day.setText(bVO.getDay());

        return convertView;
    }

    public void addItem(int motion, String content, String day){
        MotionVO item = new MotionVO();

        item.setMotion(motion);
        item.setContent(content);
        item.setDay(day);

        list.add(item);
    }
}
