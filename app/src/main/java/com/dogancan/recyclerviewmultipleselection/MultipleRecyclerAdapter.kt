package com.dogancan.recyclerviewmultipleselection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.dogancan.recyclerviewmultipleselection.databinding.ListItemBinding
import com.dogancan.recyclerviewmultipleselection.model.ItemModel

/**
 * Created by dogancan.kilic on 4/9/2022.
 */
class MultipleRecyclerAdapter(private val lifecycleOwner: LifecycleOwner) :
    BaseListAdapter<ItemModel, ListItemBinding>(
        ListAdapterItemDiffCallback()
    ) {

    private var isSelectedMode: MutableLiveData<Boolean> = MutableLiveData(false)

    var itemClickListener: ((item: ItemModel, isSelectedMode: Boolean) -> Unit)? = null

    override fun bind(binding: ListItemBinding, item: ItemModel, itemPosition: Int) {
        binding.ivSample.setImageResource(item.image)
        isSelectedMode.observe(lifecycleOwner) {
            if (it) {
                binding.ivSample.imageAlpha = 140
                binding.chMaterial.visibility = View.VISIBLE
                binding.chMaterial.isChecked = item.isSelected
            } else {
                item.toggleIsSelected(false)
                binding.chMaterial.isChecked = false
                binding.ivSample.imageAlpha = 255
                binding.chMaterial.visibility = View.GONE
            }
        }

        binding.root.setOnClickListener {
            if (isSelectedMode.value == true && item.isSelected.not()) {
                item.toggleIsSelected(true)
                binding.chMaterial.isChecked = true


            } else if (isSelectedMode.value == true && item.isSelected) {
                item.toggleIsSelected(false)
                binding.chMaterial.isChecked = false

            }

            isSelectedMode.value?.let { it1 -> itemClickListener?.invoke(item, it1) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ListItemBinding> {
        val binding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BaseViewHolder(binding)
    }

    fun selectionMode(isSelectionMode: Boolean) {
        this.isSelectedMode.value = isSelectionMode

    }


}