package com.mun.bonecci.circularprogressbar

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mun.bonecci.circularprogressbar.components.CircularProgressbar
import com.mun.bonecci.circularprogressbar.ui.theme.CircularProgressBarTheme
import com.mun.bonecci.circularprogressbar.ui.theme.dimen_16dp
import com.mun.bonecci.circularprogressbar.ui.theme.dimen_8dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CircularProgressBarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val languages = mutableListOf<LanguageData>()
                    languages.add(LanguageData(ENGLISH_LANGUAGE, 80))
                    languages.add(LanguageData(SPANISH_LANGUAGE, 100))
                    LanguageInfo(languages = languages)
                }
            }
        }
    }
}

@Composable
fun LanguageInfo(languages: List<LanguageData> = arrayListOf()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = dimen_16dp, end = dimen_16dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(dimen_16dp),
            horizontalArrangement = Arrangement.spacedBy(dimen_16dp)
        ) {
            items(languages) { language ->
                InfoItem(language, onItemClick = { data ->
                    Log.d("Language pressed: ", data.name)
                })
            }
        }
    }
}

@Composable
fun InfoItem(languageData: LanguageData, onItemClick: (LanguageData) -> Unit) {
    Box(
        modifier = Modifier
            .padding(top = dimen_8dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(languageData)
            },
        contentAlignment = Alignment.Center
    ) {
        Column {
            CircularProgressbar(
                dataUsage = languageData.percentage.toFloat(), name = languageData.name
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CircularProgressBarTheme {
        val languages = mutableListOf<LanguageData>()
        languages.add(LanguageData(ENGLISH_LANGUAGE, 80))
        languages.add(LanguageData(SPANISH_LANGUAGE, 100))
        LanguageInfo(languages = languages)
    }
}