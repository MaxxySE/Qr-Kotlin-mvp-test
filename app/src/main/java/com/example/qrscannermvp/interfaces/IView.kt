package com.example.qrscannermvp.interfaces

interface IView {

    interface HomeFrag{
        //TODO ("There is will be something to be done on the HomeFragment")
    }

    interface QrFrag{
        fun showNotification(notificationText: List<String>)
    }

    interface ResultFrag{

    }

}