package com.example.hp.game2048;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by HP on 2017/1/14.
 */

public class GameView extends GridLayout {

    public GameView(Context context) {
        super(context);
        initGameView();
    }

    /*public GameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }*/

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs) {

        super(context, attrs);
        initGameView();
    }
    private void initGameView(){
        setColumnCount(4);
        setBackgroundColor(0xffbbada0);
        setOnTouchListener(new View.OnTouchListener(){
            private float startX,startY,offsetX,offsetY;
            @Override
            public boolean onTouch(View v, MotionEvent event){
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX=event.getX();
                        startY=event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX=event.getX()-startX;
                        offsetY=event.getY()-startY;

                        if (Math.abs(offsetX)>Math.abs(offsetY)) {//此种情况说明手指欲在水平方向上滑动
                            if(offsetX<-5){
                                swipeLeft();
                            }else if(offsetX>5){
                                swipeRight();
                            }
                        }else{
                            if(offsetY<-5){
                                swipeUp();
                            }
                            else if(offsetY>5){
                                swipeDown();
                            }
                        }

                        break;
                    default:
                        break;
                }
                return true;
            }
        });

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int cardWidth=(Math.min(w,h)-10)/4;

        addCards(cardWidth,cardWidth);

        startGame();
    }

    private void startGame(){
        for (int y=0;y<4;y++)
            for(int x=0;x<4;x++){
                cardsMap[x][y].setNum(0);

            }
        addRandom();//一开始需要添加两个随机数，产生两个方格
        addRandom();
    }
    private void addCards(int cardWidth,int cardHeight){
        Card c;
        for (int y=0;y<4;y++){
            for(int x=0;x<4;x++){
                c=new Card(getContext());
                c.setNum(0);
                addView(c,cardWidth,cardHeight);
                cardsMap[x][y]= c;
            }
        }

    }
    private void addRandom(){
        EmptyPoints.clear();
        for (int y=0;y<4;y++)
            for(int x=0;x<4;x++) {
                if (cardsMap[x][y].getNum() <= 0) {
                    EmptyPoints.add(new Point(x,y));

                }
            }
        Point p=EmptyPoints.remove((int)(Math.random()*EmptyPoints.size()));//不理解！！！！
        cardsMap[p.x][p.y].setNum(Math.random()>0.1?2:4);//产生2和4的概率比是1：9
    }

    private void swipeLeft(){
        for (int y=0;y<4;y++)
            for(int x=0;x<4;x++)
                for(int x1=x+1;x1<4;x1++){
                    if(cardsMap[x1][y].getNum()>0){//如果获取到了某块非空的话--
                        if(cardsMap[x][y].getNum()<=0){//如果当前块是空块的话
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum());
                            cardsMap[x][y].setNum(0);
                        }
                        else if(cardsMap[x][y].equals(cardsMap[x1][y])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);//相同的块折叠

                        }

                    }
        }


    }
    private void swipeRight(){

    }
    private void swipeUp(){

    }
    private void swipeDown(){

    }
    private Card[][] cardsMap=new Card[4][4];
    private List<Point> EmptyPoints=new ArrayList<>();

}
