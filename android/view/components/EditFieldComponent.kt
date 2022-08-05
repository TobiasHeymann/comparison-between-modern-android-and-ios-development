package com.tobiasheymann.ep.view.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditFieldComponent(modifier: Modifier, value: String, onValueChange: (String) -> Unit, placeholder: String) {
    BasicTextField(
        modifier = modifier.padding(horizontal = 4.dp),
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        maxLines = 1,
        textStyle = MaterialTheme.typography.body1,
        decorationBox = { innerTextField ->
            Row(modifier = Modifier.fillMaxWidth()) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
            innerTextField()
        }
    )
}