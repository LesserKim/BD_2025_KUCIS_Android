package com.example.learningviews

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.learningviews.ui.theme.LearningViewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearningViewsTheme {
                MyXmlScreen()
            }
        }
    }
}

@Composable
fun MyXmlScreen() {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->

            LayoutInflater.from(context)
                .inflate(R.layout.activity_main, null) as LinearLayout
        },
        update = { layout ->

            val textLogin = layout.findViewById<TextView>(R.id.textLogin)
            val textSignUp = layout.findViewById<TextView>(R.id.textSignUp)
            val indicator = layout.findViewById<View>(R.id.indicator)
            val greyColor = ContextCompat.getColor(layout.context, android.R.color.darker_gray)
            val blueColor = Color.parseColor("#0569FB")


            textLogin.post {

                val params = indicator.layoutParams
                params.width = textLogin.width
                indicator.layoutParams = params

                indicator.x = textLogin.x
            }


            textLogin.setOnClickListener {

                textLogin.setTextColor(blueColor)
                textSignUp.setTextColor(greyColor)


                indicator.animate().x(textLogin.x).setDuration(250).start()
            }


            textSignUp.setOnClickListener {

                textLogin.setTextColor(greyColor)
                textSignUp.setTextColor(blueColor)


                indicator.animate().x(textSignUp.x).setDuration(250).start()
            }
        }
    )
}