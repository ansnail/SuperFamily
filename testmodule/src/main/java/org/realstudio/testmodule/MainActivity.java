package org.realstudio.testmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import top.androidman.SuperButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SuperButton button = findViewById(R.id.sl_normal);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "是不是很神奇", Toast.LENGTH_SHORT).show();
            }
        });

        button.getPlasterer().getTextView().setText("尝试一下");
        button.getPlasterer()
                .setMaxLength(3)
                .setText("窗前明月光")
                .startPaint();
    }
}
