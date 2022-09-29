***CustomCrashScreen***
  01. MainAcrivity.kt that throws an error(crash) by a button click
  02. CrashActivity.kt appears whenever application crash event appears
  03. ApplicationExceptionHandler that handles application crashes that extended by Thread.UncaughtExceptionHandler class.
    01. launchActivity function starts the activityToBeLaunched whenever our application crashes
    02. Flags(FLAG_ACTIVITY_CLEAR_TOP, FLAG_ACTIVITY_NEW_TASK, FLAG_ACTIVITY_CLEAR_TASK) are used in Intent to clear activities backstack
    03. uncaughtException method calls defaultHandler.uncaughtException(p0, p1) whenever any kind of exception occurs while starting the Activity, it'll show default             error dialog.
    04. initialize function has to be called at OnCreate function at Application class that will replace the default ExceptionHandler to our class.
    05. getThrowableFromIntent will retrive Throwable data from the activity we’re going to use.
 04. ThisApplication.kt will be using to initialize ApplicationExceptionHandler
 05. Separate the Main Application process with the CrashActivity by adding android:process=".error_handler" in manifest activity tag.
     This will make CrashActivity safe from error to show after application crash.
