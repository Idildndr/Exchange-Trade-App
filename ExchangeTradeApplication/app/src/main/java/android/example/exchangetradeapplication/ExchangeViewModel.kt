package android.example.exchangetradeapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class ExchangeViewModel :ViewModel() {

    private val usdRate = Random.nextDouble(from = 14.70, until = 14.80)
    private val eurRate = Random.nextDouble(from = 16.0, until = 16.10)

    val _usdRateDisplay = MutableLiveData<Double>(usdRate)
    val _eurRateDisplay = MutableLiveData<Double>(eurRate)


    val eurRateDisplay:LiveData<Double>
    get() = _eurRateDisplay

    val usdRateDisplay:LiveData<Double>
    get() = _usdRateDisplay





    fun changeCurrency(){
        _usdRateDisplay.value= Random.nextDouble(from = 14.70, until = 14.80)
        _eurRateDisplay.value =Random.nextDouble(from = 16.0, until = 16.10)
    }

}