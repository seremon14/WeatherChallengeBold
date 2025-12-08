package com.weatherchallengebold.ui.search

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun searchScreen_displaysSearchField() {
        composeTestRule.setContent {
            SearchScreen(onLocationSelected = {})
        }

        composeTestRule.onNodeWithText("Buscar ubicación").assertIsDisplayed()
    }

    @Test
    fun searchScreen_userCanTypeInSearchField() {
        composeTestRule.setContent {
            SearchScreen(onLocationSelected = {})
        }

        composeTestRule.onNodeWithText("Buscar ubicación").performTextInput("Medellin")
    }
}

