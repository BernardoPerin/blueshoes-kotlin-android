package bernardo.com.br.blueshoes.view.config

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import bernardo.com.br.blueshoes.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_tabs_user_config.*

abstract class ConfigFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs_user_config)
        setSupportActionBar( toolbar )

        supportActionBar?.setDisplayHomeAsUpEnabled( true )
        supportActionBar?.setDisplayShowHomeEnabled( true )

        window.setBackgroundDrawableResource( R.drawable.bg_activity )

        val sectionsPagerAdapter = getSectionsAdapter()
        view_pager.adapter = sectionsPagerAdapter

        tabs.setupWithViewPager(view_pager)
    }

    override fun onOptionsItemSelected( item: MenuItem ): Boolean {
        if ( item.itemId == android.R.id.home ) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    abstract fun getSectionsAdapter() : ConfigSectionsAdapter

}