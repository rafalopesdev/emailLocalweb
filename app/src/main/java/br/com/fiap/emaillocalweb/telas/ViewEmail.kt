package br.com.fiap.emaillocalweb.telas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
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
        color = Color.Black
    )

    LaunchedEffect(emailId) {
        email = emailDao.getEmailById(emailId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            //.padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF253645))
        ) {
            val interactionSourceEntrar = remember { MutableInteractionSource() }
            val isPressedEntrar by interactionSourceEntrar.collectIsPressedAsState()

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
            email?.let {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Assunto: ")
                        }
                        append(it.assunto)
                    },
                    style = textStyle.copy(
                        color = Color.White,
                        fontSize = 22.sp
                    ),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        email?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp, vertical = 8.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.AccountCircle,
                        contentDescription = "Entrada",
                        modifier = Modifier.size(45.dp),
                        tint = Color(0xFFD20B3D)
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("De: ")
                            }
                            append(seuEmail)
                        },
                        style = textStyle,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Divider(color = Color.Gray, thickness = 2.dp)

                Row(modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.AccountCircle,
                        contentDescription = "user",
                        modifier = Modifier.size(45.dp),
                        tint = Color(0xFF253645)
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Para: ")
                            }
                            append(it.email)
                        },
                        style = textStyle,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Divider(color = Color.Gray, thickness = 2.dp)

                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
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
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    val interactionSourceButton1 = remember { MutableInteractionSource() }
                    val isPressedButton1 by interactionSourceButton1.collectIsPressedAsState()

                    Button(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .width(150.dp)
                            .height(40.dp),
                        onClick = { navController.navigate("enviados") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isPressedButton1) Color(0xFF253645) else Color(0xFFD20B3D),
                            contentColor = Color.White
                        ),
                    ) {
                        Text(text = "Voltar")
                    }

                    val interactionSourceButton2 = remember { MutableInteractionSource() }
                    val isPressedButton2 by interactionSourceButton2.collectIsPressedAsState()

                    Button(
                        onClick = { navController.navigate("novoEmail") },
                        modifier = Modifier
                            .width(150.dp)
                            .height(40.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isPressedButton2) Color(0xFF253645) else Color(0xFFD20B3D),
                            contentColor = Color.White
                        ),
                    ) {
                        Text(text = "Responder")
                    }
                }
            }
        }
    }
    NavBar(navController)
}
