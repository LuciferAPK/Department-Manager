package com.example.departmentmanager.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.departmentmanager.application.ApplicationContext
import com.example.departmentmanager.databinding.LayoutItemFunctionMenuBinding

class ItemFunctionAdapter(val context: Context, val onClickItem:()-> Unit) : BaseAdapter() {
    private val items = ApplicationContext.functions
    private lateinit var binding : LayoutItemFunctionMenuBinding
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(p0: Int): Any {
        return items[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, p1: View?, viewGroup: ViewGroup?): View {
        val inflater = LayoutInflater.from(viewGroup?.context)
        binding = LayoutItemFunctionMenuBinding.inflate(inflater, viewGroup, false)
        binding.tvNameFunction.text = items[position].title

        binding.root.setOnClickListener {
            onClickItem.invoke()
        }
        return binding.root
    }
}