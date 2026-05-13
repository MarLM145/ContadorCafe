package com.cursoipn.contadordecaf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cursoipn.contadordecaf.ui.theme.ContadorDeCaféTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContadorDeCaféTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CafeApp()
                }
            }
        }
    }
}

@Composable
fun CafeApp() {
    var contador by remember { mutableStateOf(0) }

    PantallaContador(
        cantidad = contador,
        onIncrementar = { contador++ },
        onReiniciar = { contador = 0 }
    )
}

@Composable
fun PantallaContador(
    cantidad: Int,
    onIncrementar: () -> Unit,
    onReiniciar: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Capa de fondo
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "Fondo de café",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.3f),
            contentScale = ContentScale.Crop
        )

        // Capa de contenido
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Tazas de café: $cantidad",
                fontSize = 35.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            // Mensaje de advertencia
            if (cantidad > 10) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "¡Demasiada cafeína!",
                    color = Color.Red,
                    fontSize = 22.sp
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Row {
                Button(onClick = onIncrementar) {
                    Text("Añadir taza (+1)")
                }

                Spacer(modifier = Modifier.width(16.dp))

                OutlinedButton(onClick = onReiniciar) {
                    Text("Reiniciar")
                }
            }
        }
    }
}