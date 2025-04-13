package com.example.myapplication.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.supabasesimpleproject.R
import io.github.jan.supabase.realtime.Column
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import com.example.myapplication.models.cartochka

@Composable
fun Cart(cart: cartochka, getUrl:(String)->String, onClick:()->Unit)
{
    var imageUrl by remember { mutableStateOf( "") }
    Card(
        modifier = Modifier
            .padding(8.dp)

            .fillMaxWidth()
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = Color(   0xFFFFFFE0))

    ){
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            val imageState = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current).data(imageUrl)
                    .size(Size.ORIGINAL).build()
            ).state

            LaunchedEffect(cart) {
                imageUrl = getUrl(cart.id)
            }
            if(imageState is AsyncImagePainter.State.Loading){
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                ){
                    CircularProgressIndicator()
                }
            }
            if (imageState is AsyncImagePainter.State.Error){
                Image(
                    painter = painterResource(R.drawable.card),
                    contentDescription = cart.title,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            if (imageState is AsyncImagePainter.State.Success){
                Image(
                    painter = imageState.painter,
                    contentDescription = cart.title,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {

                Text(
                    text = cart.title,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = cart.description,
                    maxLines = 2, // Максимум 2 строки
                    overflow = TextOverflow.Ellipsis // Обрезка текста с многоточием
                )
            }
        }
    }

}