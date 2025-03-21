import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity

@Composable
fun OrderScreen(order: String?) {
    var orderText by remember { mutableStateOf("") }


    val context = LocalContext.current
    val uri =
        Uri.Builder().scheme("http").authority("telegram.me").appendEncodedPath("+79852798847")
            .build()
    val intent = Intent(Intent.ACTION_VIEW, uri)
    intent.setPackage("org.telegram.messenger")
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        orderText = "Your Order $order is Confirmed!"
        Text(
            buildAnnotatedString {
                withStyle(ParagraphStyle(lineHeight = 30.sp)) {
                    append(orderText)
                }
            }
        )
        Button(
            modifier = Modifier.padding(30.dp),
            onClick = {
                context.startActivity(intent, null)
            }
        ) {
            Text("ЗАКАЗАТЬ")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Sample() {
    OrderScreen("name\namericano\none cup\nand sugar")
}