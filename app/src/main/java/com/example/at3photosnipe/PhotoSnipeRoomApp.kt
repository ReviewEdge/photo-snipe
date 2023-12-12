package com.example.at3photosnipe

import android.app.Application

class PhotoSnipeRoomApp: Application() {
    lateinit var container: AppContainer
    companion object{
        private var appInstance: PhotoSnipeRoomApp? = null

        fun getApp(): PhotoSnipeRoomApp{
            if(appInstance == null){
                throw Exception("app is null")
            }
            return appInstance!!
        }
    }

    override fun onCreate() {
        appInstance = this
        container = DefaultContainer(context = this)
        super.onCreate()
    }

}