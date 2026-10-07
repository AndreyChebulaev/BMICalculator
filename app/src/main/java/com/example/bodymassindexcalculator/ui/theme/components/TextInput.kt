package com.example.bodymassindexcalculator.ui.theme.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.example.bodymassindexcalculator.ui.theme.BMIGray
import com.example.bodymassindexcalculator.ui.theme.BMITypography

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    inputTransformation: InputTransformation? = null,
    label: String,
    placeholder: String,
    textState: TextFieldState
) {
    Column(modifier) {
        Text(
            text = label,
            style = BMITypography.Input
        )
        BasicTextField(
            state = textState,
            textStyle = BMITypography.Input,
            inputTransformation = inputTransformation,
            decorator = { content ->
                if (textState.text.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = BMITypography.Input
                    )
                }
                content()
            },
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    val y = size.height - strokeWidth / 2
                    drawLine(
                        color = BMIGray,
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = strokeWidth
                    )
                }
                .padding(8.dp)
        )
    }
}