package android.example.exchangetradeapplication

import android.example.exchangetradeapplication.databinding.FragmentResultBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController


class ResultFragment : Fragment() {
    private var _binding : FragmentResultBinding?=null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater,container,false)

        val view =binding.root
        val result = ResultFragmentArgs.fromBundle(requireArguments()).amount
        binding.resultText.setText(result)

       binding.backButton.setOnClickListener {
           val action = ResultFragmentDirections.actionResultFragmentToExchangeFragment()

           view.findNavController().navigate(action
           )
       }



        return view

        // Inflate the layout for this fragment
    }


}