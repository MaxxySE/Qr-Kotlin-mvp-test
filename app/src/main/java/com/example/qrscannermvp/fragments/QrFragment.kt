package com.example.qrscannermvp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.qrscannermvp.R
import com.example.qrscannermvp.interfaces.Communicator
import com.example.qrscannermvp.interfaces.IPresenter
import com.example.qrscannermvp.interfaces.IView
import com.example.qrscannermvp.presenters.QrFragPresenter


class QrFragment : Fragment(), IView.QrFrag {

    private lateinit var codeScanner: CodeScanner
    private lateinit var scannerView: CodeScannerView
    private lateinit var communicator: Communicator

    private var iPresenter :IPresenter.QrFrag = QrFragPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_qr, container, false)
        init(view)
        return view
    }

    private fun init(view : View){
        scannerView = view.findViewById(R.id.scanner_view)
        communicator = activity as Communicator
        codeScanner(view)
        clickListeners()
    }

    private fun clickListeners(){
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    communicator.passData("", "home")
                }
            }
        )



    }

    private fun codeScanner(view : View){

        codeScanner = CodeScanner(view.context, scannerView)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.SINGLE
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                checkAndDisplayQrResultData(it.toString())
            }

            errorCallback = ErrorCallback {
                activity?.runOnUiThread {
                    Log.e("Main", "Camera initialization error: ${it.message}")
                }
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }

    }

    private fun checkAndDisplayQrResultData(it : String){
        if(it.contains("File")){
            communicator.passData(it, "result")
            println(it)
        } else {
            activity?.runOnUiThread {
                iPresenter.setNotification(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        super.onPause()
        codeScanner.releaseResources()
    }

    override fun showNotification(notificationText: List<String>) {
        for(i in notificationText.indices){
            Toast.makeText(activity, notificationText[i], Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = QrFragment()
    }

}

