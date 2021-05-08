package com.huseyindas.walletixapp.walletix

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.huseyindas.walletixapp.R
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment() {

    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = this.requireActivity().getSharedPreferences("package com.huseyindas.walletixapp.walletix",
                Context.MODE_PRIVATE)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonSave.setOnClickListener {
            save(view)
            findNavController().navigate(R.id.action_profileFragment_to_homepageFragment)
        }
    }

    fun save(view: View){

        val isim= view.textNameInput.text.toString()
        var cinsiyet : String? = null

        if(radioMale.isChecked){
            cinsiyet = "Bey"
        }
        else if(radioFemale.isChecked){
            cinsiyet = "Hanım"
        }
        else if(radioNull.isChecked){
            cinsiyet = ""
        }

        sharedPreferences.edit().putString("isim",isim).apply()

        sharedPreferences.edit().putString("cinsiyet",cinsiyet).apply()
    }

}