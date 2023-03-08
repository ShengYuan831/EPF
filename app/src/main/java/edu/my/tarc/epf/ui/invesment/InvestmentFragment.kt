package edu.my.tarc.epf.ui.invesment

import android.app.DatePickerDialog
import android.icu.text.DateFormat.DAY
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import androidx.compose.ui.text.intl.Locale
import edu.my.tarc.epf.R
import edu.my.tarc.epf.databinding.FragmentGalleryBinding
import edu.my.tarc.epf.databinding.FragmentInvestmentBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*
import java.util.Locale

/**
 * A simple [Fragment] subclass.
 * Use the [InvestmentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InvestmentFragment : Fragment() {

    private var _binding: FragmentInvestmentBinding? = null
    private val binding get() = _binding!!
    private var calendar: Calendar = getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding = FragmentInvestmentBinding.inflate(layoutInflater)
        _binding = FragmentInvestmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonCalculate.setOnClickListener{
            val account_balance = binding.editTextBalanceAccount1.text.toString().toInt()

        }
        binding.buttonReset.setOnClickListener{

        }

        val dateSetListener = DatePickerDialog.OnDateSetListener{view, year, month, day ->
            calendar.set(YEAR, year)
            calendar.set(MONTH, month)
            calendar.set(DAY_OF_YEAR, day)
            //update UI
            updateDateView()
        }

        binding.buttonDOB.setOnClickListener{
            DatePickerDialog(it.context,
                dateSetListener,
                calendar.get(YEAR),
                calendar.get(MONTH),
                calendar.get(DAY_OF_YEAR)).show()
        }
    }

    private fun updateDateView() {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.buttonDOB.text = sdf.format(calendar.time)

        val endDate = getInstance()
        val age = dayBetween(calendar, endDate).div(365)
        binding.textViewAge.text = age.toString()
    }

    private fun dayBetween(startDate: Calendar, endDate: Calendar): Long{
        val date = startDate.clone() as Calendar
        var days: Long = 0
        while(date.before(endDate)){
            date.add(DAY_OF_MONTH, 1)
            days++
        }
        return days
    }

}