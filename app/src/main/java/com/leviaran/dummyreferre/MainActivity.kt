package com.leviaran.dummyreferre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.android.installreferrer.api.ReferrerDetails
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var referreClient : InstallReferrerClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        referreClient = InstallReferrerClient.newBuilder(this).build()
        referreClient.startConnection(object: InstallReferrerStateListener{
            override fun onInstallReferrerSetupFinished(responseCode: Int) {
                when(responseCode) {
                    InstallReferrerClient.InstallReferrerResponse.OK -> {
                        Log.e("Referre status","true")
                        val response : ReferrerDetails = referreClient.installReferrer
                        val referreUrl : String = response.installReferrer
                        val referreClickTime : Long = response.referrerClickTimestampSeconds
                        val appInstallTime: Long = response.installBeginTimestampSeconds
                        val installExperienceLaunched : Boolean = response.googlePlayInstantParam

                        textView.text = referreUrl
                        textView2.text = referreClickTime.toString()
                        textView3.text = appInstallTime.toString()
                        textView4.text = installExperienceLaunched.toString()
                    }

                    InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED -> {
                        Log.e("Referre status","Not supported")
                    }

                    InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE -> {
                        Log.e("Referre status","service unavalaiable")
                    }
                }
            }

            override fun onInstallReferrerServiceDisconnected() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}
