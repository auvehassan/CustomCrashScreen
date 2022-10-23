##CustomCrashScreen
 
 - ***MainAcrivity.kt*** that throws an error(crash) by a button click
 
 - ***CrashActivity.kt*** appears whenever application crash event appears
 
 - ***ApplicationExceptionHandler*** that handles application crashes that extended by ***Thread.UncaughtExceptionHandler*** class.
     - ***launchActivity*** function starts the ***activityToBeLaunched*** whenever our application crashes
     - ***Flags(FLAG_ACTIVITY_CLEAR_TOP, FLAG_ACTIVITY_NEW_TASK, FLAG_ACTIVITY_CLEAR_TASK)*** are used in Intent to clear activities backstack
     - ***uncaughtException*** method calls ***defaultHandler.uncaughtException(p0, p1)*** whenever any kind of exception occurs while starting the Activity, 
        it'll show default error dialog.
     - ***initialize*** function has to be called at ***OnCreate*** function at Application class that will replace the default ExceptionHandler to our class.
     - ***getThrowableFromIntent*** will retrive Throwable data from the activity we’re going to use.
 
  - ***ThisApplication.kt*** will be using to initialize ***ApplicationExceptionHandler***
 
  - Separate the Main Application process with the CrashActivity by adding ***android:process=".error_handler"*** in manifest activity tag.
     This will make CrashActivity safe from error to show after application crash.
