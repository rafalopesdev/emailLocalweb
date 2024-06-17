package br.com.fiap.emaillocalweb.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.rounded.KeyboardArrowRight


@Composable
fun FormFilter(filterText: String, onFilterTextChanged: (String) -> Unit) {
    Column {
        OutlinedTextField(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(),
            value = filterText,
            onValueChange = onFilterTextChanged,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text),
            textStyle = LocalTextStyle.current.copy(Color(0xFF253645)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFD20B3D),
                unfocusedBorderColor = Color(0xFF253645),
                cursorColor = Color(0xFF253645)),
            shape = RoundedCornerShape(16.dp),
            placeholder = {Text(text = "Digite a palavra que deseja buscar")},
            trailingIcon = {
                Icon(imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription = "")
            },
            label = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Pesquisar",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = "Filtrar")


                }
            }
        )
    }
}