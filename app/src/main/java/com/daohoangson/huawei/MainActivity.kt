package com.daohoangson.huawei

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var txt: TextView
    private var receiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt = findViewById(R.id.txt)
    }

    override fun onResume() {
        super.onResume()

        receiver = MyPushReceiver();
        val filter = IntentFilter()
        filter.addAction(MyPushService.INTENT_ACTION_ON_NEW_TOKEN);
        registerReceiver(receiver, filter);
    }

    override fun onPause() {
        super.onPause()

        if (receiver != null) {
            unregisterReceiver(receiver)
            receiver = null
        }
    }

    inner class MyPushReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (MyPushService.INTENT_ACTION_ON_NEW_TOKEN == intent!!.action) {
                txt.text = intent.getStringExtra("token")
            }
        }
    }
}

