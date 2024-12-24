
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import android.content.Intent
import android.net.Uri
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity

@Composable
fun OrderScreen( order: String?) {
    var orderText by remember{ mutableStateOf("") }


    val context = LocalContext.current
    val uri = Uri.Builder().scheme("http").authority("telegram.me").appendEncodedPath("+79852798847").build()
    val intent = Intent(Intent.ACTION_VIEW, uri)
    intent.setPackage("org.telegram.messenger")
    Column {
        orderText = "Your Order $order is Confirmed!"
        Text(text = orderText)
        Button(onClick = {
            startActivity(context, intent, null) }
        ) {
            Text("ЗАКАЗАТЬ")
        }
    }
}