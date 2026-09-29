package com.jarabrama.ui.components

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import com.jarabrama.composeuitesting.ui.components.ContactCard
import com.jarabrama.composeuitesting.ui.data.Contact
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify


class ContactCardTest {

  @get:Rule
  val rule = createComposeRule()


  @Test
  fun contactCard_displays_contact_information() {
    val contact = Contact("Brayan Jaraba", "3233001867", "a short description")

    rule.setContent {
      ContactCard(contact, 1) { }
    }

    rule.onNode(hasText(contact.name)).assertIsDisplayed()
    rule.onNode(hasText(contact.description!!)).assertIsDisplayed()
    rule.onNode(hasText(contact.phone)).assertIsDisplayed()
  }

  @Test
  fun contactCard_whit_null_description_show_label() {
    val contact = Contact("Brayan Jaraba", "3233001867", null)

    rule.setContent {
      ContactCard(contact, 1) { }
    }

    rule.onNode(hasText("Sin descripción")).isDisplayed()
  }

  @Test
  fun contactCard_performs_delete_when_press_deleteButton() {
    val contact = Contact("Brayan Jaraba", "3233001867", null)
    val onDelete = mock<(Int) -> Unit>()
    rule.setContent {
      ContactCard(contact, 1, onDelete)
    }

    val button = rule.onNode(hasTestTag("delete_button"))
    button.isDisplayed()
    button.assertHasClickAction()

    button.performClick()

    verify(onDelete).invoke(1)


  }
}