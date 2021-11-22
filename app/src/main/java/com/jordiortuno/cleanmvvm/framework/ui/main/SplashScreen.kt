package com.jordiortuno.cleanmvvm.framework.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jordiortuno.cleanmvvm.framework.firebaseTools.CheckDatabaseListener
import com.jordiortuno.cleanmvvm.framework.firebaseTools.DialogType
import com.jordiortuno.cleanmvvm.framework.firebaseTools.ShowDialogRealtimeListener
import com.jordiortuno.cleanmvvm.framework.ui.auth.AuthActivity
import com.jordiortuno.cleanmvvm.framework.ui.main.common.DialogsRealtime

class SplashScreen:AppCompatActivity(),CheckDatabaseListener,ShowDialogRealtimeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //RealtimeDatabaseFacade.init(this,this)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }



    fun goToMainActivity(){
        startActivity(Intent(this, AuthActivity::class.java))
    }
    fun goToAuthActivity(){
        //startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onCheckDatabaseFinish(canPass: Boolean) {
        //loginCorrect
        goToMainActivity()
        //loginIncorrect
        goToAuthActivity()
    }

    override fun showDialog(dialogType: DialogType) {
        DialogsRealtime.manageDialogsRealtime(this,dialogType)
    }

}