package dio.me.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dio.me.extensions.formatCurrency
import dio.me.presentation.theme.SantanderDevWeekTheme
import dio.me.presentation.theme.Spacing_2
import dio.me.presentation.theme.Spacing_3
import dio.me.presentation.theme.Spacing_4

@Composable
fun BalanceCard(
    modifier: Modifier = Modifier,
    balance: Double = 0.0,
    limit: Double = 0.0
) {
    AppCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = Spacing_2
            )
    ) {
        Column(
            modifier = Modifier.padding(Spacing_2)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Filled.CurrencyExchange,
                        contentDescription = "Currency icon"
                    )
                    Text(
                        text = "Saldo disponível",
                        modifier = Modifier.padding(start = Spacing_3)
                    )
                }
                Icon(
                    imageVector = Icons.Filled.ExpandLess,
                    contentDescription = "Arrow-up icon"
                )
            }
            Text(
                text = balance.formatCurrency(),
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.Black
                )
            )
            Text(
                text = "Saldo + Limite: ${(balance + limit).formatCurrency()}",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600,
                    color = Color.Black
                )
            )
            Divider(
                modifier = Modifier.padding(top = Spacing_4)
            )
            TextButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "Ver Extrato"
                )
            }

        }
    }
}

@Preview
@Composable
fun BalanceCardPreview() {
    SantanderDevWeekTheme {
        BalanceCard(
            balance = 1023.0,
            limit = 500.0
        )
    }
}