package com.example.epidemicinfo.chatRoom;

import androidx.appcompat.app.AppCompatActivity;
import com.example.epidemicinfo.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class ChatroomActivity extends AppCompatActivity {

    private EditText input;
    private Button send;
    public List list;
    private lianjie lj;
    private MyAdapter myAdapter;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        list = new ArrayList<Msg>();
        listView= findViewById(R.id.listview);

        lj = new lianjie(this);

        input = findViewById(R.id.text);
        send = findViewById(R.id.bt);
        myAdapter=new MyAdapter(this,R.layout.message,list);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = input.getText().toString();
                if (s == null || s.equals("")) {
                    Toast.makeText(getApplicationContext(), "发送消息不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    list.add(new Msg(s, 1));
                    //new一个Msg类，第一个参数的信息的内容，第二个参数表示左右
                    //0为左边，1为右
                    listView.setAdapter(myAdapter);
                    input.setText(null);
                    lj.send(s);//发送消息
                }

            }
        });

    }





}