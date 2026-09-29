package com.jarabrama.composeuitesting.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jarabrama.composeuitesting.ui.components.ContactForm
import com.jarabrama.composeuitesting.ui.components.ContactList
import com.jarabrama.composeuitesting.ui.data.Contact


@Composable
fun Screen(paddingValues: PaddingValues) {
  val contacts = remember { mutableStateListOf<Contact>() }
  val onCreateContact: (Contact) -> Unit = { contacts.add(it) }

  val remove: (Int) -> Unit = { index: Int -> contacts.removeAt(index) }
  Box(Modifier
    .padding(paddingValues)
    .fillMaxWidth()) {

    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(16.dp),
      modifier = Modifier.padding(horizontal = 16.dp)
    ) {
      ContactForm(onCreateContact)
      Text("Contactos", style = MaterialTheme.typography.titleLarge)
      ContactList(contacts, remove)
    }
  }
}