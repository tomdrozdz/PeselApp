package com.drozdztomasz.peselapp.pesel

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.drozdztomasz.peselapp.R
import java.util.*

@ExperimentalAnimationApi
@Composable
fun PeselScreen(
    peselText: String,
    onPeselTextChange: (String) -> Unit,
    peselStateTextId: Int,
    infoVisible: Boolean,
    dateOfBirthId: Int?,
    dateOfBirth: Date?,
    sexTextId: Int,
    checksumTextId: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
        ) {
            PeselInputTextField(peselText, onPeselTextChange)
        }

        Box(modifier = Modifier.padding(top = 40.dp)) {
            PeselStateText(textId = peselStateTextId)
        }

        AnimatedVisibility(visible = infoVisible) {
            Box(modifier = Modifier.padding(top = 40.dp)) {
                Column() {
                    PeselInfoDateRow(
                        textId = dateOfBirthId,
                        date = dateOfBirth,
                        label = stringResource(id = R.string.dateOfBirth_label)
                    )
                    PeselInfoRow(
                        textId = sexTextId,
                        label = stringResource(id = R.string.sex_label)
                    )
                    PeselInfoRow(
                        textId = checksumTextId,
                        label = stringResource(id = R.string.checksum_label)
                    )
                }
            }
        }
    }
}
