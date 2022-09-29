package com.auvehassan.customerrorscreen

import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.gson.Gson
import kotlin.system.exitProcess

/**
 * [applicationContext]: Required for launching the activity.
 * [defaultHandler]:
 * If there any kind of error occurs while launching the [activityToBeLaunched]
 * default [Thread.UncaughtExceptionHandler] will be triggered. As a result
 * Android's default Crash Dialog will be show up.
 *
 * [activityToBeLaunched]: The activity to be launched,
 * it will put Exception data as JSON string. It can be
 * easily converted back to [Throwable] class by
 * Gson().fromJSON(str,Throwable::class.java) function.
 */

class ApplicationExceptionHandler private constructor(
    private val applicationContext: Context,
    private val defaultHandler: Thread.UncaughtExceptionHandler,
    private val activityToBeLaunched: Class<*>
) : Thread.UncaughtExceptionHandler {

    override fun uncaughtException(p0: Thread, p1: Throwable) {
        try {
            launchActivity(applicationContext, activityToBeLaunched, p1)
            exitProcess(0)
        } catch (e: Exception) {
            defaultHandler.uncaughtException(p0, p1)
        }
    }

    private fun launchActivity( applicationContext: Context, activity: Class<*>, exception: Throwable) {
        val crashedIntent = Intent(applicationContext, activity).also {
            it.putExtra(INTENT_DATA_NAME, JsonFormatter.toJson(exception))
        }

        // Clears all previous activities. So backstack will be gone
        crashedIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        crashedIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        applicationContext.startActivity(crashedIntent)
    }

    companion object {
        private const val INTENT_DATA_NAME = "CrashData"
        private const val TAG = "CustomExceptionHandler"

        fun initialize(
            applicationContext: Context,
            activityToBeLaunched: Class<*>
        ) {
            val handler = ApplicationExceptionHandler(
                applicationContext,
                Thread.getDefaultUncaughtExceptionHandler() as Thread.UncaughtExceptionHandler,
                activityToBeLaunched
            )
            Thread.setDefaultUncaughtExceptionHandler(handler)
        }

        /**
         * Gets throwable data from activity's intent.
         * It'll return null if stringExtra has not been found or another reasons.
         */
        fun getThrowableFromIntent(intent: Intent): Throwable? {
            return try {
                Gson().fromJson(intent.getStringExtra(INTENT_DATA_NAME), Throwable::class.java)
            } catch (e: Exception) {
                Log.e(TAG, "getThrowableFromIntent: ", e)
                null
            }
        }
    }
}