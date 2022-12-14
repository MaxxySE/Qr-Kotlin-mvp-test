package com.example.qrscannermvp.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.example.qrscannermvp.R
import com.example.qrscannermvp.interfaces.Communicator
import java.io.InputStream


class ResultFragment : Fragment() {

    private var result: String? = ""
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_result, container, false)
        init(view)
        return view
    }

    private fun init(view : View){
        communicator = activity as Communicator
        result = arguments?.getString("string")
        setDataToResultScreen(view, result.toString())
        clickListeners(view)
    }

    private fun clickListeners(view : View){
        view.findViewById<Button>(R.id.rescanBtn).setOnClickListener {
            communicator.passData("", "qr")
        }

        view.findViewById<Button>(R.id.backBtn).setOnClickListener {
            communicator.passData("", "home")
        }

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    communicator.passData("", "qr")
                }
            })
    }

    private fun setDataToResultScreen(view : View, result : String){
        setImage(view, result)
        setText(view, result)
    }

    private fun setImage(view : View, imageFile : String){
        val inputStream : InputStream? = activity?.assets?.open("$imageFile.jpg")
        val bitmap = BitmapFactory.decodeStream(inputStream)
        view.findViewById<ImageView>(R.id.resultImage).setImageBitmap(bitmap)
    }

    private fun setText(view : View, textFile : String){
        val text: String = activity?.assets?.open("$textFile.txt")?.bufferedReader().use {
            it?.readText().toString() }
        view.findViewById<TextView>(R.id.resultText).text = text

    }

    companion object {
        @JvmStatic
        fun newInstance() = ResultFragment()
    }
}