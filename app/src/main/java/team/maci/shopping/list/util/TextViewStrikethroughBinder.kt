package team.maci.shopping.list.util

import android.graphics.Paint
import android.widget.TextView
import androidx.databinding.BindingAdapter

object TextViewStrikethroughBinder{
    @BindingAdapter("strikethrough")
    fun bindStriketrough(target: TextView, isStrikethroughSpan: Boolean){
        if(isStrikethroughSpan){
            target.paintFlags = target.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            target.paintFlags = target.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}