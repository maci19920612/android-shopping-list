package team.maci.shopping.list.util

import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.recyclerview.widget.RecyclerView

@BindingMethods(value = [
    BindingMethod(
        type = RecyclerView::class,
        attribute = "app:adapter",
        method = "setAdapter"
    )
])
object RecyclerViewAdapterBinder