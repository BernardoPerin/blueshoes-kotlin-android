package bernardo.com.br.blueshoes.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.data.AccountSettingsItemsDataBase
import bernardo.com.br.blueshoes.domain.User
import bernardo.com.br.blueshoes.util.isValidEmail
import bernardo.com.br.blueshoes.util.validate
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.content_account_settings.*
import kotlinx.android.synthetic.main.content_forgot_password.*
import kotlinx.android.synthetic.main.info_block.*

class AccountSettingsActivity :
    AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_account_settings )
        setSupportActionBar( toolbar )

        supportActionBar?.setDisplayHomeAsUpEnabled( true )
        supportActionBar?.setDisplayShowHomeEnabled( true )

        val user = intent.getParcelableExtra<User>(User.Key)

        tv_user_connected.text = String.format(
            "%s %s",
            getString(R.string.connected),
            user.name
        )

        initItems()
    }

    override fun onOptionsItemSelected( item: MenuItem): Boolean {
        if ( item.itemId == android.R.id.home ) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initItems(){
        rv_account_settings_items.setHasFixedSize( true )

        val layoutManager = LinearLayoutManager(this)
        rv_account_settings_items.layoutManager = layoutManager

        val divider = DividerItemDecoration(
            this,
            layoutManager.orientation
        )
        divider.setDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.light_grey_divider
            )!!
        )
        rv_account_settings_items.addItemDecoration( divider )

        rv_account_settings_items.adapter = AccountSettingsItemsAdapter(
            AccountSettingsItemsDataBase.getItems( this )
        )
    }
}
