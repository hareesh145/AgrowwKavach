package com.ak.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.ak.R
import com.bumptech.glide.Glide

class AutoScrollPagerAdapter(
    private val context: Context,
    private val list: ArrayList<Int>
) : PagerAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.home_scroll_image_item, container, false)

        val introImage: ImageView = view.findViewById(R.id.main_item_image)


        val listItems = list[position]
        introImage.visibility = View.VISIBLE
        Glide.with(context).load(listItems).into(introImage)
//        if (listItems != null && listItems.imageUrl != null)
//            Glide.with(context).load(listItems.imageUrl).into(introImage)


        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}