package team.maci.shopping.list.util

import androidx.databinding.Observable

class PropertyWatcher(private val block: () -> Unit) : Observable.OnPropertyChangedCallback(){
    var enabled = true
    override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
        if(enabled){
            block()
        }
    }
}