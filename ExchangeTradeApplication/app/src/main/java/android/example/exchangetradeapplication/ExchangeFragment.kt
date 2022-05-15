package android.example.exchangetradeapplication

import android.example.exchangetradeapplication.databinding.FragmentExchangeBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import android.os.Handler
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar


class ExchangeFragment : Fragment() {

    private var _binding: FragmentExchangeBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: ExchangeViewModel

    private var handler: Handler = Handler()
    private var runnable: Runnable? = null
    var delay = 2000


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentExchangeBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(ExchangeViewModel::class.java)



        viewModel.usdRateDisplay.observe(viewLifecycleOwner,
            Observer { newValue -> binding.usdTOtryBirim.text = "$newValue" })
        viewModel.eurRateDisplay.observe(viewLifecycleOwner,
            Observer { newValue -> binding.eurTOtryBirim.text = "$newValue" })






        binding.buyUSDButton.setOnClickListener {

            if (binding.amount.text.isNotEmpty()) {

                val getUserAmount = binding.amount.text.toString().toInt()
                val getCurrency = viewModel.usdRateDisplay.value!!
                val totalAmount = getUserAmount * getCurrency


                val action =
                    ExchangeFragmentDirections.actionExchangeFragmentToResultFragment("You bougth " + getUserAmount + " USD for " + totalAmount.toString())

                view.findNavController().navigate(action)

            } else {
                val text = "You should enter the amount first !!!"
                val snackbar = Snackbar.make(binding.root, text, Snackbar.LENGTH_LONG)
                snackbar.show()

            }
        }


        binding.buyEURBUtton.setOnClickListener {

            if (binding.amount.text.isNotEmpty()) {

                val getUserAmount = binding.amount.text.toString().toInt()
                val getCurrency = viewModel.eurRateDisplay.value!!
                val totalAmount = getUserAmount * getCurrency

                val action =
                    ExchangeFragmentDirections.actionExchangeFragmentToResultFragment("You bougth " + getUserAmount + " EUR for " + totalAmount.toString())
                view.findNavController().navigate(action)
            }
                else{
                    val text = "You should enter the amount first !!!"
                    val snackbar = Snackbar.make( binding.root , text, Snackbar.LENGTH_LONG)
                snackbar.show()
                }
            }



        // Inflate the layout for this fragment
        return view
    }


    override fun onResume() {
        handler.postDelayed(Runnable {
            handler.postDelayed(runnable!!, delay.toLong())
            viewModel.changeCurrency()
        }.also { runnable = it }, delay.toLong())
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}