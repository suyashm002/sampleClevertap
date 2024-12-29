package com.suyash.loginexample


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.suyash.loginexample.ui.CameraActivity
import com.suyash.loginexample.ui.LoginScreen
import com.suyash.loginexample.ui.ReadingsListActivity
import com.suyash.loginexample.ui.ReportActivity
import com.suyash.loginexample.ui.theme.LoginExampleTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setupClickListeners()
    }
    private fun setupClickListeners() {
        findViewById<Button>(R.id.btn_capture).setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }

        findViewById<Button>(R.id.btn_readings).setOnClickListener {
            startActivity(Intent(this, ReadingsListActivity::class.java))
        }

        findViewById<Button>(R.id.btn_report).setOnClickListener {
            startActivity(Intent(this, ReportActivity::class.java))
        }
    }
}

