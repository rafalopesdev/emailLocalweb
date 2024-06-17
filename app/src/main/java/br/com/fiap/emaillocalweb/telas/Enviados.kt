package br.com.fiap.emaillocalweb.telas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.emaillocalweb.EmailDb
import br.com.fiap.emaillocalweb.EmailModel
import br.com.fiap.emaillocalweb.R
import br.com.fiap.emaillocalweb.components.EmailCard
import br.com.fiap.emaillocalweb.components.NavBar
import br.com.fiap.emaillocalweb.components.Profile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Enviados(navController: NavController, seuEmail: String) {
    val context = LocalContext.current
    val db = EmailDb.getDatabase(context)
    val emailDao = db.emailDao()

    var emails by remember { mutableStateOf<List<EmailModel>>(emptyList()) }

    // Load emails when the screen is composed
    LaunchedEffect(Unit) {
        emails = emailDao.listarEmails()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Profile(navController)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
    ) {
        Row (verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.logolocaw), contentDescription = "imagem logo",
                modifier = Modifier.size(35.dp)

            )
            Text(
                modifier = Modifier.padding(12.dp),
                text = "Enviados",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(570.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(emails) { email ->
                    EmailCard(
                        email = email,
                        seuEmail = seuEmail,
                        navController = navController,
                        onDelete = { emailToDelete ->
                            CoroutineScope(Dispatchers.IO).launch {
                                emailDao.excluir(emailToDelete)
                                emails = emailDao.listarEmails()
                            }
                        },
                    )
                    Divider(color = Color.Gray, thickness = 1.dp)
                }
            }

        }

    }
    }
    NavBar(navController)
}


