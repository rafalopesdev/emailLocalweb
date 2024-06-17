package br.com.fiap.emaillocalweb.telas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.emaillocalweb.R

@Composable
fun SplashScreen(navController: NavController) {
    val interactionSourceLogin = remember { MutableInteractionSource() }
    val isPressedLogin by interactionSourceLogin.collectIsPressedAsState()
    val interactionSourceCadastro = remember { MutableInteractionSource() }
    val isPressedCadastro by interactionSourceCadastro.collectIsPressedAsState()
    Column(
        modifier = Modifier
            .background(Color(0xFFFEFEFE))
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Card(
            modifier = Modifier
                .size(width = 120.dp, height = 40.dp)
                .align(Alignment.End),
            shape = RoundedCornerShape(
                bottomStart = 16.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFD20B3D)
            )

        )
        {
            // Empty Card
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        )
        {
            Image(
                painter = painterResource(id = R.drawable.locawebescrito),
                contentDescription = "imagem logo",
                modifier = Modifier
                    .size(250.dp)
                //.padding(top = 10.dp
                //.background(Color.Red),

            )
            Text(
                text = "Sua caixa de email inteligente",
                modifier = Modifier
                    //.padding(bottom = 70.dp)
                    .offset(y = (-90).dp)

            )

            Button(
                onClick = { navController.navigate("login") },
                interactionSource = interactionSourceLogin,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isPressedLogin) Color(0xFFD20B3D) else Color(0xFF253645),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .size(width = 250.dp, height = 55.dp)
                    //.padding(bottom = 30.dp)
                    .offset(y = (-60).dp),
            ) {
                Text(text = "Login")
                Icon(imageVector = Icons.Outlined.ArrowForward, contentDescription = "")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { navController.navigate("cadastro") },
                interactionSource = interactionSourceCadastro,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isPressedCadastro) Color(0xFFD20B3D) else Color(0xFF253645),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .size(width = 250.dp, height = 55.dp)
                    .offset(y = (-60).dp),
                //.padding(top = 30.dp)
            ) {
                Text(text = "Cadastrar")
                Icon(imageVector = Icons.Outlined.ArrowForward, contentDescription = "")
            }
            Card(
                modifier = Modifier
                    .size(width = 120.dp, height = 40.dp)
                    .align(Alignment.Start)
                    .offset(y = 144.dp),
                shape = RoundedCornerShape(topEnd = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFD20B3D))
            ) { }

        }

    }

}
