package com.example.qrscannermvp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.qrscannermvp.fragments.HomeFragment
import com.example.qrscannermvp.fragments.QrFragment
import com.example.qrscannermvp.fragments.ResultFragment
import com.example.qrscannermvp.interfaces.Communicator



class MainActivity : AppCompatActivity(), Communicator {

    private val communicator: Communicator = this as Communicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verifyPermissions()
        communicator.passData("", "home")
    }

    override fun passData(string: String, ft : String) {
        when(ft){
            "home" -> setFragment(HomeFragment.newInstance(), string)
            "qr" -> setFragment(QrFragment.newInstance(), string)
            "result" -> setFragment(ResultFragment.newInstance(), string)
        }
    }

    private fun setFragment(fragment: Fragment, string : String){
        val bundle = Bundle()
        bundle.putString("string", string)

        val transaction = this.supportFragmentManager.beginTransaction()
        fragment.arguments = bundle

        transaction.replace(R.id.fragmentHolder, fragment)
        transaction.commit()
    }

    private fun verifyPermissions() {
        val PERMISSION_ALL = 1
        val PERMISSIONS = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )

        if (!checkPermissions(this, *PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL)
        }
    }

    private fun checkPermissions(context: Context?, vararg permissions: String?): Boolean {
        if (context != null) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        permission!!
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
        }
        return true
    }
}