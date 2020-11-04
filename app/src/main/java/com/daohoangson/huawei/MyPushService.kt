package com.daohoangson.huawei

import android.content.Intent
import android.util.Log
import com.huawei.hms.push.HmsMessageService

class MyPushService : HmsMessageService() {

    companion object {
        const val INTENT_ACTION_ON_NEW_TOKEN = "com.daohoangson.huawei.ON_NEW_TOKEN"
        const val TAG = "MyPushService"
    }

    override fun onNewToken(token: String?) {
        super.onNewToken(token)

        Log.v(TAG, "token=$token")

        val intent = Intent(INTENT_ACTION_ON_NEW_TOKEN)
        intent.putExtra("token", token)
        sendBroadcast(intent)
    }
}
