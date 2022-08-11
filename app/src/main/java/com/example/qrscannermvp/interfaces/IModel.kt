package com.example.qrscannermvp.interfaces

interface IModel {

    interface HomeFrag{
        //TODO ("There is will be something to be done on the HomeFragment")
    }

    interface QrFrag{
        fun setNotification(codeText : String)
    }

    interface ResultFrag{

    }

}