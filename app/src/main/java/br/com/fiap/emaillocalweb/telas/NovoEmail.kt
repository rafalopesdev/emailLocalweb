package br.com.fiap.emaillocalweb.telas

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import br.com.fiap.emaillocalweb.EmailDb
import br.com.fiap.emaillocalweb.EmailModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun NovoEmail(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)

        ) {
            Text(
                text = "Novo Email",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp
            )
            Column(

            ) {
                Column {
                    FormNovo(navController)
                }
                NavBar(navController)
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
        Column {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = nome,
                onValueChange = { nome = it },
                label = { Text(text = "De: $seuEmail") }

            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Digite o email...") },
                label = { Text(text = "Para:") }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = assunto,
                onValueChange = { assunto = it },
                placeholder = { Text("Título da mensagem...") },
                label = { Text(text = "Assunto:") }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
                maxLines = Int.MAX_VALUE,
                textStyle = TextStyle(fontSize = 16.sp),
                shape = RoundedCornerShape(8.dp),
                placeholder = { Text("Digite sua mensagem...") },
                value = message,
                onValueChange = { message = it },
                label = { Text(text = "Mensagem:") }
            )
            val interactionSourceEntrar = remember { MutableInteractionSource() }
            val isPressedEntrar = interactionSourceEntrar.collectIsPressedAsState().value
            Button(
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
                    .padding(top = 32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isPressedEntrar) Color(0xFF253645) else Color(0xFFD20B3D),
                    contentColor = Color.White
                ),
            ) {
                Text(text = "Enviar")
            }
            Spacer(modifier = Modifier.height(10.dp))
            if (successMessageVisible) {
                Text(
                    text = "Email enviado com sucesso",
                    color = Color.Green,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            NavBar(navController)
        }
    }
}

