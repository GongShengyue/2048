package com.example.hp.game2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public  MainActivity(){
        mainActivity=this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvScore=(TextView)findViewById(R.id.tvScore);//使用了一个强制类型转换
    }
    public void clearScore(){
        score=0;
        showScore();
    }
    public void showScore(){//显示分数函数
        tvScore.setText(score+"");

    }
    public void addScore(int s){//计分函数
        score+=s;
        showScore();

    }
    private int score=0;
    private TextView tvScore;
    private static MainActivity mainActivity=null;
    public static MainActivity getMainActivity(){
        return mainActivity;
    }
}

