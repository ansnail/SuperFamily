package top.androidman.superbutton

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_main)

    }

    fun test(view: View?) {
        Toast.makeText(this, "是不是很神奇", Toast.LENGTH_SHORT).show()
    }
}