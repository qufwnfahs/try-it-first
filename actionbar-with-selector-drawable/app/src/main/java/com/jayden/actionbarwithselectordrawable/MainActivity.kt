package com.jayden.actionbarwithselectordrawable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.get
import com.jayden.actionbarwithselectordrawable.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var optionMenu: Menu? = null

    private var isFavored = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.test_actions, menu)
        optionMenu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite -> {
                isFavored = !isFavored
                refreshMenuItems()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun refreshMenuItems() {
        val optionMenu = optionMenu ?: return

        val actionFavorite = optionMenu.findItem(R.id.action_favorite) ?: return

        actionFavorite.icon.level =
            if (isFavored) ICON_LEVEL_IS_FAVORED else ICON_LEVEL_IS_NOT_FAVORED
    }

    companion object {
        const val ICON_LEVEL_IS_FAVORED = 1
        const val ICON_LEVEL_IS_NOT_FAVORED = 2
    }
}