package com.example.epidemicinfo.chatRoom;

import android.widget.ArrayAdapter;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.epidemicinfo.R;

public class MyAdapter extends ArrayAdapter<Msg> {

    private int resourceID;

    public MyAdapter(Context context, int resource, List<Msg> objects) {
        super(context, resource, objects);
        resourceID = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Msg msg = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceID,  null);
            viewHolder = new ViewHolder();
            viewHolder.leftLayout = view.findViewById(R.id.receive);
            viewHolder.rightLayout =  view.findViewById(R.id.receive_message);
            viewHolder.leftMsg = view.findViewById(R.id.send);
            viewHolder.rightMsg = view.findViewById(R.id.send_message);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if(msg.getType() == Msg.MSG_RECEIVE) {
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.leftMsg.setText(msg.getMessage());
        }else {
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightMsg.setText(msg.getMessage());
        }
        return view;
    }

    class ViewHolder {
        RelativeLayout leftLayout;

        RelativeLayout rightLayout;

        TextView leftMsg;

        TextView rightMsg;

    }

}