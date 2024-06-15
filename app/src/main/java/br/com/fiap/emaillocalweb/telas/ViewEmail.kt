package br.com.fiap.emaillocalweb.telas

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.fiap.emaillocalweb.EmailDb
import br.com.fiap.emaillocalweb.EmailModel
import br.com.fiap.emaillocalweb.components.NavBar


@Composable
fun ViewEmail(navController: NavHostController, emailId: String, seuEmail: String) {
    val context = LocalContext.current
    val db = EmailDb.getDatabase(context)
    val emailDao = db.emailDao()

    var email by remember { mutableStateOf<EmailModel?>(null) }

    val textStyle = TextStyle(
        fontSize = 16.sp,
        color = Color.Black,
        // Adicione outras propriedades de estilo conforme necessário
    )

    LaunchedEffect(emailId) {
        email = emailDao.getEmailById(emailId)
    }

    email?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp, start = 15.dp, end = 15.dp)

        ) {
            IconButton(
                modifier = Modifier.background(Color.Green),
                onClick = { navController.navigate("enviados")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Excluir",
                    tint = Color.Gray,
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Column {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Nome: ")
                        }
                        append("$seuEmail")
                    },
                    style = textStyle
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Email: ")
                        }
                        append(it.email)
                    },
                    style = textStyle
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Assunto: ")
                        }
                        append(it.assunto)
                    },
                    style = textStyle
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Mensagem: ")
                        }
                        append(it.message)
                    },
                    style = textStyle
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    val interactionSourceEntrar = remember { MutableInteractionSource() }
                    val isPressedEntrar = interactionSourceEntrar.collectIsPressedAsState().value

                    Button(
                        modifier = Modifier.padding(end = 10.dp),
                        onClick = { navController.navigate("enviados") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isPressedEntrar) Color(0xFF253645) else Color(0xFFD20B3D),
                            contentColor = Color.White
                        ),
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(text = "Responder")
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }

                    Button(
                        onClick = { navController.navigate("enviados") },
                                colors = ButtonDefaults.buttonColors(
                                containerColor = if (isPressedEntrar) Color(0xFF253645) else Color(0xFFD20B3D),
                        contentColor = Color.White
                    ),
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(text = "Responder")
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }
                }
            }
            NavBar(navController)
        }
    }
}


