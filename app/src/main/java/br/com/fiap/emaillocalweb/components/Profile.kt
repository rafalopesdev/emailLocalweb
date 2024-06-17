package br.com.fiap.emaillocalweb.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun Profile(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Card (
                colors = CardDefaults.cardColors(containerColor = Color(0xFF253645)),
                elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
                shape = RoundedCornerShape(bottomStart = 4.dp, bottomEnd = 8.dp)
            ){

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Icon(
                            imageVector = Icons.Rounded.AccountCircle,
                            contentDescription = "Entrada",
                            modifier = Modifier.size(45.dp),
                            tint = Color(0xFFD20B3D)
                        )
                    }
                    Column {
                        var nomeUser = "Rafael"
                        Text(
                            text = "$nomeUser, Seja bem-vindo!",
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    IconButton(
                        modifier = Modifier
                            .padding(0.dp),
                        onClick = { navController.navigate("login") },

                        ) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "login",
                            tint = Color(0xFFA8A8A8),

                            )
                    }
                }
            }
        }
    }
}
