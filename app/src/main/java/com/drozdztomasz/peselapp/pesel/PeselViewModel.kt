package com.drozdztomasz.peselapp.pesel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.drozdztomasz.peselapp.R
import java.util.*

class PeselViewModel : ViewModel() {

    companion object {
        private const val SEX_DIGIT_INDEX = 9
        private const val CONTROL_DIGIT_INDEX = 10
        private const val PESEL_LENGTH = 11
        private val WEIGHTS = listOf(1, 3, 7, 9, 1, 3, 7, 9, 1, 3)
    }

    var pesel: String by mutableStateOf("")
        private set

    var peselState: Int by mutableStateOf(0)
        private set

    var infoVisible: Boolean by mutableStateOf(false)
        private set

    var dateOfBirthId: Int? by mutableStateOf(0)
        private set

    var dateOfBirth: Date? by mutableStateOf(Date(10))
        private set

    var sex: Int by mutableStateOf(0)
        private set
    var checksum: Int by mutableStateOf(0)
        private set

    init {
        peselState = R.string.pesel_empty_state
    }

    fun onPeselChanged(newPesel: String) {
        if (newPesel.length <= PESEL_LENGTH)
            pesel = newPesel

        when (pesel.length) {
            PESEL_LENGTH -> {
                peselState = R.string.pesel_valid_state
                setInfo()
            }
            else -> {
                peselState = when {
                    pesel.isNotEmpty() -> R.string.pesel_short_state
                    else -> R.string.pesel_empty_state
                }
                resetInfo()
            }
        }
    }

    private fun setInfo() {
        dateOfBirth = calculateDate(pesel)
        dateOfBirthId = if (dateOfBirth == null) R.string.dateOfBirth_invalid else null

        sex =
            if (pesel[SEX_DIGIT_INDEX].toString()
                    .toInt() % 2 == 1
            ) R.string.sex_male else R.string.sex_female

        checksum = if (pesel[CONTROL_DIGIT_INDEX].toString().toInt() == calculateCheckNumber(pesel))
            R.string.checksum_valid
        else
            R.string.checksum_invalid
        1
        infoVisible = true
    }

    private fun resetInfo() {
        infoVisible = false
    }

    private fun calculateDate(pesel: String): Date? {
        // odtąd będą magic numbers, bo nawet nie wiem jakie nazwy im nadać
        var year = pesel.substring(0, 2).toInt()
        year += when (pesel[2].toString().toInt()) {
            0, 1 -> 1900
            2, 3 -> 2000
            4, 5 -> 2100
            6, 7 -> 2200
            8, 9 -> 1800
            else -> 0
        }

        var month = pesel[3].toString().toInt()
        if (pesel[2].toString().toInt() % 2 == 1)
            month += 10

        val day = pesel.substring(4, 6).toInt()

        val calendar = Calendar.getInstance()
        calendar.isLenient = false
        calendar.set(year, month - 1, day)

        return try {
            calendar.time
        } catch (e: IllegalArgumentException) {
            null
        }
    }

    private fun calculateCheckNumber(pesel: String): Int {
        var sum = 0
        for (i in WEIGHTS.indices) {
            sum += (pesel[i].toString().toInt() * WEIGHTS[i])
        }
        return 10 - (sum % 10)
    }
}
