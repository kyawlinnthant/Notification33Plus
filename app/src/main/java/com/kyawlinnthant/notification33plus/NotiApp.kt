package com.kyawlinnthant.notification33plus

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.kyawlinnthant.notification33plus.notification.NotificationServiceHandler

class NotiApp : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val simpleChannel = NotificationChannel(
                NotificationServiceHandler.SIMPLE_CHANNEL_ID,
                NotificationServiceHandler.SIMPLE_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = NotificationServiceHandler.SIMPLE_CHANNEL_DESCRIPTION
            }
            val actionChannel = NotificationChannel(
                NotificationServiceHandler.ACTION_CHANNEL_ID,
                NotificationServiceHandler.ACTION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = NotificationServiceHandler.ACTION_CHANNEL_DESCRIPTION
            }
            val messageChannel = NotificationChannel(
                NotificationServiceHandler.MESSAGE_CHANNEL_ID,
                NotificationServiceHandler.MESSAGE_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = NotificationServiceHandler.MESSAGE_CHANNEL_DESCRIPTION
            }
            val callChannel = NotificationChannel(
                NotificationServiceHandler.CALL_CHANNEL_ID,
                NotificationServiceHandler.CALL_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = NotificationServiceHandler.CALL_CHANNEL_DESCRIPTION
            }
            val channels = listOf(
                simpleChannel,
                actionChannel,
                messageChannel,
                callChannel,
            )

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.apply {
                createNotificationChannels(channels)
            }
        }
    }
}