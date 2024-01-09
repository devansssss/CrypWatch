package com.example.crypwatch.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.size.Size
import com.example.crypwatch.R
import com.example.crypwatch.models.GlobalData
import com.example.crypwatch.viewmodels.GlobalViewModel

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        GlobalDetail()
    }
}


@Composable
fun GlobalDetail() {
    val globalViewModel : GlobalViewModel = hiltViewModel()
    val globaldata : State<GlobalData> = globalViewModel.Global.collectAsState()
    LazyVerticalGrid(columns = GridCells.Fixed(2), verticalArrangement = Arrangement.SpaceAround, contentPadding = PaddingValues(8.dp)){
        item {
            GlobalCardItem(value = globaldata.value.market_cap_usd.toString(), "Market Cap")
        }
        item {
            GlobalCardItem(value = globaldata.value.volume_24h_usd.toString(), "Volume 24h USD")
        }
        item {
            GlobalCardItem(value = globaldata.value.last_updated.toString(), "Last Updated")
        }
        item {
            GlobalCardItem(value = globaldata.value.bitcoin_dominance_percentage.toString(), "Bitcoin Dominance %")
        }
    }
}


@Composable
fun GlobalCardItem(value: String, type: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(160.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                2.dp, Color.Black
            )
            .paint(
                painter = painterResource(id = R.drawable.layered_steps_haikei__1_),
                contentScale = ContentScale.Crop
            )
    ){
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(text = "${type}" ,color = Color.White, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "${value}", color = Color.White, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

