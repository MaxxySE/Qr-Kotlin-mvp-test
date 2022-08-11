package com.example.qrscannermvp.interfaces

interface IPresenter {

    interface HomeFrag{
        //TODO ("There is will be something to be done on the HomeFragment")
    }

    interface QrFrag{
        fun setNotification(codeText : String)
        fun showNotification(notificationText: List<String>)
    }

    interface ResultFrag{

    }

}