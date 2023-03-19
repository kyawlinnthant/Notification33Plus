package com.kyawlinnthant.notification33plus.action

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.kyawlinnthant.notification33plus.notification.NotificationServiceHandler

class ActionNotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val service = NotificationServiceHandler(context)
        service.showActionNotification(counter = ++Counter.value)
    }
}