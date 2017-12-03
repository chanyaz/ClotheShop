package com.valgood.clotheshop

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import co.zsmb.materialdrawerkt.builders.accountHeader
import co.zsmb.materialdrawerkt.builders.drawer
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import co.zsmb.materialdrawerkt.draweritems.profile.profile
import co.zsmb.materialdrawerkt.draweritems.sectionHeader
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.valgood.clotheshop.adapter.MainTabLayoutAdapter
import com.valgood.clotheshop.utils.FontCache
import kotlinx.android.synthetic.main.main_option_tabs.*
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import co.zsmb.materialdrawerkt.draweritems.badge
import com.mikepenz.iconics.IconicsDrawable
import com.valgood.clotheshop.utils.CustomFontUtils
import com.valgood.clotheshop.utils.ImageLoading
import kotlinx.android.synthetic.main.toolbar.*
import com.mikepenz.fontawesome_typeface_library.FontAwesome
import net.hockeyapp.android.CrashManager
import net.hockeyapp.android.UpdateManager

class MainActivity : AppCompatActivity() {

    private lateinit var result: Drawer
    private lateinit var headerResult: AccountHeader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_option_tabs)
        setupToolbar()
        setupDrawer(savedInstanceState)
        setupMainTabViewPager()
        checkForUpdates()
    }

    override fun onResume() {
        super.onResume()
        checkForCrashes()
    }

    override fun onPause() {
        super.onPause()
        unregisterManagers()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterManagers()
    }

    fun checkForCrashes() {
        CrashManager.register(this)
    }

    fun checkForUpdates() {
        // Remove this for store builds!
        UpdateManager.register(this)
    }

    fun unregisterManagers() {
        UpdateManager.unregister()
    }

    private fun setupDrawer(savedInstanceState: Bundle?) {
        val textColorSection = R.color.lightOrange
        val typeFaceSection = FontCache[getString(R.string.roboto_medium), this@MainActivity] as Typeface
        val typeFaceItems = FontCache[getString(R.string.roboto_regular), this@MainActivity] as Typeface
        val textColorItems = R.color.colorAccent

        result = drawer {
            toolbar = this@MainActivity.toolbar
            hasStableIds = true
            savedInstance = savedInstanceState

            headerResult = accountHeader {
                background = R.color.colorBackground
                textColorRes = R.color.colorAccent
                savedInstance = savedInstanceState
                translucentStatusBar = true
                customViewRes = R.layout.material_drawer_header

                profile("Jorge Valbuena", "jorgevalbuena56@gmail.com") {
                    identifier = 100
                }.withTypeface(FontCache[getString(R.string.roboto_regular), this@MainActivity])
            }


            sectionHeader(getString(R.string.item_category)) {
                textColorRes = textColorSection
                typeface = typeFaceSection
                divider = false
            }

            primaryItem(getString(R.string.item_women)) {
                iicon = GoogleMaterial.Icon.gmd_female
                typeface = typeFaceItems
                textColorRes = textColorItems
                selectedTextColorRes = textColorItems
                selectedIconColorRes = textColorItems
                //onClick(openActivity(ProductByFeatureActivity::class))
            }
            primaryItem(getString(R.string.item_men)) {
                iicon = GoogleMaterial.Icon.gmd_male
                typeface = typeFaceItems
                textColorRes = textColorItems
                selectedTextColorRes = textColorItems
                selectedIconColorRes = textColorItems
                //onClick(openActivity(AccountHeaderActivity::class))
            }
            primaryItem(getString(R.string.item_kids)) {
                iicon = GoogleMaterial.Icon.gmd_male_female
                typeface = typeFaceItems
                textColorRes = textColorItems
                selectedTextColorRes = textColorItems
                selectedIconColorRes = textColorItems
                //onClick(openActivity(HeaderFooterActivity::class))
            }

            sectionHeader(getString(R.string.item_you)) {
                textColorRes = textColorSection
                typeface = typeFaceSection
                divider = false
            }

            primaryItem(getString(R.string.item_wish_list)) {
                iicon = GoogleMaterial.Icon.gmd_star
                typeface = typeFaceItems
                textColorRes = textColorItems
                selectedTextColorRes = textColorItems
                selectedIconColorRes = textColorItems
                badge("10") {
                    cornersDp = 30
                    color = 0xFFEE7E33
                    textColorRes = R.color.colorPrimary
                }
                //onClick(openActivity(DrawerItemTypesActivity::class))
            }
            primaryItem(getString(R.string.item_last_seen)) {
                iicon = GoogleMaterial.Icon.gmd_view_list
                typeface = typeFaceItems
                textColorRes = textColorItems
                selectedTextColorRes = textColorItems
                selectedIconColorRes = textColorItems
                //onClick(openActivity(AccountHeaderActivity::class))
            }
            primaryItem(getString(R.string.item_orders)) {
                iicon = GoogleMaterial.Icon.gmd_calendar_note
                typeface = typeFaceItems
                textColorRes = textColorItems
                selectedTextColorRes = textColorItems
                selectedIconColorRes = textColorItems
                badge("5") {
                    cornersDp = 30
                    color = 0xFFEE7E33
                    textColorRes = R.color.colorPrimary
                }
                //onClick(openActivity(HeaderFooterActivity::class))
            }
        }
        result.header.findViewById<ImageView>(R.id.material_drawer_account_header_text_switcher).visibility = View.GONE
        result.header.findViewById<LinearLayout>(R.id.material_drawer_account_header_text_section).setPadding(0,0,0,0)

        result.header.findViewById<ImageView>(R.id.material_drawer_account_header_small_first).apply {
            visibility = View.VISIBLE
            background = IconicsDrawable(this@MainActivity).icon(GoogleMaterial.Icon.gmd_settings)
        }

        result.header.findViewById<TextView>(R.id.material_drawer_account_header_name).apply {
            setTextColor(ContextCompat.getColor(this@MainActivity, R.color.colorAccent))
        }
        result.header.findViewById<TextView>(R.id.material_drawer_account_header_email).apply {
            setTextColor(ContextCompat.getColor(this@MainActivity, R.color.lightGrey))
        }
        ImageLoading.loadPicture("https://avatars3.githubusercontent.com/u/7516423?v=3&s=460",
                   result.header.findViewById<ImageView>(R.id.material_drawer_account_header_current))

    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = CustomFontUtils.
                    formatTitleBar(this@MainActivity,
                                    getString(R.string.item_women),
                                    getString(R.string.roboto_medium))
            setDisplayHomeAsUpEnabled(false)
        }
    }

    private fun setupMainTabViewPager() {
        val adapter = MainTabLayoutAdapter(this)
        pager.adapter = adapter

        tabs.setupWithViewPager(pager)

        // Needed to be able to change pages when tapping on any tab
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        result.saveInstanceState(outState)
        headerResult.saveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        //Get a reference to your item by id
        val item = menu.findItem(R.id.action_category)

        val rootView = item.actionView as ImageView
        rootView.setImageBitmap(IconicsDrawable(this).icon(FontAwesome.Icon.faw_tags).toBitmap())
        return true
    }

    override fun onBackPressed() {
        if (result.isDrawerOpen)
            result.closeDrawer()
        else
            super.onBackPressed()
    }
}
