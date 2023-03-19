package com.kyawlinnthant.notification33plus.notification

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.kyawlinnthant.notification33plus.MainActivity
import com.kyawlinnthant.notification33plus.R
import com.kyawlinnthant.notification33plus.action.ActionNotificationReceiver

class NotificationServiceHandler constructor(
    private val context: Context
) : NotificationService {
    companion object {
        const val SIMPLE_CHANNEL_ID = "simple.id"
        const val SIMPLE_CHANNEL_NAME = "Simple Notification"
        const val SIMPLE_CHANNEL_DESCRIPTION = "Simple notification description"
        const val ACTION_CHANNEL_ID = "action.id"
        const val ACTION_CHANNEL_NAME = "Action Notification"
        const val ACTION_CHANNEL_DESCRIPTION = "Action notification description"
        const val MESSAGE_CHANNEL_ID = "message.id"
        const val MESSAGE_CHANNEL_NAME = "Message Notification"
        const val MESSAGE_CHANNEL_DESCRIPTION = "Message notification description"
        const val CALL_CHANNEL_ID = "call.id"
        const val CALL_CHANNEL_NAME = "Call Notification"
        const val CALL_CHANNEL_DESCRIPTION = "Call notification description"
        private const val SIMPLE_REQUEST_CODE = 1
        private const val ACTION_REQUEST_CODE = 2
        private const val COUNTER_REQUEST_CODE = 100
        private const val MESSAGE_REQUEST_CODE = 3
        private const val CALL_REQUEST_CODE = 4
    }

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private fun makeSimpleNotification(intent: PendingIntent): Notification {
        return NotificationCompat.Builder(context, SIMPLE_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_simple)
            .setContentTitle("Simple Notification Title")
            .setContentText("Simple Notification Content Text")
            .setContentIntent(intent)
            .build()
    }

    private fun makePendingIntentForSimple(): PendingIntent {
        val simpleIntent = Intent(context, MainActivity::class.java)
        return PendingIntent.getActivity(
            context,
            SIMPLE_REQUEST_CODE,
            simpleIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun makePendingIntentForCounter(): PendingIntent {
        return PendingIntent.getBroadcast(
            context,
            COUNTER_REQUEST_CODE,
            Intent(context, ActionNotificationReceiver::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun makeActionNotification(
        showIntent: PendingIntent,
        counterIntent: PendingIntent,
        counter: Int
    ): Notification {
        return NotificationCompat.Builder(context, ACTION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_action)
            .setContentTitle("Action Notification Title")
            .setContentText("Current counter is $counter")
            .setContentIntent(showIntent)
            .addAction(
                R.drawable.ic_action,
                "Increase",
                counterIntent
            )
            .build()
    }

    private fun makePendingIntentForAction(): PendingIntent {
        val simpleIntent = Intent(context, MainActivity::class.java)
        return PendingIntent.getActivity(
            context,
            SIMPLE_REQUEST_CODE,
            simpleIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    override fun showSimpleNotification() {
        val simpleIntent = makePendingIntentForSimple()
        val simpleNotification = makeSimpleNotification(simpleIntent)
        notificationManager.notify(SIMPLE_REQUEST_CODE, simpleNotification)
    }

    override fun showActionNotification(counter: Int) {
        val counterIntent = makePendingIntentForCounter()
        val actionIntent = makePendingIntentForAction()
        val actionNotification = makeActionNotification(
            showIntent = actionIntent,
            counterIntent = counterIntent,
            counter = counter
        )
        notificationManager.notify(ACTION_REQUEST_CODE, actionNotification)
    }


}