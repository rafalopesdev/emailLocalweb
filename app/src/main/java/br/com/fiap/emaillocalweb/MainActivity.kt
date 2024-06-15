package br.com.fiap.emaillocalweb
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import br.com.fiap.emaillocalweb.ui.theme.EmailLocalwebTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.emaillocalweb.telas.Agenda
import br.com.fiap.emaillocalweb.telas.Cadastro
import br.com.fiap.emaillocalweb.telas.CaixaEntrada
import br.com.fiap.emaillocalweb.telas.Enviados
import br.com.fiap.emaillocalweb.telas.Login
import br.com.fiap.emaillocalweb.telas.NovoEmail
import br.com.fiap.emaillocalweb.telas.SplashScreen
import br.com.fiap.emaillocalweb.telas.ViewEmail

class MainActivity<NavHostController> : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmailLocalwebTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "splash"
                    )
                    {
                        composable(route = "splash"){ SplashScreen(navController)}
                        composable(route = "login"){ Login(navController)}
                        composable(route = "cadastro"){ Cadastro(navController) }
                        composable(route = "caixaEntrada"){
                            val seuEmail = "seuemail@example.com"
                            CaixaEntrada(navController, seuEmail)
                        }
                        composable("novoEmail") {
                            // Conteúdo da tela "Novo"
                            NovoEmail(navController)
                        }
                        composable("agenda") {
                            // Conteúdo da tela "Novo"
                            Agenda(navController)
                        }
                        composable("enviados") { Enviados(navController, "seuEmail@exemplo.com") }
                        composable("viewEmail/{emailId}/{seuEmail}") { backStackEntry ->
                            val emailId = backStackEntry.arguments?.getString("emailId") ?: ""
                            val seuEmail = "seuemail@example.com"
                            ViewEmail(navController, emailId, seuEmail)
                        }
                    }
                }
            }
        }
    }
}
