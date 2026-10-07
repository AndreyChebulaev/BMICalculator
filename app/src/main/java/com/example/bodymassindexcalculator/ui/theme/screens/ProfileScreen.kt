package com.example.bodymassindexcalculator.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bodymassindexcalculator.R
import com.example.bodymassindexcalculator.ui.theme.BMIGrayLight
import com.example.bodymassindexcalculator.ui.theme.BMITypography
import com.example.bodymassindexcalculator.ui.theme.components.MainCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bodymassindexcalculator.Screen
import com.example.bodymassindexcalculator.ui.theme.components.Navigation
import com.example.bodymassindexcalculator.ui.theme.viewmodel.ProfileViewModel


@Composable
fun ProfileScreen(changeScreen: (Screen) -> Unit) {
    val viewModel: ProfileViewModel = viewModel()

    val user by viewModel.user.collectAsState()
    val results by viewModel.results.collectAsState()

    Scaffold(
        bottomBar = { Navigation(Screen.Profile, changeScreen) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BMIGrayLight)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                MainCard(Modifier.padding(vertical = 8.dp)) {
                    Image(
                        modifier = Modifier.fillMaxWidth().size(80.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.outline_account_circle_24),
                        alignment = Alignment.Center,
                        contentDescription = "Аватар"
                    )
                    Text(
                        modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth(),
                        text = "${user.firstName} ${user.lastName}",
                        style = BMITypography.Complementary.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth(),
                        text = user.email,
                        style = BMITypography.Complementary
                    )
                }
                MainCard(Modifier.padding(vertical = 8.dp)) {
                    Text(
                        text = "Активность",
                        style = BMITypography.Title.copy(fontSize = 16.sp)
                    )
                }
            }
            items(results) {
                MainCard(Modifier.padding(vertical = 8.dp)) {
                    Text(
                        text = "Время расчёта",
                        style = BMITypography.Complementary
                    )
                    Text(
                        text = it.createdAt,
                        style = BMITypography.Result
                    )
                    Row(
                        modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Рост",
                                style = BMITypography.Complementary
                            )
                            Text(
                                text = it.height.toString(),
                                style = BMITypography.Result
                            )
                        }
                        Column {
                            Text(
                                text = "Вес",
                                style = BMITypography.Complementary
                            )
                            Text(
                                text = it.weight.toString(),
                                style = BMITypography.Result
                            )
                        }
                        Column {
                            Text(
                                text = "Индекс Массы Тела",
                                style = BMITypography.Complementary
                            )
                            Text(
                                text = "%.2f".format(it.bmi),
                            style = BMITypography.Result
                            )
                        }
                    }
                    Text(
                        text = "Рекомендация",
                        style = BMITypography.Complementary
                    )
                    Text(
                        text = it.recommendation,
                        style = BMITypography.Result,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}