package com.jordiortuno.cleanmvvm.framework.firebaseTools

import android.content.pm.PackageManager
import com.jordiortuno.cleanmvvm.BuildConfig
import com.jordiortuno.cleanmvvm.framework.MyApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

interface CheckDatabaseListener {
    fun onCheckDatabaseFinish(canPass: Boolean)
}

interface ShowDialogRealtimeListener {
    fun showDialog(dialogType: DialogType)
}

enum class DialogType {
    UPDATE,
    MAINTENANCE
}

object LiveChecks {

    private enum class StateCheck {
        NON,
        CHECKED_FALSE,
        CHECKED_TRUE
    }

    private const val REALTIME_DATABASE_URL_ANDROID =
        ""
    private const val REALTIME_DATABASE_PLATFORM = "android"
    //TODO Define sons depending on firebase tree

    private const val TEST_UPDATE = ""
    private const val TEST_MAINTENANCE = ""
    private const val PRODUCTION_UPDATE = ""
    private const val PRODUCTION_MAINTENANCE = ""

    private val database = FirebaseDatabase.getInstance(REALTIME_DATABASE_URL_ANDROID)

    private var maintenanceState: StateCheck = StateCheck.NON
    private var updateState: StateCheck = StateCheck.NON
    private var checkDatabaseListener: CheckDatabaseListener? = null
    private var showDialogRealtimeListener: ShowDialogRealtimeListener? = null

    fun init(
        showDialogRealtimeListener: ShowDialogRealtimeListener,
        checkDatabaseListener: CheckDatabaseListener? = null
    ) {
        checkDatabaseListener?.let { setListenerRealtimeDatabase(checkDatabaseListener) }
        setShowDialogRealtime(showDialogRealtimeListener)
        setUpdateListener()
        setMaintenanceListener()
    }

    private fun checkStates() {
        if (maintenanceState != StateCheck.NON && updateState != StateCheck.NON) {
            checkStateDatabase()
        }
    }

    private fun checkStateDatabase() {
        when (MyApp.appStatus) {
            MyApp.Environment.DEVELOPMENT -> {
                canPass()
            }
            MyApp.Environment.TEST -> {
                if (maintenanceState == StateCheck.CHECKED_TRUE) {
                    noCanPass()
                } else if (maintenanceState == StateCheck.CHECKED_FALSE) {
                    canPass()
                }
            }
            MyApp.Environment.PRODUCTION -> {
                if (maintenanceState == StateCheck.CHECKED_TRUE || updateState == StateCheck.CHECKED_TRUE) {
                    noCanPass()
                } else if (maintenanceState == StateCheck.CHECKED_FALSE && updateState == StateCheck.CHECKED_FALSE) {
                    canPass()
                }
            }
        }
    }

    // Updates
    private fun setUpdateListener() {
        val myRef = database.reference
        when (MyApp.appStatus) {
            MyApp.Environment.DEVELOPMENT -> {
                //No llama a checkDatabase porque ya lo hace maintenance. Al no comprobar con firebase el flujo es sincrono, entonces se llamaria dos veces.
            }
            MyApp.Environment.TEST -> {
                //Test no tiene listener de actualizacion
            }
            MyApp.Environment.PRODUCTION -> {
                val update = myRef.child(REALTIME_DATABASE_PLATFORM).child("TODO")
                updateState = StateCheck.NON
                update.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val value = dataSnapshot.value.toString().toInt()
                        try {
                            val versionCode = BuildConfig.VERSION_CODE
                            updateState = if (versionCode < value) {
                                if (maintenanceState != StateCheck.CHECKED_TRUE) {
                                    showUpdateDialog()
                                }
                                StateCheck.CHECKED_TRUE
                            } else {
                                StateCheck.CHECKED_FALSE
                            }

                            checkStates()
                        } catch (e: PackageManager.NameNotFoundException) {
                            e.printStackTrace()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {}
                })
            }
        }
    }

    // Maintenance
    private fun setMaintenanceListener() {
        val myRef = database.reference
        when (MyApp.appStatus) {
            MyApp.Environment.DEVELOPMENT -> {
                checkStateDatabase()
            }
            MyApp.Environment.TEST -> {
                val maintenance =
                    myRef.child(REALTIME_DATABASE_PLATFORM).child("")

                maintenanceState = StateCheck.NON
                maintenance.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val value: Boolean = dataSnapshot.value as Boolean
                        maintenanceState = if (value) {
                            showMaintenanceDialog()
                            StateCheck.CHECKED_TRUE
                        } else {
                            StateCheck.CHECKED_FALSE
                        }
                        checkStateDatabase()
                    }

                    override fun onCancelled(error: DatabaseError) {}
                })
            }
            MyApp.Environment.PRODUCTION -> {
                val maintenance =
                    myRef.child(REALTIME_DATABASE_PLATFORM).child("")

                maintenanceState = StateCheck.NON
                maintenance.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val value: Boolean = dataSnapshot.value as Boolean
                        maintenanceState = if (value) {
                            showMaintenanceDialog()
                            StateCheck.CHECKED_TRUE
                        } else {
                            StateCheck.CHECKED_FALSE
                        }

                        checkStates()
                    }

                    override fun onCancelled(error: DatabaseError) {}
                })
            }
        }

    }

    private fun canPass() {
        canPass(true)
    }

    private fun noCanPass() {
        canPass(false)
    }

    private fun canPass(boolean: Boolean) {
        checkDatabaseListener?.onCheckDatabaseFinish(boolean)
    }

    private fun showUpdateDialog() {
        showDialog(DialogType.UPDATE)
    }

    private fun showMaintenanceDialog() {
        showDialog(DialogType.MAINTENANCE)
    }

    private fun showDialog(dialogType: DialogType) {
        showDialogRealtimeListener?.showDialog(dialogType)
    }

    private fun setListenerRealtimeDatabase(checkDatabaseListener: CheckDatabaseListener) {
        this.checkDatabaseListener = checkDatabaseListener
    }

    fun setShowDialogRealtime(showDialogRealtimeListener: ShowDialogRealtimeListener) {
        this.showDialogRealtimeListener = showDialogRealtimeListener
    }
}