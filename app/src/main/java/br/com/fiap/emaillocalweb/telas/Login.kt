package br.com.fiap.emaillocalweb.telas

import br.com.fiap.emaillocalweb.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun Login(navController: NavController) {
    val emailState = remember { mutableStateOf("") }
    val senhaState = remember { mutableStateOf("") }

    val interactionSourceEntrar = remember { MutableInteractionSource() }
    val isPressedEntrar = interactionSourceEntrar.collectIsPressedAsState().value

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
    ){
        Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "imagem logo")
    }

    Column(
        modifier = Modifier
            .background(Color(0xFFFEFEFE))
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Card(
            modifier = Modifier
                .size(width = 120.dp, height = 40.dp)
                .align(Alignment.End),
            shape = RoundedCornerShape(bottomStart = 16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFD20B3D))
        ) {}

        Column(modifier = Modifier.padding(start = 32.dp)) {
            Row (verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = "Login",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF253645)
                )
                Image(
                    painter = painterResource(id = R.drawable.logolocaw), contentDescription = "imagem logo",
                    modifier = Modifier
                        .size(60.dp)
                        .padding(5.dp)
                )
            }
            Text(text = "Faça login para acessar sua conta")
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 32.dp, start = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = emailState.value,
                onValueChange = { emailState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                label = { Text(text = "E-mail") },
                colors = TextFieldDefaults.colors(focusedTextColor = Color.Black, unfocusedTextColor = Color.Gray),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                leadingIcon = { Icon(imageVector = Icons.Outlined.Email, contentDescription = "") }
            )
            OutlinedTextField(
                value = senhaState.value,
                onValueChange = { senhaState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                label = { Text(text = "Senha") },
                colors = TextFieldDefaults.colors(focusedTextColor = Color.Black, unfocusedTextColor = Color.Gray),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = { Icon(imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription = "") },
                leadingIcon = { Icon(imageVector = Icons.Filled.Lock, contentDescription = "") }
            )
            Button(
                onClick = { navController.navigate("caixaEntrada") },
                interactionSource = interactionSourceEntrar,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isPressedEntrar) Color(0xFFD20B3D) else Color(0xFF253645),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Text(text = "Entrar")
                Icon(imageVector = Icons.Outlined.ArrowForward, contentDescription = "")
            }
            Row(
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = "Não tem uma conta?",
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    text = "Cadastre-se",
                    color = Color(0xFFD20B3D),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.None,
                    modifier = Modifier
                        .clickable {
                            navController.navigate("cadastro")
                        }
                )
            }
        }
        Card(
            modifier = Modifier
                .size(width = 120.dp, height = 40.dp)
                .align(Alignment.Start),
            shape = RoundedCornerShape(topEnd = 16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFD20B3D))
        ) {}
    }
}
