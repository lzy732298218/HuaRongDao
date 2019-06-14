package com.example.android.huarongdao;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    Button Qz[] = new Button[10];
    int BG[][] = new int[5][4];
    TextView txt1;
    TextView txt2;
    float SW;
    float x1, x2, y1, y2;
    int Step;
    {
        Step = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Qz[0] = findViewById(R.id.Qz1);
        Qz[1] = findViewById(R.id.Qz2);
        Qz[2] = findViewById(R.id.Qz3);
        Qz[3] = findViewById(R.id.Qz4);
        Qz[4] = findViewById(R.id.Qz5);
        Qz[5] = findViewById(R.id.Qz6);
        Qz[6] = findViewById(R.id.Qz7);
        Qz[7] = findViewById(R.id.Qz8);
        Qz[8] = findViewById(R.id.Qz9);
        Qz[9] = findViewById(R.id.Qz10);

        txt1 = findViewById(R.id.Text1);
        txt2 = findViewById(R.id.Text2);

        for (int i = 0; i < 10; i++)
            Qz[i].setOnTouchListener(new mTouch());

        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 4; j++)
                BG[i][j] = 1;
        BG[4][1] = 0;
        BG[4][2] = 0;

        boolean post = txt1.post(new Runnable() {
            @Override
            public void run() {
                txt1.setText("欢迎来到华容道");
                SW = txt1.getWidth();
                init();
            }
        });

    }

    public class mTouch implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int type; // 1 bing    2  zhangfei  3 guanyu 4 caocao
            int r, c;
            if (v.getWidth() == v.getHeight()) {
                if (v.getHeight() > 300)
                    type = 4;
                else
                    type = 1;

            } else {
                if (v.getHeight() > v.getWidth())
                    type = 2;
                else
                    type = 3;
            }

            r = (int) (v.getY() / 270f);
            c = (int) (v.getX() / 270f);
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                x1 = event.getX();
                y1 = event.getY();
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                x2 = event.getX();
                y2 = event.getY();
                if (y1 - y2 > 50) //"向上滑:"
                {
                    switch (type) {
                        case 1:
                            if (r > 0 && BG[r - 1][c] == 0) {
                                SetPois(v, r - 1, c);
                                BG[r - 1][c] = 1;
                                BG[r][c] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 2:
                            if (r > 0 && BG[r - 1][c] == 0) {
                                SetPois(v, r - 1, c);
                                BG[r - 1][c] = 1;
                                BG[r + 1][c] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 3:
                            if (r > 0 && BG[r - 1][c] == 0 && BG[r - 1][c + 1] == 0) {
                                SetPois(v, r - 1, c);
                                BG[r - 1][c] = BG[r - 1][c + 1] = 1;
                                BG[r][c] = BG[r][c + 1] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 4:
                            if (r > 0 && BG[r - 1][c] == 0 && BG[r - 1][c + 1] == 0) {
                                SetPois(v, r - 1, c);
                                BG[r - 1][c] = BG[r - 1][c + 1] = 1;
                                BG[r + 1][c] = BG[r + 1][c + 1] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;

                    }

                } else if (y2 - y1 > 50) //向下滑
                {
                    switch (type) {
                        case 1:
                            if (r < 4 && BG[r + 1][c] == 0) {
                                SetPois(v, r + 1, c);
                                BG[r + 1][c] = 1;
                                BG[r][c] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 2:
                            if (r < 3 && BG[r + 2][c] == 0) {
                                SetPois(v, r + 1, c);
                                BG[r + 2][c] = 1;
                                BG[r][c] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }

                            break;
                        case 3:
                            if (r < 4 && BG[r + 1][c] == 0 && BG[r + 1][c + 1] == 0) {
                                SetPois(v, r + 1, c);
                                BG[r + 1][c] = BG[r + 1][c + 1] = 1;
                                BG[r][c] = BG[r][c + 1] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 4:
                            if (r < 3 && BG[r + 2][c] == 0 && BG[r + 2][c + 1] == 0) {
                                SetPois(v, r + 1, c);
                                BG[r + 2][c] = BG[r + 2][c + 1] = 1;
                                BG[r][c] = BG[r][c + 1] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                                if (r + 1 == 3 && c == 1) {
                                    txt1.setText("你赢了！共用" + Step + "步！");
                                    win(Step);
                                }
                            }
                            break;
                    }
                } else if (x1 - x2 > 50) //向左滑
                {
                    switch (type) {
                        case 1:
                            if (c > 0 && BG[r][c - 1] == 0) {
                                SetPois(v, r, c - 1);
                                BG[r][c - 1] = 1;
                                BG[r][c] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 2:
                            if (c > 0 && BG[r][c - 1] == 0 && BG[r + 1][c - 1] == 0) {
                                SetPois(v, r, c - 1);
                                BG[r][c - 1] = 1;
                                BG[r + 1][c - 1] = 1;
                                BG[r][c] = 0;
                                BG[r + 1][c] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 3:
                            if (c > 0 & BG[r][c - 1] == 0) {
                                SetPois(v, r, c - 1);
                                BG[r][c - 1] = 1;
                                BG[r][c + 1] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 4:
                            if (c > 0 && BG[r][c - 1] == 0 && BG[r + 1][c - 1] == 0) {
                                SetPois(v, r, c - 1);
                                BG[r][c - 1] = BG[r + 1][c - 1] = 1;
                                BG[r][c + 1] = BG[r + 1][c + 1] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                                if (r == 3 && c - 1 == 1) {
                                    txt1.setText("你赢了！共用" + Step + "步！");
                                    win(Step);
                                }
                            }
                            break;
                    }
                } else if (x2 - x1 > 50) //向右滑
                {
                    switch (type) {
                        case 1:
                            if (c < 3 && BG[r][c + 1] == 0) {
                                SetPois(v, r, c + 1);
                                BG[r][c + 1] = 1;
                                BG[r][c] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 2:
                            if (c < 3 & BG[r][c + 1] == 0 && BG[r + 1][c + 1] == 0) {
                                SetPois(v, r, c + 1);
                                BG[r][c + 1] = 1;
                                BG[r + 1][c + 1] = 1;
                                BG[r][c] = 0;
                                BG[r + 1][c] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 3:
                            if (c < 2 && BG[r][c + 2] == 0) {
                                SetPois(v, r, c + 1);
                                BG[r][c + 2] = 1;
                                BG[r][c] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 4:
                            if (c < 2 && BG[r][c + 2] == 0 && BG[r + 1][c + 2] == 0) {
                                SetPois(v, r, c + 1);
                                BG[r][c + 2] = BG[r + 1][c + 2] = 1;
                                BG[r][c] = BG[r + 1][c] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                                if (r == 3 && c + 1 == 1) {
                                    txt1.setText("你赢了！共用" + Step + "步！");
                                    win(Step);
                                }
                            }
                            break;
                    }
                }
            }
            return true;
        }
    }
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    void SetSize(Button v, int w, int h) {
        v.setWidth(240);
        v.setHeight(h * dip2px(getApplicationContext(), SW / 4));
    }

    void SetPois(View v, int r, int c) {
        v.setX(c * SW / 4f);
        v.setY(r * SW / 4f);
    }
    void win(int step){
        String text = getIntent().getStringExtra("text");
        if(text.equals("横刀立马")){
            SharedPreferences sharedPreferences = getSharedPreferences("横刀立马",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            int step1 = sharedPreferences.getInt("step",0);
            if(step1 == 0)
            {
                step = step;
            }
            if(step1 <= step){
                step = step1;
            }
            else{
                step = step;
            }
            editor.putInt("step",step);
            editor.commit();
        }
        if(text.equals("指挥若定")){
            SharedPreferences sharedPreferences = getSharedPreferences("指挥若定",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            int step1 = sharedPreferences.getInt("step",0);
            if(step1 == 0)
            {
                step = step;
            }
            if(step1 <= step){
                step = step1;
            }
            else{
                step = step;
            }
            editor.putInt("step",step);
            editor.commit();
        }
        if(text.equals("齐头并进")){
            SharedPreferences sharedPreferences = getSharedPreferences("齐头并进",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            int step1 = sharedPreferences.getInt("step",0);
            if(step1 == 0)
            {
                step = step;
            }
            if(step1 <= step){
                step = step1;
            }
            else{
                step = step;
            }
            editor.putInt("step",step);
            editor.commit();
        }
        AlertDialog.Builder myAlertBuilder =
                new AlertDialog.Builder(SecondActivity.this);
        myAlertBuilder.setTitle(R.string.alert_title);
        myAlertBuilder.setMessage("你已经走了：" + step + "步！");
        myAlertBuilder.setPositiveButton(R.string.ok_button,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        init();
                    }
                });

        myAlertBuilder.setNegativeButton(R.string.cancel_button,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
        myAlertBuilder.show();
    }

    void init() {
        SetSize(Qz[0], 1, 1);
        SetSize(Qz[1], 1, 1);
        SetSize(Qz[2], 1, 1);
        SetSize(Qz[3], 1, 1);
        SetSize(Qz[4], 1, 2);
        SetSize(Qz[5], 1, 2);
        SetSize(Qz[6], 1, 2);
        SetSize(Qz[7], 1, 2);
        SetSize(Qz[8], 2, 1);
        SetSize(Qz[9], 2, 2);
        txt1 = findViewById(R.id.Text1);
        txt1.setText("欢迎来到华容道");
        String text = getIntent().getStringExtra("text");
        if(text.equals("横刀立马")) {
            SetPois(Qz[0], 4, 0);
            SetPois(Qz[1], 3, 1);
            SetPois(Qz[2], 3, 2);
            SetPois(Qz[3], 4, 3);
            SetPois(Qz[4], 0, 0);
            SetPois(Qz[5], 0, 3);
            SetPois(Qz[6], 2, 0);
            SetPois(Qz[7], 2, 3);
            SetPois(Qz[8], 2, 1);
            SetPois(Qz[9], 0, 1);
            SharedPreferences sp = getSharedPreferences("横刀立马", Context.MODE_PRIVATE);
            int step = sp.getInt("step",0);
            txt2.setText("最好成绩："+step+"步");
        }
        if(text.equals("指挥若定")) {
            SetPois(Qz[0], 2, 0);
            SetPois(Qz[1], 3, 1);
            SetPois(Qz[2], 3, 2);
            SetPois(Qz[3], 2, 3);
            SetPois(Qz[4], 0, 0);
            SetPois(Qz[5], 0, 3);
            SetPois(Qz[6], 3, 0);
            SetPois(Qz[7], 3, 3);
            SetPois(Qz[8], 2, 1);
            SetPois(Qz[9], 0, 1);
            SharedPreferences sp = getSharedPreferences("指挥若定", Context.MODE_PRIVATE);
            int step = sp.getInt("step",0);
            txt2.setText("最好成绩："+step+"步");
        }
        if(text.equals("齐头并进")) {
            SetPois(Qz[0], 2, 0);
            SetPois(Qz[1], 2, 1);
            SetPois(Qz[2], 2, 2);
            SetPois(Qz[3], 2, 3);
            SetPois(Qz[4], 0, 0);
            SetPois(Qz[5], 0, 3);
            SetPois(Qz[6], 3, 0);
            SetPois(Qz[7], 3, 3);
            SetPois(Qz[8], 3, 1);
            SetPois(Qz[9], 0, 1);
            SharedPreferences sp = getSharedPreferences("齐头并进", Context.MODE_PRIVATE);
            int step = sp.getInt("step",0);
            txt2.setText("最好成绩："+step+"步");
        }

    }
}
