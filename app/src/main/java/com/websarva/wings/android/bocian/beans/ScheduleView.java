package com.websarva.wings.android.bocian.beans;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class ScheduleView extends View {
    private Bitmap bmp, bmpSea;
    private Paint pText, pLine;

    public ScheduleView(Context context) {
        super(context);
        pText = new Paint();
        pLine = new Paint();
    }

    public ScheduleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScheduleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        // 研修用描画色 9:00~10:00
        // 【矩形】(1000, 100)->(1050, 150)の正方形
        Paint pRect = new Paint();
        pRect.setARGB(255,60,179,113);
        pRect.setStrokeWidth(5);
        pRect.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(218, 0, 432, 300, pRect);

        // 打ち合わせ用描画色 10:00~11:00
        // 【矩形】(1000, 100)->(1050, 150)の正方形
        Paint pRect1 = new Paint();
        pRect1.setARGB(255,255,165,80);
        pRect1.setStrokeWidth(5);
        pRect1.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(433, 300, 646, 598, pRect1);

        // 打ち合わせ用描画色 10:00~11:00
        // 【矩形】(1000, 100)->(1050, 150)の正方形
        Paint pRect2 = new Paint();
        pRect2.setARGB(175,139,0,0);
        pRect2.setStrokeWidth(5);
        pRect2.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(649, 600, 862, 899, pRect2);

        // アイディア会議用描画色 9:30~10:30
        // 【矩形】(1000, 100)->(1050, 150)の正方形
        Paint pRect3 = new Paint();
        pRect3.setARGB(200,65,105,225);
        pRect3.setStrokeWidth(5);
        pRect3.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(866, 150, 1080, 450, pRect3);

        for(int i = 0;i<12;i++) {
            // 【テキスト】フォント、サイズ、位置、拡大、ずらし
            Paint pText = new Paint();
            pText.setColor(Color.BLACK);
            pText.setTypeface(Typeface.SERIF);
            pText.setTextSize(40);
            canvas.drawText((i + 9) + ":00", 100, 30 + (300 * i) , pText);
        }

        // 【テキスト】フォント、サイズ、位置、拡大、ずらし
        Paint pText = new Paint();
        pText.setColor(Color.BLACK);
        pText.setTypeface(Typeface.SERIF);
        pText.setTextSize(40);
        canvas.drawText("研修", 285, 100 , pText);

        // 【テキスト】フォント、サイズ、位置、拡大、ずらし
        Paint pText2 = new Paint();
        pText2.setColor(Color.BLACK);
        pText2.setTypeface(Typeface.SERIF);
        pText2.setTextSize(40);
        canvas.drawText("打ち合わせ", 440, 400 , pText2);

        // 【テキスト】フォント、サイズ、位置、拡大、ずらし
        Paint pText3 = new Paint();
        pText3.setColor(Color.BLACK);
        pText3.setTypeface(Typeface.SERIF);
        pText3.setTextSize(40);
        canvas.drawText("打ち合わせ", 655, 700 , pText3);

        // 【テキスト】フォント、サイズ、位置、拡大、ずらし
        Paint pText4 = new Paint();
        pText4.setColor(Color.BLACK);
        pText4.setTypeface(Typeface.SERIF);
        pText4.setTextSize(40);
        canvas.drawText("アイディア", 870, 225 , pText4);
        canvas.drawText("会議", 935, 270 , pText4);


        for(int i = 0;i<5;i++){
            // 【直線】縦列
            Paint pLine = new Paint();
            pLine.setColor(Color.BLACK);
            pLine.setStrokeWidth(2);
            pLine.setStrokeCap(Paint.Cap.BUTT);
            canvas.drawLine(216*i, 0, 216*i, 3600, pLine);
        }

        for(int i = 0;i<49;i++){
            // 【直線】横列
            Paint pLine = new Paint();
            pLine.setColor(Color.BLACK);
            pLine.setStrokeWidth(2);
            pLine.setStrokeCap(Paint.Cap.BUTT);
            if(i <= 2)
                canvas.drawLine(432, 75 * i, 1080, 75 * i, pLine);

            else if(i <= 3)
                canvas.drawLine(432, 75 * i, 864, 75 * i, pLine);

            else if(i == 4)
                canvas.drawLine(216, 75 * i, 864, 75 * i, pLine);

            else if(i == 5) {
                canvas.drawLine(216, 75 * i, 432, 75 * i, pLine);
                canvas.drawLine(648, 75 * i, 864, 75 * i, pLine);
            }

            else if(i >= 6 && i <=8){
                canvas.drawLine(216, 75 * i, 432, 75 * i, pLine);
                canvas.drawLine(648, 75 * i, 1080, 75 * i, pLine);
            }

            else if(i >= 9 && i <= 11){
                canvas.drawLine(216, 75 * i, 648, 75 * i, pLine);
                canvas.drawLine(864, 75 * i, 1080, 75 * i, pLine);
            }

            else canvas.drawLine(216, 75 * i, 1080, 75 * i, pLine);

        }





    }
}