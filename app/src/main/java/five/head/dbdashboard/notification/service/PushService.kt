package five.head.dbdashboard.notification.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import five.head.dbdashboard.R

class PushService : FirebaseMessagingService(){
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "my_channel_id"
            val channelName = applicationContext
                .getString(R.string.crashes_in_database)
            val channelDescription = applicationContext
                .getString(R.string.notifications_will_notify_you_about_failures_in_database)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }
            val notificationManager = applicationContext.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("PushService", token)
    }
}