package com.example.pc.rxandroiddemo;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.pc.rxandroiddemo.concatmap.ConcatActivity;
import com.example.pc.rxandroiddemo.demo1.DemoActivity1;
import com.example.pc.rxandroiddemo.demo2.DemoActivity2;
import com.example.pc.rxandroiddemo.demo3.DemoActivity3;
import com.example.pc.rxandroiddemo.demo4.DemoActivity4;
import com.example.pc.rxandroiddemo.demo5.DemoActivity5;
import com.example.pc.rxandroiddemo.flatmap.FlatMapActivity;
import com.example.pc.rxandroiddemo.map.MapOperator;
import com.example.pc.rxandroiddemo.merge.MergeOperatorActivity;

/**
 * Created by pc on 8/30/2018.
 */

public class FirstActivity extends AppCompatActivity implements View.OnClickListener{

    Button demo1,demo2,demo3,demo4,demo5,demo6,demo7,demo8,demo9,demo10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        demo1=findViewById(R.id.demo1);
        demo2=findViewById(R.id.demo2);
        demo3=findViewById(R.id.demo3);
        demo4=findViewById(R.id.demo4);
        demo5=findViewById(R.id.demo5);
        demo6=findViewById(R.id.demo6);
        demo7=findViewById(R.id.demo7);
        demo8=findViewById(R.id.demo8);
        demo9=findViewById(R.id.demo9);
        demo10=findViewById(R.id.demo10);

        demo1.setOnClickListener(this);
        demo2.setOnClickListener(this);
        demo3.setOnClickListener(this);
        demo4.setOnClickListener(this);
        demo5.setOnClickListener(this);
        demo6.setOnClickListener(this);
        demo7.setOnClickListener(this);
        demo8.setOnClickListener(this);
        demo9.setOnClickListener(this);
        demo10.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.demo1:
                startActivity(new Intent(FirstActivity.this, DemoActivity1.class));
                break;
            case R.id.demo2:
                startActivity(new Intent(FirstActivity.this, DemoActivity2.class));
                break;
            case R.id.demo3:
                startActivity(new Intent(FirstActivity.this, DemoActivity3.class));
                break;
            case R.id.demo4:
                startActivity(new Intent(FirstActivity.this, DemoActivity4.class));
                break;
            case R.id.demo5:
                startActivity(new Intent(FirstActivity.this, DemoActivity5.class));
                break;
            case R.id.demo6:
                startActivity(new Intent(FirstActivity.this, ConcatActivity.class));
                break;
            case R.id.demo7:
                startActivity(new Intent(FirstActivity.this, MergeOperatorActivity.class));
                break;
            case R.id.demo8:
                startActivity(new Intent(FirstActivity.this, MapOperator.class));
                break;
            case R.id.demo9:
                startActivity(new Intent(FirstActivity.this, FlatMapActivity.class));
                break;
            case R.id.demo10:
                startActivity(new Intent(FirstActivity.this, ConcatActivity.class));
                break;

        }
    }
}
