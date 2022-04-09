package com.dogancan.recyclerviewmultipleselection.ext

import android.content.Context
import android.widget.Toast

/**
 * Created by dogancan.kilic on 4/9/2022.
 */
inline fun Context.toast(message:String){
    Toast.makeText(this, message , Toast.LENGTH_SHORT).show()
}