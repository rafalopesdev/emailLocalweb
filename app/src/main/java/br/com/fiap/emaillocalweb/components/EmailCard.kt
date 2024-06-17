package br.com.fiap.emaillocalweb.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import br.com.fiap.emaillocalweb.EmailModel
import br.com.fiap.emaillocalweb.R


@Composable
fun EmailCard(
    email: EmailModel,
    seuEmail: String,
    navController: NavController,
    onDelete: (EmailModel) -> Unit,
) {
    val textStyle = TextStyle(
        fontSize = 16.sp,
        color = Color.Black,
        // Adicione outras propriedades de estilo conforme necessário
    )
    var isHovered by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                navController.navigate("viewEmail/${email.id}/${seuEmail}")
            }
            .background(if (isHovered) Color.Gray else Color.White),
        colors = CardDefaults.cardColors(
            containerColor = Color.White // Aqui você define a cor de fundo desejada
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically


        ) {
            Column {

            Image(
                painter = painterResource(id = R.drawable.iconperson),
                contentDescription = "imagem logo",
                modifier = Modifier
                    .size(40.dp)
                //.padding(top = 10.dp
                //.background(Color.Red),

            )}

            Column(

            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("De: ")
                        }
                        append(seuEmail)
                    },
                    style = textStyle
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Para: ")
                        }
                        val truncatedEmail = if (email.email.length > 16) {
                            email.email.take(14) + "..."
                        } else {
                            email.email
                        }
                        append(truncatedEmail)
                    },
                    style = textStyle
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Assunto: ")
                        }
                        val truncatedAssunto = if (email.assunto.length > 10) {
                            email.assunto.take(10) + "..."
                        } else {
                            email.assunto
                        }
                        append(truncatedAssunto)
                    },
                    style = textStyle
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Mensagem: ")
                        }
                        if (email.message.split(" ").size > 2) {
                            // Se a mensagem tiver mais de 5 palavras, exibe apenas as primeiras 5 palavras
                            append(email.message.split(" ").take(2).joinToString(" "))
                            append("...")
                        } else {
                            // Caso contrário, exibe a mensagem completa
                            append(email.message)
                        }
                    },
                    style = textStyle
                )
            }

            Column {
                Text(
                    text = "${email.horario}",
                    fontSize = 10.sp
                )
                IconButton(
                    modifier = Modifier
                        .padding(0.dp),

                    onClick = {onDelete(email)}

                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Excluir",
                        tint = Color.Gray,
                        modifier = Modifier
                            .padding(0.dp)
                    )
                }
            }

        }
    }
}

