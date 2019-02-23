package team.maci.shopping.list.util

import android.view.View
import androidx.databinding.BindingAdapter

object ViewOnTouchListenerBinder {

    @JvmStatic
    @BindingAdapter("onTouch")
    fun bindOnTouchListener(target: View, listener: View.OnTouchListener?) {
        target.setOnTouchListener(listener)
    }
}