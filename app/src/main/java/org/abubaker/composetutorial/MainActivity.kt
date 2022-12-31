package org.abubaker.composetutorial

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

            // ComposeTutorialTheme is a wrapper around the Material Design theme.
            // It is defined in the ui/theme.kt package.
            ComposeTutorialTheme {

                // Surface is a container that allows us to apply a background color
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
                    // .padding(8.dp)

                    // Image size = 40dp
                    .size(40.dp)

                    .clip(CircleShape)

                    // Add a border around the image
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)

            )

            // Add a horizontal space between the image and the column
            Spacer(modifier = Modifier.width(8.dp))

            // Column is a vertical arrangement of composable
            Column {

                // Author?
                Text(
                    text = msg.author,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Message?
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp
                ) {
                    Text(
                        text = msg.body,
                        modifier = Modifier.padding(all = 4.dp),
                        style = MaterialTheme.typography.body2
                    )
                }

            }
        }
    }

    /**
     * The @Preview annotation allows you to preview the UI of your composable functions within
     * the Android Studio without having to build and install the app on a device.
     *
     * The annotation must be used on a composable function that does not take in parameters
     */
    @Preview(name = "Light Mode")
    @Preview(
        uiMode = UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
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
