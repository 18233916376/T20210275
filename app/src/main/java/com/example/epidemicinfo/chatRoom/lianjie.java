package com.example.epidemicinfo.chatRoom;
import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by Sunlei on 2020/1/2.
 */

public class lianjie implements Runnable {
    private ChatroomActivity chatroom;
    private Socket socket = null;
    final Handler handler = new Handler();

    public lianjie(ChatroomActivity chatroom) {
        chatroom = this.chatroom;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            socket = new Socket("xxx.xxx.xxx.xxx", 61666);//第一个参数服务器的ip，第二个参数是端口号
            //服务器连接成功的话则发一个Message给UI线程 跳到MainActivity.java的第24行
            Message msg = new Message();
            msg.what = 1;
            handler.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        //连接服务器成功之后开始接受消息
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            while (true) {
                String s = in.readLine();
                if (s == null || s.equals("")) break;
                //收到消息之后便new一个xx类，第一个参数是信息内容，第二个参数是头像ID
                //第三个参数显示在左边还是右边，没有第三个参数的话默认显示在左边
                //我事先准备了两张头像
                //最后将他添加到list里
                chatroom.list.add(new Msg(s, 0));
                //收到消息后就要更新RecyclerView将他们显示出来，跳到MainActivity.java的第24行
                Message msg = new Message();
                msg.what = 2;
                handler.sendMessage(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(final String s) {//发送消息
        new Thread(new Runnable() {
            @Override
            public void run() {//我发现有的手机在UI线程发消息会崩溃，而有的不会
                // 这里就在一个新的线程发消息
                BufferedWriter out = null;
                try {
                    out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
                    out.write(s + "\r\n");
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}