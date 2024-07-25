package com.example.practicaltestkonfio.dogList.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.practicaltestkonfio.dogList.domain.model.Dog
import com.example.practicaltestkonfio.dogList.util.getAverageColor

@Composable
fun DogItem(
    dog: Dog
) {
    val imagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(dog.image)
            .size(Size.ORIGINAL).build()
    )

    val defaultColor = MaterialTheme.colorScheme.secondaryContainer
    var dominantColor by remember {
        mutableStateOf(defaultColor)
    }

    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(220.dp) // Altura de la imagen
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.BottomEnd)
        ) {
            // Imagen del perro
            Box(
                modifier = Modifier
                    .height(220.dp) // Altura de la imagen
                    .width(150.dp) // Ancho de la imagen
                    .clip(RoundedCornerShape(22.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                if (imagePainter.state is AsyncImagePainter.State.Error) {
                    Icon(
                        imageVector = Icons.Rounded.ImageNotSupported,
                        contentDescription = dog.dogName
                    )
                }

                if (imagePainter.state is AsyncImagePainter.State.Success) {
                    dominantColor = getAverageColor(
                        imageBitmap = (imagePainter.state as AsyncImagePainter.State.Success).result.drawable.toBitmap().asImageBitmap()
                    )

                    Image(
                        painter = imagePainter,
                        contentDescription = dog.dogName,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            // Tarjeta de texto
            Box(
                modifier = Modifier
                    .height(200.dp) // Altura de la tarjeta de texto
                    .align(Alignment.Bottom) // Alinear la tarjeta al fondo
                    .weight(1f)
                    .clip(RoundedCornerShape(28.dp))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.secondaryContainer,
                                dominantColor
                            )
                        )
                    )
                    .background(Color(0xFFF8F8F8)) // Establecer el color de fondo a #F8F8F8

                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(text = dog.dogName, style = MaterialTheme.typography.headlineLarge)
                    Text(
                        text = dog.description,
                        style = MaterialTheme.typography.bodyMedium,

                    ) // Limitar el texto
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Almost ${dog.age}", style = MaterialTheme.typography.headlineSmall)
                }
            }
        }
    }
}
