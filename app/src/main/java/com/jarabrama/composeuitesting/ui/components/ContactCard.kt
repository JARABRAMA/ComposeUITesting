package com.jarabrama.composeuitesting.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jarabrama.composeuitesting.R
import com.jarabrama.composeuitesting.ui.data.Contact


@Composable
fun ContactCard(contact: Contact, index: Int, onRemove: (Int) -> Unit) {
  Card(modifier = Modifier.fillMaxWidth()) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.End,
      modifier = Modifier.padding(16.dp)
    ) {
      Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth(.9f)
      ) {
        Text("Contacto", style = MaterialTheme.typography.titleMedium)
        ContactRow("Nombre:", contact.name)
        ContactRow("Teléfono:", contact.phone)
        ContactRow(
          "Descripción:", contact.description ?: "Sin descripción" +
          ""
        )
      }
      IconButton({ onRemove(index) }, Modifier.semantics { testTag = "delete_button" }) {
        Icon(painterResource(R.drawable.delete), "Eliminar")
      }
    }
  }
}


@Composable
private fun ContactRow(label: String, value: String) {
  Row {
    Text(
      text = label,
      style = MaterialTheme.typography.titleSmall,
      modifier = Modifier.weight(1f),
      color = MaterialTheme.colorScheme.onPrimaryContainer
    )
    Text(
      text = value,
      modifier = Modifier.weight(2f)
    )
  }
}

@Preview
@Composable
private fun PreviewContactCard() {
  val contact = Contact("Brayan Jaraba", "323 300 6787", null)
  ContactCard(contact, 1) {}
}