import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

enum class CoffeeType(val price: Double) {
    AMERICANO(3.0),
    CAPPUCCINO(4.0),
    ESPRESSO(2.5)
}

@Composable
fun DetailsScreen(navController: NavController) {
    var quantity by remember { mutableStateOf(1) }
    var selectedCoffeeType by remember { mutableStateOf(CoffeeType.AMERICANO) }
    var name by remember { mutableStateOf("") }
    var milk by remember { mutableStateOf(false) }
    var syrup by remember { mutableStateOf(false) }
    var sugar by remember { mutableStateOf(false) }


    val totalPrice:Double = quantity * selectedCoffeeType.price +
            (if (milk) 0.5 else 0.0) +
            (if (syrup) 0.7 else 0.0) +
            (if (sugar) 0.3 else 0.0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Заказ кофе", style = MaterialTheme.typography.headlineMedium)

        TextField(value = name, onValueChange = { name = it }, label = { Text("Ваше имя") })

        // Выбор количества с помощью Slider
        Text("Количество чашек: $quantity")
        Slider(
            value = quantity.toFloat(),
            onValueChange = { quantity = it.toInt() },
            valueRange = 1f..10f,
            steps = 9
        )

        // Dropdown меню для выбора вида кофе
        Text("Тип кофе:")
        DropdownMenuExample(selectedCoffeeType) { type ->
            selectedCoffeeType = type
        }

        // Чекбоксы для дополнительных опций
        Text("Дополнительные опции:")
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = milk, onCheckedChange = { milk = it })
            Text("Молоко (+0.5 $)")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = syrup, onCheckedChange = { syrup = it })
            Text("Сироп (+0.7 $)")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = sugar, onCheckedChange = { sugar = it })
            Text("Сахар (+0.3 $)")
        }

        // Итоговая сумма заказа
        Text(
            text = "Итого: ${"%.2f".format(totalPrice)} $",
            style = MaterialTheme.typography.headlineLarge
        )

        val order =
            "Имя: $name, \n Вид кофе: $selectedCoffeeType, \n Количество: $quantity, " +
                    (if (syrup) "Дополнительно - Сироп" else "") + '\n'
        (if (sugar) "Дополнительно - Сахар" else "") + '\n'
        (if (milk) "Дополнительно - Молоко" else "") + '\n'+ "%.2f".format(totalPrice)

        Button(onClick = {
            navController.navigate("order_details/${order}")
        }) {
            Text("Заказать")
        }
    }
}

@Composable
fun DropdownMenuExample(
    selectedCoffeeType: CoffeeType,
    onCoffeeTypeSelected: (CoffeeType) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {

        Text(selectedCoffeeType.name, modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = true })

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            CoffeeType.entries.forEach { type ->
                DropdownMenuItem(text = { Text(text = type.name) }, onClick = {
                    onCoffeeTypeSelected(type)
                    expanded = false
                })
            }
        }
    }
}