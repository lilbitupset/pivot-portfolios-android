package com.example.pivotportfolios

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_themes.*
import kotlinx.android.synthetic.main.home_menu_item.view.*

class HomeFragment : Fragment() {

    //______________Variables For Recycler View____________________
    private val homeMenuItemAdapter = GroupAdapter<GroupieViewHolder>()

    //here we are adding items to the recycler view using the adapter we created to use images as buttons in a list
    var displayItems: ArrayList<HomeMenuItem> = ArrayList()
        set(value) {
            homeMenuItemAdapter.clear()

            for (sectionItem: HomeMenuItem in value) {
                val imageButton = HomeMenuAdapter(sectionItem)
                homeMenuItemAdapter.add(imageButton)
            }
            field = value
        }
    //_____________________________________________________________

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //items to add with the view, view does not exist at this point
        homeMenuItemAdapter.setOnItemClickListener { item, view ->
            var selectedID = (item as HomeMenuAdapter).itemID
            when (selectedID) {
                0 -> {
                    item1Action()
                }
                1 -> {
                    item2Action()
                }
            }
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_themes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //view is now created and can be accessed
        super.onViewCreated(view, savedInstanceState)
        //here we will add the recycler view items
        ThemesRecyclerView.apply {
            ThemesRecyclerView.layoutManager = LinearLayoutManager(context)
            ThemesRecyclerView.adapter = homeMenuItemAdapter
        }
        //put functional code here for function calls, etc.
        getMenuItems()


//        view.findViewById<Button>(R.id.button_first).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }

    }


    fun getMenuItems() {
        val image1: Drawable? =
            context?.let { ResourcesCompat.getDrawable(it.resources, R.drawable.image1, null) }
        val image2: Drawable? =
            context?.let { ResourcesCompat.getDrawable(it.resources, R.drawable.image2, null) }
        val image3: Drawable? =
            context?.let { ResourcesCompat.getDrawable(it.resources, R.drawable.image3, null) }
        val image4: Drawable? =
            context?.let { ResourcesCompat.getDrawable(it.resources, R.drawable.image4, null) }
        val image5: Drawable? =
            context?.let { ResourcesCompat.getDrawable(it.resources, R.drawable.image5, null) }
        //create home menu items
        var homeMenuItems = ArrayList<HomeMenuItem>()
        val item1 = HomeMenuItem(
            "Envision",
            0, image1
        )
        val item2 = HomeMenuItem(
            "Creator",
            1,
            image2
        )
        val item3 = HomeMenuItem(
            "Focus",
            2,
            image3
        )
        val item4 = HomeMenuItem(
            "Revive",
            3,
            image4
        )
        val item5 = HomeMenuItem(
            "Geometric",
            4,
            image5
        )

        //add home menu items to an array list
        homeMenuItems.add(item1)
        homeMenuItems.add(item2)
        homeMenuItems.add(item3)
        homeMenuItems.add(item4)
        homeMenuItems.add(item5)

        //pass array list to displayItems to pass through Adapter
        displayItems = homeMenuItems
    }

    fun item1Action() {

        (activity as Activity).findNavController(R.id.nav_host_fragment)
            .navigate(R.id.action_HomeFragment_to_SecondFragment)

    }

    fun item2Action() {

//        (activity as Activity).findNavController(R.id.nav_host_fragment)
//            .navigate(R.id.action_homeFragment_to_distributorLocatorFragment)

    }
}

class HomeMenuAdapter(private val item: HomeMenuItem) : Item() {
    val itemID = item.id
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        //this this a function to add item properties to the recycler view, in this case I just want the image
        viewHolder.itemView.homeMenuImageView.setImageDrawable(item.image)
        viewHolder.itemView.themeTitle.text = item.title
    }

    override fun getLayout(): Int {
        return R.layout.home_menu_item
    }

}


class HomeMenuItem(var title: String, var id: Int, var image: Drawable?) {

}