package com.madhumarga.app.ui.settings

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationCompat
import com.madhumarga.app.R

class SettingsActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    companion object {
        const val PREFS_NAME = "madhumarga_prefs"
        const val KEY_DARK_MODE = "dark_mode"
        const val KEY_NOTIFICATIONS = "notifications_enabled"
        const val CHANNEL_ID = "madhumarga_alerts"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        val switchDark = findViewById<Switch>(R.id.switchDark)
        val switchNotification = findViewById<Switch>(R.id.switchNotification)

        // Restore saved state
        switchDark.isChecked = prefs.getBoolean(KEY_DARK_MODE, false)
        switchNotification.isChecked = prefs.getBoolean(KEY_NOTIFICATIONS, true)

        // Dark mode toggle
        switchDark.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean(KEY_DARK_MODE, isChecked).apply()
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }

        // Notification toggle
        switchNotification.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean(KEY_NOTIFICATIONS, isChecked).apply()
            if (isChecked) {
                createNotificationChannel()
                sendTestNotification()
            }
        }

        // Create channel on open (needed for Android 8+)
        createNotificationChannel()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Hive Alerts",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notifications for low activity hives and pest alerts"
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun sendTestNotification() {
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_bee)
            .setContentTitle("Notifications Enabled")
            .setContentText("You'll receive alerts for hive activity and pest sightings.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()
        manager.notify(1001, notification)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}