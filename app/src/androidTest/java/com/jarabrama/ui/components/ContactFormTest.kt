package com.jarabrama.ui.components

import androidx.compose.ui.layout.onVisibilityChangedNode
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.jarabrama.composeuitesting.ui.components.ContactForm
import com.jarabrama.composeuitesting.ui.data.Contact
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.kotlin.any
import org.mockito.kotlin.verify

class ContactFormTest {

  @get:Rule
  val rule = createComposeRule()

  @Test
  fun create_success_contact() {
    val onSaveContact = mock<(Contact) -> Unit>()
    val expected = Contact("Brayan Jaraba", "3233001867", "some description")

    rule.setContent {
      ContactForm(onSaveContact)
    }

    val nameInput = rule.onNode(hasTestTag("name_input"))
    nameInput.performTextInput(expected.name)

    val phoneInput = rule.onNode(hasTestTag("phone_input"))
    phoneInput.performTextInput(expected.phone)

    val descriptionInput = rule.onNode(hasTestTag("description_input"))
    descriptionInput.performTextInput(expected.description!!)

    val saveButton = rule.onNode(hasText("Guardar"))

    saveButton.performClick()

    verify(onSaveContact).invoke(expected)
  }

  @Test
  fun create_contact_whit_no_description() {
    val onSaveContact = mock<(Contact) -> Unit>()
    val expected = Contact("Brayan Jaraba", "3233001867", null)

    rule.setContent {
      ContactForm(onSaveContact)
    }

    val nameInput = rule.onNode(hasTestTag("name_input"))
    nameInput.performTextInput(expected.name)

    val phoneInput = rule.onNode(hasTestTag("phone_input"))
    phoneInput.performTextInput(expected.phone)

    val saveButton = rule.onNode(hasText("Guardar"))

    saveButton.performClick()

    verify(onSaveContact).invoke(expected)
  }

  @Test
  fun create_contact_with_void_name() {
    val onSaveContact = mock<(Contact) -> Unit>()
    val expected = Contact("Brayan Jaraba", "3233001867", null)

    rule.setContent {
      ContactForm(onSaveContact)
    }


    val phoneInput = rule.onNode(hasTestTag("phone_input"))
    phoneInput.performTextInput(expected.phone)

    val saveButton = rule.onNode(hasText("Guardar"))

    saveButton.performClick()

    rule.onNode(hasText("Nombre invalido")).assertIsDisplayed()

    verify(onSaveContact, never()).invoke(any())
  }

  @Test
  fun create_contact_with_void_phone() {
    val onSaveContact = mock<(Contact) -> Unit>()
    val expected = Contact("Brayan Jaraba", "3233001867", null)

    rule.setContent {
      ContactForm(onSaveContact)
    }

    val nameInput = rule.onNode(hasTestTag("name_input"))
    nameInput.performTextInput(expected.name)

    val saveButton = rule.onNode(hasText("Guardar"))

    saveButton.performClick()

    rule.onNode(hasText("Numero de teléfono invalido")).assertIsDisplayed()

    verify(onSaveContact, never()).invoke(any())
  }

  @Test
  fun create_contact_with_phoneNumber_whit_less_than_10_digits() {
    val onSaveContact = mock<(Contact) -> Unit>()
    val expected = Contact("Brayan Jaraba", "32330067", null)

    rule.setContent {
      ContactForm(onSaveContact)
    }

    val nameInput = rule.onNode(hasTestTag("name_input"))
    nameInput.performTextInput(expected.name)

    val phoneInput = rule.onNode(hasTestTag("phone_input"))
    phoneInput.performTextInput(expected.phone)

    val saveButton = rule.onNode(hasText("Guardar"))

    saveButton.performClick()

    rule.onNode(hasText("Numero de teléfono invalido")).assertIsDisplayed()

    verify(onSaveContact, never()).invoke(any())
  }

  @Test
  fun create_contact_whit_phoneNumber_with_non_digits_characters() {
    val onSaveContact = mock<(Contact) -> Unit>()
    val expected = Contact("Brayan Jaraba", "32330067as", null)

    rule.setContent {
      ContactForm(onSaveContact)
    }

    val nameInput = rule.onNode(hasTestTag("name_input"))
    nameInput.performTextInput(expected.name)

    val phoneInput = rule.onNode(hasTestTag("phone_input"))
    phoneInput.performTextInput(expected.phone)

    val saveButton = rule.onNode(hasText("Guardar"))

    saveButton.performClick()

    rule.onNode(hasText("Numero de teléfono invalido")).assertIsDisplayed()

    verify(onSaveContact, never()).invoke(any())
  }

  @Test
  fun remove_error_message_after_error() {
    val onSaveContact = mock<(Contact) -> Unit>()
    val expected = Contact("Brayan Jaraba", "3233001867", "some description")

    rule.setContent {
      ContactForm(onSaveContact)
    }


    val saveButton = rule.onNode(hasText("Guardar"))

    saveButton.performClick()

    val nameInput = rule.onNode(hasTestTag("name_input"))
    nameInput.performTextInput(expected.name)

    val phoneInput = rule.onNode(hasTestTag("phone_input"))
    phoneInput.performTextInput(expected.phone)

    val descriptionInput = rule.onNode(hasTestTag("description_input"))
    descriptionInput.performTextInput(expected.description!!)

    saveButton.performClick()

    rule.onNode(hasTestTag("error_message")).assertIsNotDisplayed()

    verify(onSaveContact).invoke(expected)
  }
}