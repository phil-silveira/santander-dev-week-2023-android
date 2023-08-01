package dio.me.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dagger.hilt.android.AndroidEntryPoint
import dio.me.data.model.Response
import dio.me.presentation.components.BalanceCard
import dio.me.presentation.components.CreditCard
import dio.me.presentation.components.Header
import dio.me.presentation.components.MenuItems
import dio.me.presentation.components.NewsPagerApp
import dio.me.presentation.components.TopAppBar
import dio.me.presentation.theme.SantanderDevWeekTheme
import dio.me.presentation.theme.Spacing_2
import dio.me.presentation.theme.Spacing_3

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state by viewModel.state.collectAsState()

            SantanderDevWeekTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainApp(
                        response = state.response
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(response: Response? = null) =
    Surface(color = MaterialTheme.colorScheme.background) {
        Scaffold(
            topBar = { TopAppBar() },
            content = { innerPadding ->
                ConstraintLayout(
                    modifier = Modifier
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState())
                ) {
                    val (header, spacer, balanceCard, menu, creditCard, news) = createRefs()

                    Header(
                        modifier = Modifier.constrainAs(header) {
                            centerHorizontallyTo(parent)

                        },
                        name = response?.name ?: "",
                        agency = response?.account?.agency ?: "",
                        number = response?.account?.number ?: "",
                    )
                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                            .constrainAs(spacer) {
                                bottom.linkTo(header.bottom)
                            }
                    )
                    BalanceCard(
                        modifier = Modifier.constrainAs(balanceCard) {
                            centerHorizontallyTo(parent)
                            top.linkTo(spacer.top)
                        },
                        balance = response?.account?.balance ?: 0.0,
                        limit = response?.account?.limit ?: 0.0,
                    )
                    MenuItems(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = Spacing_2,
                                vertical = Spacing_3
                            )
                            .constrainAs(menu) {
                                centerHorizontallyTo(parent)
                                top.linkTo(balanceCard.bottom)
                            },
                        features = response?.features ?: emptyList()
                    )
                    CreditCard(
                        modifier = Modifier
                            .padding(
                                horizontal = Spacing_2,
                            )
                            .constrainAs(creditCard) {
                                centerHorizontallyTo(parent)
                                top.linkTo(menu.bottom)
                            },
                        number = response?.card?.number ?: ""
                    )
                    NewsPagerApp(
                        news = response?.news ?: emptyList(),
                        modifier = Modifier
                            .padding(
                                vertical = Spacing_2
                            )
                            .constrainAs(news) {
                                centerHorizontallyTo(parent)
                                top.linkTo(creditCard.bottom)
                            },
                    )

                }
            }
        )
    }

@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    SantanderDevWeekTheme {
        MainApp()
    }
}
