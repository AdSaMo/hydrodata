package com.logocito.atlas.ui

import android.view.MotionEvent
import android.view.View

class OnSwipeListener (val id : String) : View.OnTouchListener {
    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        //TODO("Not yet implemented")
        return true
    }
    fun onSwipeRight(){
        println(this.id)
    }
}