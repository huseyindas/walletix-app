package com.huseyindas.walletixapp.walletix

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.huseyindas.walletixapp.R
import com.huseyindas.walletixapp.adapter.ListAdapter
import com.huseyindas.walletixapp.api.Currency
import com.huseyindas.walletixapp.api.CurrencyAPI
import com.huseyindas.walletixapp.database.ExpenseViewModel
import kotlinx.android.synthetic.main.fragment_homepage.*
import kotlinx.android.synthetic.main.fragment_homepage.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var usd = 0.0
var eur = 0.0
var gbp = 0.0

class HomepageFragment : Fragment() {

    private lateinit var mExpenseViewModel: ExpenseViewModel
    lateinit var sharedPreferences : SharedPreferences

    var usdTry = 8.0
    var euroTry = 9.0
    var gbpTry = 10.0

    val BASE_URL = "https://free.currconv.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = this.requireActivity().getSharedPreferences("package com.huseyindas.walletixapp.walletix",
                Context.MODE_PRIVATE)

        getCurrencyRates()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_homepage, container, false)

        val adapter = ListAdapter()
        val recyclerView = view.recycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mExpenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)

        mExpenseViewModel.getAll.observe(this.requireActivity(), {
            adapter.setData(it)
        })

        val isimPreferences = sharedPreferences.getString("isim","")
        val cinsiyetPreferences = sharedPreferences.getString("cinsiyet","")

        view.name.text = isimPreferences + " " +cinsiyetPreferences



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addExpense.setOnClickListener {
            val action = HomepageFragmentDirections.actionHomepageFragmentToExpenseFragment()
            Navigation.findNavController(it).navigate(action)
        }

        card_view.setOnClickListener {
            val action = HomepageFragmentDirections.actionHomepageFragmentToProfileFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun getCurrencyRates() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CurrencyAPI::class.java)

        val call = service.getCurrencyDolar()
        val call2 = service.getCurrencyEuro()
        val call3 = service.getCurrencySterlin()

        call.enqueue(object : Callback<Currency> {
            override fun onFailure(call: Call<Currency>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Currency>, response: Response<Currency>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        usdTry= it.uSDTRY
                        println(usdTry)
                        usd=usdTry
                    }
                }
            }
        })

        call2.enqueue(object : Callback<Currency> {
            override fun onFailure(call2: Call<Currency>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call2: Call<Currency>, response: Response<Currency>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        euroTry= it.eURTRY
                        println(euroTry)
                        eur=euroTry
                    }
                }
            }
        })
        call3.enqueue(object : Callback<Currency> {
            override fun onFailure(call3: Call<Currency>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call3: Call<Currency>, response: Response<Currency>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        gbpTry= it.gBPTRY
                        println(gbpTry)
                        gbp=gbpTry
                    }
                }
            }
        })
    }


}