package com.example.crypwatch.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.crypwatch.R
import com.example.crypwatch.models.CoinDetail
import com.example.crypwatch.models.CoinListItem
import com.example.crypwatch.viewmodels.CoinsViewModel
import com.example.crypwatch.viewmodels.DetailViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
fun CoinListScreen(onClick : (coinID: String) -> Unit) {
    val coinsViewModel : CoinsViewModel = hiltViewModel()
    val coins: State<List<CoinListItem>> = coinsViewModel.coins.collectAsState()

    Column {
        Spacer(modifier = Modifier.height(15.dp))
        Text(modifier = Modifier.fillMaxWidth(), text = "Track Coins", style = MaterialTheme.typography.headlineSmall, fontStyle = FontStyle(R.font.robotostatic), textAlign = TextAlign.Center, color = Color.White)
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp), verticalArrangement = Arrangement.spacedBy(6.dp)){
            items(coins.value){
                CoinScreenItem(coinListItem = it, onClick, it.id)
            }
        }
    }

}


@Composable
fun CoinScreenItem(
    coinListItem: CoinListItem,
    onClick : (coinID: String) -> Unit,
    ID : String
) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .background(Color(0x51, 0x51, 0x51))
        .padding(25.dp)
        .clickable { onClick(coinListItem.id) }, horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {

        var logoLink : String = "https://static.coinpaprika.com/coin/$ID/logo.png"

        AsyncImage(modifier = Modifier.size(width = 30.dp, height = 30.dp), model = logoLink, contentDescription = "TAG",)


        Text(text = "${coinListItem.rank}. ${coinListItem.name} (${coinListItem.symbol})",
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
        )


        
        Text(text = if (coinListItem.is_active) "active" else "inactive",
            color = if (coinListItem.is_active) Color.Green else Color.Red,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(CenterVertically)
        )
    }
    
}