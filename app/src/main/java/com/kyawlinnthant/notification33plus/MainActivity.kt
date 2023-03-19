package com.kyawlinnthant.notification33plus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kyawlinnthant.notification33plus.action.Counter
import com.kyawlinnthant.notification33plus.notification.NotificationServiceHandler
import com.kyawlinnthant.notification33plus.theme.Notification33PlusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val service = NotificationServiceHandler(applicationContext)
        setContent {
            Notification33PlusTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SenderView(
                        onSimpleClicked = {
                            service.showSimpleNotification()
                        },
                        onActionClicked = {
                            service.showActionNotification(Counter.value)
                        },
                        onMessageClicked = {

                        },
                        onCallClicked = {

                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SenderView(
    modifier: Modifier = Modifier,
    onSimpleClicked: () -> Unit,
    onActionClicked: () -> Unit,
    onMessageClicked: () -> Unit,
    onCallClicked: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onSimpleClicked) {
            Text(text = "Send Simple Notification")
        }
        Button(onClick = onActionClicked) {
            Text(text = "Send Action Notification")
        }
        Button(onClick = onMessageClicked) {
            Text(text = "Send Message Notification")
        }
        Button(onClick = onCallClicked) {
            Text(text = "Send Call Notification")
        }
    }
}
