package com.jordiortuno.cleanmvvm.framework.ui.main.common

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.jordiortuno.cleanmvvm.R
import com.jordiortuno.cleanmvvm.framework.firebaseTools.DialogType
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object DialogsRealtime {

    fun manageDialogsRealtime(activity: Activity,dialogType: DialogType){
        when(dialogType){
            DialogType.UPDATE -> showUpdateDialog(activity)
            DialogType.MAINTENANCE -> showMaintenanceDialog(activity)
        }
    }

    private fun showMaintenanceDialog(activity: Activity) {
        MaterialAlertDialogBuilder(activity)
            .setTitle(activity.resources.getString(R.string.maintenance))
            .setMessage(activity.resources.getString(R.string.maintenance_message))
            .setCancelable(false)
            .setPositiveButton(activity.getString(R.string.close_app)) { _, _ ->
                activity.finishAndRemoveTask()
            }.show()
    }

    private fun showUpdateDialog(activity: Activity) {
        MaterialAlertDialogBuilder(activity)
            .setTitle(activity.resources.getString(R.string.update))
            .setMessage(activity.resources.getString(R.string.update_message))
            .setCancelable(false)
            .setNegativeButton(activity.getString(R.string.close)) { _, _ ->
                activity.finishAndRemoveTask()
            }
            .setPositiveButton(activity.getString(R.string.update)) { dialog, _ ->
                intentGooglePlay(activity)
                dialog.dismiss()
            }.show()
    }



    private fun intentGooglePlay(activity: Activity?) {
        if (activity != null) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data =
                Uri.parse("https://play.google.com/store/apps/details?id=" + activity.packageName)
            intent.setPackage("com.android.vending")
            activity.startActivity(intent)
            activity.finish()
        }
    }
}