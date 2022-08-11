package com.example.qrscannermvp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.qrscannermvp.R
import com.example.qrscannermvp.interfaces.Communicator


class HomeFragment : Fragment() {

    private lateinit var communicator : Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        init()
        clickListeners(view)
        return view
    }

    private fun init(){
        communicator = activity as Communicator
    }

    private fun clickListeners(view : View){
        view.findViewById<Button>(R.id.scanBtn).setOnClickListener {
            communicator.passData("", "qr")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

}