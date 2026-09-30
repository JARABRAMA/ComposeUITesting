package com.jarabrama.composeuitesting.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.jarabrama.composeuitesting.ui.data.Contact
import com.jarabrama.composeuitesting.ui.theme.ComposeUITestingTheme

@Composable
fun ContactForm(onSaveContact: (Contact) -> Unit) {
  var name by remember { mutableStateOf("") }
  var phone by remember { mutableStateOf("") }
  var error by remember { mutableStateOf<String?>(null) }
  var description by remember { mutableStateOf("") }


  Surface(
    shape = MaterialTheme.shapes.medium,
    color = MaterialTheme.colorScheme.surfaceContainer,
    shadowElevation = 3.dp,
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(
      Modifier
        .padding(16.dp)
        .fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Text(
        text = "Contacto nuevo",
        style = MaterialTheme.typography.titleLarge
      )

      OutlinedTextField(
        value = name,
        onValueChange = { name = it },
        label = { Text("Nombre del contacto") },
        placeholder = { Text("Don Fulano") },
        singleLine = true,
        maxLines = 1,
        modifier = Modifier
          .fillMaxWidth()
          .semantics { testTag = "name_input" }
      )
      OutlinedTextField(
        value = phone,
        onValueChange = { phone = it },
        label = { Text("Numero de teléfono") },
        placeholder = { Text("323 000 0000") },
        keyboardOptions = KeyboardOptions(
          keyboardType = KeyboardType.Phone
        ),
        singleLine = true,
        maxLines = 1,
        modifier = Modifier
          .fillMaxWidth()
          .semantics { testTag = "phone_input" }

      )
      OutlinedTextField(
        value = description,
        onValueChange = { description = it },
        label = { Text("Descripción del contacto") },
        placeholder = { Text("El señor que vende el qeso") },

        singleLine = false,
        minLines = 2,
        modifier = Modifier
          .fillMaxWidth()
          .semantics { testTag = "description_input" }
      )

      if (error != null) {
        Text(
          error!!,
          color = MaterialTheme.colorScheme.error,
          modifier = Modifier.semantics { testTag = "error_message" })
      }

      Button(onClick = {
        if (name.trim().isBlank()) {
          error = "Nombre invalido"
          return@Button
        }

        val phoneWithOutSpaces = phone.trim().filter { it != ' ' }
        if (phoneWithOutSpaces.length != 10 || !phoneWithOutSpaces.isDigitsOnly() || phone.trim()
            .isBlank()
        ) {
          error = "Numero de teléfono invalido"
          return@Button
        }

        val contact = if (description.trim().isNotBlank()) Contact(name, phone, description)
        else Contact(name, phone, null)
        onSaveContact(contact)
        error = null
      }, modifier = Modifier.align(Alignment.End)) {
        Text("Guardar")
      }
    }
  }
}


@Preview
@Composable
private fun PreviewContactForm() {
  ComposeUITestingTheme {
    ContactForm() {}
  }
}