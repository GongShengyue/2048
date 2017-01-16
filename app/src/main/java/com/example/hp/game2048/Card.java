package com.example.hp.game2048;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by HP on 2017/1/15.
 */

public class Card extends FrameLayout {
    public Card(Context context) {

        super(context);
        label=new TextView(getContext());
        label.setTextSize(32);
        label.setGravity(Gravity.CENTER);
        label.setBackgroundColor(0x33ffffff);
        LayoutParams lp=new LayoutParams(-1,-1);
        lp.setMargins(10,10,0,0);
        addView(label,lp);
        setNum(0);
    }
    private int num=0;

    public int getNum(){
        return  num;
    }
    public void setNum(int num){
        this.num=num;
        if (num<=0){
            label.setText("");
        }
        else {

            label.setText(num + "");
        }
    }


    public boolean equals(Card o) {//用来判断两张卡片的数值是否相等
        return getNum()==o.getNum();
    }

    private TextView label;
}
