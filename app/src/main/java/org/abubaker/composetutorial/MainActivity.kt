package org.abubaker.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
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

    /**
     * Sample data imported from https://gist.github.com/yrezgui/26a1060d67bf0ec2a73fa12695166436
     */
    object SampleData {
        // Sample conversation data
        val conversationSample = listOf(
            MainActivity.Message(
                "Colleague",
                "Test...Test...Test..."
            ),
            MainActivity.Message(
                "Colleague",
                "List of Android versions:\n" +
                        "Android KitKat (API 19)\n" +
                        "Android Lollipop (API 21)\n" +
                        "Android Marshmallow (API 23)\n" +
                        "Android Nougat (API 24)\n" +
                        "Android Oreo (API 26)\n" +
                        "Android Pie (API 28)\n" +
                        "Android 10 (API 29)\n" +
                        "Android 11 (API 30)\n" +
                        "Android 12 (API 31)\n"
            ),
            Message(
                "Colleague",
                "I think Kotlin is my favorite programming language.\n" +
                        "It's so much fun!"
            ),
            Message(
                "Colleague",
                "Searching for alternatives to XML layouts..."
            ),
            Message(
                "Colleague",
                "Hey, take a look at Jetpack Compose, it's great!\n" +
                        "It's the Android's modern toolkit for building native UI." +
                        "It simplifies and accelerates UI development on Android." +
                        "Less code, powerful tools, and intuitive Kotlin APIs :)"
            ),
            Message(
                "Colleague",
                "It's available from API 21+ :)"
            ),
            Message(
                "Colleague",
                "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
            ),
            Message(
                "Colleague",
                "Android Studio next version's name is Arctic Fox"
            ),
            Message(
                "Colleague",
                "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
            ),
            Message(
                "Colleague",
                "I didn't know you can now run the emulator directly from Android Studio"
            ),
            Message(
                "Colleague",
                "Compose Previews are great to check quickly how a composable layout looks like"
            ),
            Message(
                "Colleague",
                "Previews are also interactive after enabling the experimental setting"
            ),
            Message(
                "Colleague",
                "Have you tried writing build.gradle with KTS?"
            ),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // The setContent block defines the activity's layout where composable functions are called.
        setContent {

            // ComposeTutorialTheme is a wrapper around the Material Design theme.
            // It is defined in the ui/theme.kt package.
            ComposeTutorialTheme {

                // Surface is a container that allows us to apply a background color
                Surface(modifier = Modifier.fillMaxSize()) {
                    // MessageCard(Message("Android", "Jetpack Compose"))
                    Conversation(SampleData.conversationSample)
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

            // We keep track if the message is expanded or not in this variable
            var isExpanded by remember { mutableStateOf(false) }

            // surfaceColor will be updated gradually from one color to another
            val surfaceColor by animateColorAsState(
                if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
            )

            // Column is a vertical arrangement of composable
            // We toggle the isExpanded variable when we click on this Column
            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {

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
                    elevation = 1.dp,

                    // surfaceColor will be updated gradually from one color to another
                    color = surfaceColor,

                    modifier = Modifier

                        // animateContentSize will change the Surface size gradually
                        .animateContentSize()

                        // Padding: 1dp
                        .padding(1.dp)

                ) {
                    Text(
                        text = msg.body,
                        modifier = Modifier.padding(all = 4.dp),

                        // If the message is expanded, we display all its content
                        // otherwise we only display the first line
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
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
    // @Preview(name = "Light Mode")
    // @Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode")
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

    /**
     *
     */
    @Composable
    fun Conversation(messages: List<Message>) {
        LazyColumn {
            items(messages) { message ->
                MessageCard(message)
            }
        }
    }

    @Preview
    @Composable
    fun PreviewConversation() {
        ComposeTutorialTheme {
            Conversation(MainActivity.SampleData.conversationSample)
        }
    }

}
