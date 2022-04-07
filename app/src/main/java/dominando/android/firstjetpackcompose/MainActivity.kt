package dominando.android.firstjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Error
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dominando.android.firstjetpackcompose.ui.theme.FirstJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstJetpackComposeTheme {
                ValidationsComposable()
            }
        }
    }
}

@Composable
fun ValidationsComposable() {
    var name by remember { mutableStateOf("") }
    val nameTextUpdate = { data: String ->
        name = data
    }

    var nameTextError by remember { mutableStateOf(false)} // Error flag created

    ValidationsUI(name, nameTextUpdate, nameTextError) {
        nameTextError = name.isEmpty()
    }
}

@Composable
fun ValidationsUI(
    name: String,
    nameUpdate: (String) -> Unit,
    nameError: Boolean, // Error flag input
    validate: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        OutlineTextFieldWithErrorView(
            value = name,
            onValueChange = nameUpdate,
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text("Name") },
            trailingIcon = { Icon(Icons.Outlined.Error, stringResource(R.string.error_description)) },
            isError = nameError,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            singleLine = true,
            errorMsg = "Enter Valid Name"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                validate()
            },
            shape = RoundedCornerShape(size = 22.5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
        ) {
            Text(
                text = stringResource(R.string.btn_submit),
                fontSize = 16.sp
            )
        }
    }
}


@Composable
fun OutlineTextFieldWithErrorView(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable() (() -> Unit)? = null,
    placeholder: @Composable() (() -> Unit)? = null,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    shape: Shape = MaterialTheme.shapes.small,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
    errorMsg: String = ""
) {

    Column(
        modifier = Modifier.padding(
            bottom = if (isError) {
                0.dp
            } else {
                10.dp
            }
        )
    ) {
        OutlinedTextField(
            enabled = enabled,
            readOnly = readOnly,
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            singleLine = singleLine,
            textStyle = textStyle,
            placeholder = placeholder,
            label = label,
            leadingIcon = leadingIcon,
            trailingIcon = if (isError) trailingIcon else null,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            maxLines = maxLines,
            shape = shape,
            colors = colors
        )

        if (isError) {
            Text(
                text = errorMsg,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp, top = 0.dp)
            )
        }
    }
}