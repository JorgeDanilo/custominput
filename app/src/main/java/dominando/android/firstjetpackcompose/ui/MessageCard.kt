package dominando.android.firstjetpackcompose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dominando.android.firstjetpackcompose.ui.theme.FirstJetpackComposeTheme

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {

    Row(modifier = Modifier.padding(all = 8.dp)) {
       /* Image(
            painter = painterResource(id = R.drawable.icon_android),
            contentDescription = null,
            modifier = Modifier
                .size(35.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        ) How to get drawable here ? */

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.width(4.dp))

            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewMessageCard() {
    FirstJetpackComposeTheme {
        MessageCard(msg = Message("Jorge Danilo", "Hey, take a look jetpack compose"))
    }
}