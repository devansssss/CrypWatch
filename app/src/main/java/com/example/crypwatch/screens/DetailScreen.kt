package com.example.crypwatch.screens

import android.content.ClipData.Item
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.crypwatch.models.CoinDetail
import com.example.crypwatch.models.CoinListItem
import com.example.crypwatch.models.coinlatestItem
import com.example.crypwatch.viewmodels.CoinLatestDetails
import com.example.crypwatch.viewmodels.DetailViewModel

@Composable
fun CoinDetailScreen() {
    val coinViewModel: DetailViewModel = hiltViewModel()
    val coin: State<CoinDetail?> = coinViewModel.coindetail.collectAsState()

    val coinlatestViewModel : CoinLatestDetails = hiltViewModel()
    val coinlatestdetail : State<List<coinlatestItem>> = coinlatestViewModel.coinlatest.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(20.dp)) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {


                    Text(
                        text = "${coin.value?.rank}. ${coin.value?.name}. ${coin.value?.symbol}",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.weight(8f),
                        color = Color.White
                    )

                    Text(
                        text = if (coin.value!!.is_active) "active" else "inactive",
                        color = if (coin.value!!.is_active) Color.Green else Color.Red,
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .align(CenterVertically)
                            .weight(2f)
                    )

                }

                Spacer(modifier = Modifier.height(15.dp))

                Text(text = coin.value!!.description, style = MaterialTheme.typography.bodyMedium, color = Color.White)

                Spacer(modifier = Modifier.height(15.dp))

                AsyncImage(model = coin.value!!.logo, contentDescription = "Logo")
                Spacer(modifier = Modifier.height(30.dp))

                if (coinlatestdetail.value.isNotEmpty()){
                    latestpricedetails(text = "Open Today", price = "${coinlatestdetail.value.get(0).open}")
                    Spacer(modifier = Modifier.height(15.dp))
                    latestpricedetails(text = "High Today", price = "${coinlatestdetail.value.get(0).high}")
                    Spacer(modifier = Modifier.height(15.dp))
                    latestpricedetails(text = "Low Today", price = "${coinlatestdetail.value.get(0).low}")
                }
            }
        }
    }
}


@Composable
fun latestpricedetails(text : String, price: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0x51, 0x51, 0x51))
            .padding(PaddingValues(vertical = 20.dp, horizontal = 10.dp))
    ){
        Text(text = "${text}.  $price", style = MaterialTheme.typography.bodyMedium, color = Color.White, modifier = Modifier.fillMaxWidth(), fontSize = 16.sp, textAlign = TextAlign.Center)
    }
}