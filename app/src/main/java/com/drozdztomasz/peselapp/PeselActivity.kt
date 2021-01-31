// Tomasz Drozdz, 246718
// Testowane na emulatorze + Samsung Galaxy Note 9

package com.drozdztomasz.peselapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import com.drozdztomasz.peselapp.pesel.PeselScreen
import com.drozdztomasz.peselapp.pesel.PeselViewModel
import com.drozdztomasz.peselapp.ui.theme.PeselAppTheme

@ExperimentalAnimationApi
class PeselActivity : AppCompatActivity() {
    private val peselViewModel by viewModels<PeselViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PeselAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    PeselActivityScreen(peselViewModel)
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun PeselActivityScreen(peselViewModel: PeselViewModel) {
    PeselScreen(
        peselViewModel.pesel,
        peselViewModel::onPeselChanged,
        peselViewModel.peselState,
        peselViewModel.infoVisible,
        peselViewModel.dateOfBirthId,
        peselViewModel.dateOfBirth,
        peselViewModel.sex,
        peselViewModel.checksum
    )
}

