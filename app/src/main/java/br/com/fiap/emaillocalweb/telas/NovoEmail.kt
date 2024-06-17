package br.com.fiap.emaillocalweb.telas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.emaillocalweb.components.NavBar
import androidx.navigation.NavController
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import br.com.fiap.emaillocalweb.EmailDb
import br.com.fiap.emaillocalweb.EmailModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import br.com.fiap.emaillocalweb.R
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.draw.clip

@Composable
fun NovoEmail(navController: NavController) {
    val interactionSourceEntrar = remember { MutableInteractionSource() }
    val isPressedEntrar = interactionSourceEntrar.collectIsPressedAsState().value



    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                //.padding(top = 8.dp, start = 8.dp, end = 8.dp)

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF253645))  // Define the background color here
            ) {

            Button(
                onClick = { navController.navigate("caixaEntrada") },
                interactionSource = interactionSourceEntrar,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isPressedEntrar) Color(0xFFD20B3D) else Color.Transparent,
                    contentColor = Color(0xFFD20B3D)
                ),
            ) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
                    contentDescription = "Arrow left",
                    modifier = Modifier.size(50.dp)
                )
            }
            Text(
                text = "Novo Email",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp
            )}
            Column(

            ) {
                Column {
                    FormNovo(navController)
                }
            }

        }
    }
}

@Composable
fun FormNovo(navController: NavController) {
    val seuEmail = "rafalopes@email.com"

    val context = LocalContext.current
    val db = EmailDb.getDatabase(context)
    val emailDao = db.emailDao()

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var assunto by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var successMessageVisible by remember { mutableStateOf(false) }

    var emails by remember { mutableStateOf<List<EmailModel>>(emptyList()) }

    // Load contacts on initial composition
    LaunchedEffect(Unit) {
        emails = emailDao.listarEmails()
    }

    if (successMessageVisible) {
        LaunchedEffect(Unit) {
            kotlinx.coroutines.delay(1500) // delay for 3 seconds
            successMessageVisible = false
        }
    }
    Column {
        Column(
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = nome,
                onValueChange = { nome = it },
                label = { Text(text = "De: $seuEmail") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFD20B3D),
                unfocusedBorderColor = Color(0xFF253645),
                cursorColor = Color(0xFF253645)),

            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Digite o email...") },
                label = { Text(text = "Para:") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFD20B3D),
                    unfocusedBorderColor = Color(0xFF253645),
                    cursorColor = Color(0xFF253645)),
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = assunto,
                onValueChange = { assunto = it },
                placeholder = { Text("Título da mensagem...") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text(text = "Assunto:") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFD20B3D),
                    unfocusedBorderColor = Color(0xFF253645),
                    cursorColor = Color(0xFF253645)),
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 250.dp),
                maxLines = Int.MAX_VALUE,
                textStyle = TextStyle(fontSize = 16.sp),
                shape = RoundedCornerShape(8.dp),
                placeholder = { Text("Digite sua mensagem...") },
                value = message,
                onValueChange = { message = it },
                label = { Text(text = "Mensagem:") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFD20B3D),
                    unfocusedBorderColor = Color(0xFF253645),
                    cursorColor = Color(0xFF253645)),
            )
            val interactionSourceEntrar = remember { MutableInteractionSource() }
            val isPressedEntrar = interactionSourceEntrar.collectIsPressedAsState().value
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isPressedEntrar) Color(0xFFD20B3D) else Color(0xFF253645),
                    contentColor = Color.White
                ),
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        emailDao.salvar(
                            EmailModel(
                                nome = nome,
                                email = email,
                                assunto = assunto,
                                message = message
                            )
                        )
                        emails = emailDao.listarEmails()
                        withContext(Dispatchers.Main) {
                            nome = ""
                            email = ""
                            assunto = ""
                            message = ""
                            successMessageVisible = true
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
                    .size(width = 100.dp, height = 40.dp),

            ) {
                Icon(
                    imageVector = Icons.Rounded.Send,
                    contentDescription = "Enviado",
                    modifier = Modifier
                        .size(20.dp)
                        .padding(20.dp),
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Enviar")
            }
            Spacer(modifier = Modifier.height(10.dp))
            if (successMessageVisible) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF008B8B)
                    ),
                    modifier = Modifier
                        .padding(30.dp)
                        .size(width = 260.dp, height = 50.dp)
                        .align(Alignment.CenterHorizontally),
                ) {
                    Text(
                        text = "Email enviado com sucesso",
                        color = Color.White,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally),
                    )
                }

            }
        }
    }
    NavBar(navController)
}

