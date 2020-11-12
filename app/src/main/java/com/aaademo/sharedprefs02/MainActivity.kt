package com.aaademo.sharedprefs02

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var loggingTag = getString(R.string.LOGGING_TAG)
        Log.i(loggingTag, getString(R.string.MainActivityLaunchMessage))


        btnSetPreferences.setOnClickListener {view ->
            Log.i(loggingTag, getString(R.string.ButtonClicked))

            var sharedPreferences = getSharedPreferences(getString(R.string.PrefsPackage), Context.MODE_PRIVATE)

            var todoItems = sharedPreferences.getStringSet(getString(R.string.ToDoItemStrings), setOf())?.toMutableSet()

            var sampleToDoItem = getString(R.string.SampleItem) + LocalDateTime.now()

            Log.i(loggingTag,sampleToDoItem)

            todoItems?.add(sampleToDoItem)

            sharedPreferences.edit().putStringSet(getString(R.string.ToDoItemStrings),todoItems).apply()

            Log.i(loggingTag, getString(R.string.SAVED_MESSAGE))

        }

        btnLoadPreferences.setOnClickListener { view ->
            Log.i(loggingTag, getString(R.string.ButtonClicked))
            var sharedPreferences = getSharedPreferences(getString(R.string.PrefsPackage), Context.MODE_PRIVATE)

            // List of string items
            var todoItems = sharedPreferences.getStringSet(getString(R.string.ToDoItemStrings), setOf())?.toMutableSet()

            Log.i(loggingTag, "the set contains ${todoItems?.size} elements")

            todoItems?.forEach { currentTodoItem -> Log.i(loggingTag, currentTodoItem) }

        }

    }
}
