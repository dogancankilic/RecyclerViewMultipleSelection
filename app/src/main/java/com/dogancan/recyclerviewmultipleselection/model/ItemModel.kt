package com.dogancan.recyclerviewmultipleselection.model

import androidx.annotation.DrawableRes
import com.dogancan.recyclerviewmultipleselection.ListAdapterItem

/**
 * Created by dogancan.kilic on 4/9/2022.
 */
data class ItemModel(
    @DrawableRes val image: Int,
    val title: String,
    override var id: Int
) : ListAdapterItem() {
    var isSelected = false

    fun toggleIsSelected(isSelected : Boolean) {
        this.isSelected = isSelected
    }

}

