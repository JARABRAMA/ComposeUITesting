package com.jarabrama.composeuitesting.ui.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jarabrama.composeuitesting.ui.data.Contact

@Composable
fun ContactList(contacts: List<Contact>, onRemoveContact: (Int) -> Unit) {
  LazyColumn(Modifier.padding(horizontal = 8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
    itemsIndexed(contacts) { index, contact ->
      ContactCard(contact, index, onRemoveContact)
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun PreviewContactList() {
  val contactList = listOf(
    Contact("Brayan Jaraba", "323 300 6787", null),
    Contact("Brayan Jaraba", "323 300 6787", null),
    Contact("Brayan Jaraba", "323 300 6787", null),
    Contact("Brayan Jaraba", "323 300 6787", null)
  )
  ContactList(contactList) { }
}