package com.example.simulacro2025.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simulacro2025.R
import com.example.simulacro2025.model.Coffee
import com.example.simulacro2025.ui.theme.White

@Composable
fun HomeScreen() {

    val categories = listOf("All Coffee", "Machiato", "Latte", "Americano")

    val products = listOf(
        Coffee("Caffe Mocha", "Deep Foam", 4.53, R.drawable.caffe),
        Coffee("Flat White", "Espresso", 3.53, R.drawable.flatwhite),
        Coffee("Caffe Mocha", "Deep Foam", 4.53, R.drawable.caffe),
        Coffee("Flat White", "Espresso", 3.53, R.drawable.flatwhite),
        Coffee("Caffe Mocha", "Deep Foam", 4.53, R.drawable.caffe),
        Coffee("Flat White", "Espresso", 3.53, R.drawable.flatwhite)
    )

    Scaffold(
        bottomBar = { BottomNavigationBar() },
        containerColor = White
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            TopBarLocationSection()
            Spacer(modifier = Modifier.height(16.dp))
            SearchSection()
            Spacer(modifier = Modifier.height(16.dp))
            Banner()
            Spacer(modifier = Modifier.height(16.dp))
            FilterCategoryTabs(categories)
            Spacer(modifier = Modifier.height(16.dp))
            ProductGrid(products)
        }
    }
}

@Composable
fun TopBarLocationSection() {
    Column {
        Text("Location", color = Color.Gray, fontSize = 12.sp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Bilzen, Tanjungbalai", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
        }
    }
}

@Composable
fun SearchSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Search coffee") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.weight(1f),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF2D2D2D),
                unfocusedContainerColor = Color(0xFF2D2D2D),
                disabledContainerColor = Color(0xFF2D2D2D)
            )
        )
        Spacer(modifier = Modifier.width(12.dp))
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFD17A45)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Build, contentDescription = "Filter", tint = Color.White)
        }
    }
}

@Composable
fun Banner() {
    Box(
        modifier = Modifier
            .fillMaxWidth().height(150.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFFB98E63))
            .paint(painterResource(id = R.drawable.banner), contentScale = ContentScale.FillBounds)
    ) {
        Text(
            text = "Buy one get one FREE",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(12.dp)
                .background(Color.Red, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text("Promo", color = Color.White, fontSize = 12.sp)
        }
    }
}

@Composable
fun FilterCategoryTabs(categories: List<String>) {
    var selected by remember { mutableIntStateOf(0) }
    val scrollState = rememberLazyListState(0)

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        state = scrollState,
        flingBehavior = rememberSnapFlingBehavior(
            lazyListState = scrollState
        ),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        itemsIndexed(categories){ index, category ->
            val isSelected = index == selected
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(if (isSelected) Color(0xFFD17A45) else Color(0xFFF1F1F1))
                    .clickable { selected = index }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = category,
                    color = if (isSelected) Color.White else Color.Black
                )
            }
        }
    }
}

@Composable
fun ProductGrid(products: List<Coffee>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.height(500.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        itemsIndexed(products) { index, coffee ->
            CoffeeCard(coffee)
        }
    }
}

@Composable
fun CoffeeCard(coffee: Coffee) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = coffee.image),
            contentDescription = coffee.name,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(8.dp))
        Text(coffee.name, fontWeight = FontWeight.Bold)
        Text(coffee.desc, color = Color.Gray, fontSize = 12.sp)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("$ ${coffee.price}", fontWeight = FontWeight.Bold)
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFD17A45)),
                contentAlignment = Alignment.Center
            ) {
                Text("+", color = Color.White)
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    BottomAppBar(
        containerColor = Color.White,
        contentPadding = PaddingValues(horizontal = 24.dp),
        tonalElevation = 8.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(Icons.Default.Home, contentDescription = "Home", tint = Color(0xFFD17A45))
            Icon(Icons.Default.FavoriteBorder, contentDescription = "Favorites", tint = Color.Gray)
            Icon(Icons.Default.ShoppingCart, contentDescription = "Bag", tint = Color.Gray)
            Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}