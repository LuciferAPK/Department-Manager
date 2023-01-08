package com.example.departmentmanager.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.view.isVisible
import com.example.departmentmanager.application.ApplicationContext
import com.example.departmentmanager.data.model.Employee
import com.example.departmentmanager.databinding.LayoutItemFunctionMenuBinding
import kotlinx.android.synthetic.main.layout_item_function_menu.view.*

class ItemFunctionAdapter(val context: Context, val onClickItem:(Int)-> Unit) : BaseAdapter() {
    private val items = ApplicationContext.functions
    private lateinit var binding : LayoutItemFunctionMenuBinding
    private var employee : Employee ?= null

    fun setEmployee(employee: Employee?){
        this.employee = employee
    }

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
        items[position].image?.let { binding.imgFunction.setImageResource(it) }
        when(employee?.position){
            "HR" -> {
                if(position == 2)
                    binding.viewEnabled.visibility = View.VISIBLE
            }
            "LEADER" ->{
                if(position == 0 || position ==1)
                    binding.viewEnabled.visibility = View.VISIBLE
            }
            "STAFF" ->{
                if(position == 0 || position ==1 || position ==2)
                    binding.viewEnabled.visibility = View.VISIBLE
            }
        }
        binding.root.isEnabled = !binding.viewEnabled.isVisible
        binding.root.setOnClickListener {
            onClickItem.invoke(position)
        }
        return binding.root
    }
}