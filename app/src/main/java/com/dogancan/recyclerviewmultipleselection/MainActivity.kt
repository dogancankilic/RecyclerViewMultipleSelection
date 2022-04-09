package com.dogancan.recyclerviewmultipleselection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.dogancan.recyclerviewmultipleselection.ext.toast
import com.dogancan.recyclerviewmultipleselection.model.ItemModel
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val rvAdapter: MultipleRecyclerAdapter by lazy {
        MultipleRecyclerAdapter(this)
    }
    private val items: MutableList<ItemModel> = mutableListOf()
    private val selectedItems: MutableList<ItemModel> = mutableListOf()
    private var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recView = findViewById<RecyclerView>(R.id.rvItems)
        val switchMode = findViewById<SwitchMaterial>(R.id.swRv)

        rvAdapter.itemClickListener = { item, isSelectedMode ->
            if (isSelectedMode && item.isSelected) {
                selectedItems.add(item)
                toast(item.title.plus(" is selected"))
            } else if (isSelectedMode && item.isSelected.not()) {
                toast(item.title.plus(" is deselected"))
                selectedItems.remove(item)
            }

        }

        repeat(20) {
            items.add(ItemModel(R.drawable.galata, "${Random.nextInt(0, 999)}", id++))
        }

        recView.adapter = rvAdapter
        rvAdapter.submitList(items)
        switchMode.setOnCheckedChangeListener { _, isChecked ->
            rvAdapter.selectionMode(isChecked)
            if (isChecked.not()) selectedItems.clear()
        }
    }
}