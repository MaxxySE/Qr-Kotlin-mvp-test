package com.example.qrscannermvp.presenters

import com.example.qrscannermvp.interfaces.IModel
import com.example.qrscannermvp.interfaces.IPresenter
import com.example.qrscannermvp.interfaces.IView
import com.example.qrscannermvp.models.QrFragModel

class QrFragPresenter(private val iView: IView.QrFrag) : IPresenter.QrFrag {

    var iModel : IModel.QrFrag = QrFragModel(this)

    override fun setNotification(codeText: String) {
        iModel.setNotification(codeText)
    }

    override fun showNotification(notificationText: List<String>) {
        iView.showNotification(notificationText)
    }
}