package org.abubaker.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.abubaker.composetutorial.ui.theme.ComposeTutorialTheme

class MainActivity : ComponentActivity() {

    /**
     * Message (Data Class)
     */
    data class Message(
        val author: String,
        val body: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // The setContent block defines the activity's layout where composable functions are called.
        setContent {

            ComposeTutorialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MessageCard(Message("Android", "Jetpack Compose"))
                }
            }

        }


    }

    /**
     * Layout options:
     * 1. Column  = Arrange items vertically
     * 2. Row = Arrange items horizontally
     * 3. Box = Stack Elements
     */
    @Composable
    fun MessageCard(msg: Message) {

        // Add a padding around our content = 8dp
        Row(modifier = Modifier.padding(all = 8.dp)) {

            // Image is a composable function that takes:
            // 1. A painter and
            // 2. Content description
            Image(

                // Load the image from the resources
                // painterResource is a function that takes an ID and returns a Painter
                painter = painterResource(id = R.drawable.profile_picture),

                // contentDescription is used for accessibility
                contentDescription = "Contact profile picture",

                // Modifier is used to apply styling to the composable
                modifier = Modifier

                    // Padding: 8dp
                    .padding(8.dp)

                    // Image size = 40dp
                    .size(40.dp)

            )

            // Add a horizontal space between the image and the column
            Spacer(modifier = Modifier.width(8.dp))

            // Column is a vertical arrangement of composable
            Column {

                // Author?
                Text(text = msg.author)

                // Message?
                Text(text = msg.body)

            }
        }
    }

    /**
     * The @Preview annotation allows you to preview the UI of your composable functions within
     * the Android Studio without having to build and install the app on a device.
     *
     * The annotation must be used on a composable function that does not take in parameters
     */
    @Preview
    @Composable
    fun PreviewMessageCard() {
        ComposeTutorialTheme {
            Surface {
                MessageCard(
                    msg = Message("Colleauge", "Hey take a look at Jetpack Compose")
                )
            }
        }
    }

}
