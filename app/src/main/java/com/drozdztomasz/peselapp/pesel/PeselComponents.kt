package com.drozdztomasz.peselapp.pesel

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.drozdztomasz.peselapp.R
import java.util.*

@Composable
fun PeselInputTextField(
    text: String,
    onTextChange: (String) -> Unit
) =
    TextField(
        value = text,
        onValueChange = onTextChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        textStyle = TextStyle(fontSize = 30.sp),
        label = { Text(stringResource(R.string.pesel_label)) },
        modifier = Modifier.fillMaxWidth()
    )

@Composable
fun PeselStateText(
    textId: Int
) =
    Text(text = stringResource(id = textId), fontSize = 24.sp)

@Composable
fun PeselInfoRow(
    textId: Int,
    label: String,
) =
    Row(horizontalArrangement = Arrangement.Center) {
        PeselInfoLabel(label = label)
        PeselInfoText(text = stringResource(id = textId))
    }

@Composable
fun PeselInfoDateRow(
    date: Date? = null,
    textId: Int? = null,
    label: String,
    formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
) =
    Row(horizontalArrangement = Arrangement.Center) {
        PeselInfoLabel(label = label)
        if (date != null)
            PeselInfoText(text = formatter.format(date))
        else if (textId != null)
            PeselInfoText(text = stringResource(id = textId))
    }

@Composable
fun PeselInfoLabel(
    label: String
) = PeselText(text = label, textAlign = TextAlign.Right)

@Composable
fun PeselInfoText(
    text: String
) = PeselText(text = text, textAlign = TextAlign.Left)

@Composable
fun PeselText(
    text: String,
    textAlign: TextAlign
) = Text(
    text = text,
    textAlign = textAlign,
    modifier = Modifier
        .width(160.dp)
        .padding(horizontal = 8.dp),
    fontSize = 20.sp
)
