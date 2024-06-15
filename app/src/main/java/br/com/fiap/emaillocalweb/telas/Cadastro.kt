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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Cadastro(navController: NavController){
    val nomeCadastroState = remember { mutableStateOf("") }
    val emailCadastroState = remember { mutableStateOf("") }
    val senhaCadastroState = remember { mutableStateOf("") }
    val senhaCadastroState2 = remember { mutableStateOf("") }
    val telefoneCadastroState = remember { mutableStateOf("") }

    val interactionSourceCadastrar = remember { MutableInteractionSource() }
    val isPressedCadastrar = interactionSourceCadastrar.collectIsPressedAsState().value

    Column(
        modifier = Modifier
            .background(Color(0xFFFEFEFE)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Card (
            modifier = Modifier
                .size(width = 120.dp, height = 40.dp)
                .align(Alignment.End),

            shape = RoundedCornerShape(
                bottomStart = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFD20B3D)
            )

        ) {
            // Empty Card
        }

        Column(
            modifier = Modifier.padding(start = 32.dp),
        ) {
            Row {

                Text(
                    text = "Cadastro",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF253645)
                )
                Image(
                    painter = painterResource(id = R.drawable.logolocaw), contentDescription = "imagem logo",
                    modifier = Modifier.size(60.dp)

                )
            }
            Text(text = "Insira seus dados para cadastro")
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 32.dp, start = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                value = nomeCadastroState.value,
                onValueChange = { nomeCadastroState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                label = {
                    Text(text = "Nome")
                },
                colors = TextFieldDefaults.colors(focusedTextColor = Color.Black, unfocusedTextColor = Color.Gray),
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = "")
                },
                trailingIcon = {
                    Icon(imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription = "")
                },
            )

            OutlinedTextField(
                value = telefoneCadastroState.value,
                onValueChange = { telefoneCadastroState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                label = {
                    Text(text = "Telefone")
                },
                colors = TextFieldDefaults.colors(focusedTextColor = Color.Black, unfocusedTextColor = Color.Gray),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Phone, contentDescription = "")
                },
                trailingIcon = {
                    Icon(imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription = "")
                },
            )

            OutlinedTextField(
                value = emailCadastroState.value,
                onValueChange = { emailCadastroState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                label = {
                    Text(text = "Email")
                },
                colors = TextFieldDefaults.colors(focusedTextColor = Color.Black, unfocusedTextColor = Color.Gray),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                trailingIcon = {
                    Icon(imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription = "")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Email, contentDescription = "")
                }
            )

            OutlinedTextField(
                value = senhaCadastroState.value,
                onValueChange = { senhaCadastroState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                label = {
                    Text(text = "Senha")
                },
                colors = TextFieldDefaults.colors(focusedTextColor = Color.Black, unfocusedTextColor = Color.Gray),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    Icon(imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription = "")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Lock, contentDescription = "")
                }
            )

            OutlinedTextField(
                value = senhaCadastroState2.value,
                onValueChange = { senhaCadastroState2.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                label = {
                    Text(text = "Confirme sua Senha")
                },
                colors = TextFieldDefaults.colors(focusedTextColor = Color.Black, unfocusedTextColor = Color.Gray),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    Icon(imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription = "")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Lock, contentDescription = "")
                }
            )

            Spacer(modifier = Modifier.height(1.dp))

            val checar = remember { mutableStateOf(false) }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checar.value,
                    onCheckedChange = { isSelected -> checar.value = isSelected },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF253645),
                        uncheckedColor = Color(0xFFD20B3D)
                    )
                )
                Text(text = "Li e concordo com os termos de uso")
            }

            Button(
                onClick = { navController.navigate("caixaEntrada") },
                interactionSource = interactionSourceCadastrar,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isPressedCadastrar) Color(0xFFD20B3D) else Color(0xFF253645),
                    contentColor = Color.White
                ),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Cadastrar")
                Icon(imageVector = Icons.Outlined.ArrowForward, contentDescription = "")
            }

            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 6.dp)
            ) {
                Text(
                    text = "Já tem uma conta?",
                    modifier = Modifier.padding(end = 6.dp)
                )
                Text(
                    text = "Faça Login",
                    color = Color(0xFFD20B3D),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate("login")
                    }
                )
            }
        }

        Card(
            modifier = Modifier
                .size(width = 120.dp, height = 40.dp)
                .align(Alignment.Start),
            shape = RoundedCornerShape(topEnd = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFD20B3D)
            )
        ) {
            // Empty Card
        }
    }
}
