package top.androidman.superbutton;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private SuperButton mSuperButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSuperButton = findViewById(R.id.super_button);
    }

    public void test(View view) {
        mSuperButton.setText("疑是地上霜");
        mSuperButton.setColorNormal(Color.BLUE);
    }
}
