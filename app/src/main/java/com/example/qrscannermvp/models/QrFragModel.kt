package com.example.qrscannermvp.models

import com.example.qrscannermvp.interfaces.IModel
import com.example.qrscannermvp.interfaces.IPresenter

class QrFragModel(private val iPresenter: IPresenter.QrFrag) : IModel.QrFrag{

    private lateinit var text : String

    override fun setNotification(codeText: String) {

        text = "This QR/barcode isn't supported by this app \n" +
                           "It contains: $codeText \n" +
                           "Press on the screen to continue scanning."

        val notificationText : List<String> = text.split("\n")

        iPresenter.showNotification(notificationText)
    }
}