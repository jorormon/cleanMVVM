package com.jordiortuno.cleanmvvm.framework.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jordiortuno.cleanmvvm.R
import com.jordiortuno.cleanmvvm.framework.ui.main.MainActivity

class AuthActivity : AppCompatActivity() {//ShowDialogRealtimeListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth_activity)

        //RealtimeDatabaseFacade.setShowDialogRealtime(this)
    }

    fun startSession(){
        val startIntent = Intent(applicationContext, MainActivity::class.java)
        startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(startIntent)
        finish()
    }

    /*override fun showDialog(dialogType: DialogType) {
        DialogsRealtime.manageDialogsRealtime(this,dialogType)
    }*/
}