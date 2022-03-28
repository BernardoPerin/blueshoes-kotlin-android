package bernardo.com.br.blueshoes.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.data.NavMenuItemsDataBase
import bernardo.com.br.blueshoes.domain.NavMenuItem
import bernardo.com.br.blueshoes.domain.User
import bernardo.com.br.blueshoes.util.NavMenuItemDetailsLookup
import bernardo.com.br.blueshoes.util.NavMenuItemKeyProvider
import bernardo.com.br.blueshoes.util.NavMenuItemPredicate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.nav_header_user_logged.*
import kotlinx.android.synthetic.main.nav_header_user_not_logged.*
import kotlinx.android.synthetic.main.nav_menu.*


class MainActivity :
    AppCompatActivity() {

    companion object {
        /**
         * Para debug em LogCat durante desenvolvimento do
         * projeto.
         **/
        const val LOG = "log-bs"

        const val FRAGMENT_ID = "frag-id"
        const val FRAGMENT_TAG = "frag-tag"
    }

    val user = User(
        "Bernardo Perin",
        R.drawable.user,
        true
    )

    lateinit var navMenuItems : List<NavMenuItem>
    lateinit var selectNavMenuItems : SelectionTracker<Long>

    lateinit var navMenuItemsLogged : List<NavMenuItem>
    lateinit var selectNavMenuItemsLogged : SelectionTracker<Long>
    lateinit var navMenu : NavMenuItemsDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        initNavMenu( savedInstanceState )

        initFragment()
    }

    private fun initNavMenu( savedInstanceState: Bundle? ){

        navMenu = NavMenuItemsDataBase(this)
        navMenuItems = navMenu.items
        navMenuItemsLogged = navMenu.itemsLogged

        showHideNavMenuViews()

        initNavMenuItems()
        initNavMenuItemsLogged()

        if( savedInstanceState != null ){
            selectNavMenuItems.onRestoreInstanceState( savedInstanceState )
            selectNavMenuItemsLogged.onRestoreInstanceState( savedInstanceState )
        }
        else{
            var fragId = intent.getIntExtra( FRAGMENT_ID, 0)
            if( fragId == 0 ){
                fragId =  R.id.item_all_shoes
            }

            selectNavMenuItems.select( fragId.toLong() )
        }
    }

    private fun showHideNavMenuViews(){
        if( user.status ){
            rl_header_user_not_logged.visibility = View.GONE

            fillUserHeaderNavMenu()
        }
        else{
            rl_header_user_logged.visibility = View.GONE
            v_nav_vertical_line.visibility = View.GONE
            rv_menu_items_logged.visibility = View.GONE
        }
    }

    private fun fillUserHeaderNavMenu(){
        if( user.status ){
            iv_user.setImageResource( user.image )
            tv_user.text = user.name
        }
    }

    private fun initNavMenuItems(){
        rv_menu_items.setHasFixedSize( false )
        rv_menu_items.layoutManager = LinearLayoutManager( this )
        rv_menu_items.adapter = NavMenuItemsAdapter( navMenuItems )

        initNavMenuItemsSelection()
    }

    private fun initNavMenuItemsSelection(){
        selectNavMenuItems = SelectionTracker.Builder<Long>(
            "id-selected-item",
            rv_menu_items,
            NavMenuItemKeyProvider( navMenuItems ),
            NavMenuItemDetailsLookup( rv_menu_items ),
            StorageStrategy.createLongStorage()
        )
        .withSelectionPredicate( NavMenuItemPredicate( this ) )
        .build()

        selectNavMenuItems.addObserver(
            SelectObserverNavMenuItems(
                {
                    selectNavMenuItemsLogged.selection.filter {
                        selectNavMenuItemsLogged.deselect( it )
                    }
                }
            )
        )

        (rv_menu_items.adapter as NavMenuItemsAdapter).selectionTracker = selectNavMenuItems
    }

    private fun initNavMenuItemsLogged(){
        rv_menu_items_logged.setHasFixedSize( false )
        rv_menu_items_logged.layoutManager = LinearLayoutManager( this )
        rv_menu_items_logged.adapter = NavMenuItemsAdapter( navMenuItemsLogged )

        initNavMenuItemsLoggedSelection()
    }

    private fun initNavMenuItemsLoggedSelection(){
        selectNavMenuItemsLogged = SelectionTracker.Builder<Long>(
            "id-selected-item-logged",
            rv_menu_items_logged,
            NavMenuItemKeyProvider( navMenuItemsLogged ),
            NavMenuItemDetailsLookup( rv_menu_items_logged ),
            StorageStrategy.createLongStorage()
        )
        .withSelectionPredicate( NavMenuItemPredicate( this ) )
        .build()

        selectNavMenuItemsLogged.addObserver(
            SelectObserverNavMenuItems(
                {
                    selectNavMenuItems.selection.filter {
                        selectNavMenuItems.deselect( it )
                    }
                }
            )
        )

        (rv_menu_items_logged.adapter as NavMenuItemsAdapter).selectionTracker = selectNavMenuItemsLogged
    }

    private fun initFragment(){
        var fragment = supportFragmentManager.findFragmentByTag( FRAGMENT_TAG )

        if( fragment == null ){
            var fragId = intent.getIntExtra( FRAGMENT_ID, 0)
            if( fragId == 0 ){
                fragId =  R.id.item_about
            }

            fragment = getFragment( fragId.toLong() )
        }

        replaceFragment( fragment )
    }

    private fun getFragment( fragmentId: Long): Fragment {

        return when (fragmentId) {
            R.id.item_about.toLong() -> AboutFragment()
            R.id.item_contact.toLong() -> ContactFragment()
            R.id.item_privacy_policy.toLong() -> PrivacyPolicyFragment()
            else -> AboutFragment()
        }
    }

    private fun replaceFragment( fragment: Fragment ){
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fl_fragment_container,
                fragment,
                FRAGMENT_TAG
            )
            .commit()
    }

    fun updateToolbarTitleInFragment( titleId: Int ){
        toolbar.title = getString( titleId )
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState)

        selectNavMenuItems.onSaveInstanceState( outState!! )
        selectNavMenuItemsLogged.onSaveInstanceState( outState )
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun callLoginActivity( view: View ){
        val intent = Intent( this, LoginActivity::class.java )
        startActivity( intent )
    }

    fun calSignUpActivity( view: View ){
        val intent = Intent( this, SignUpActivity::class.java )
        startActivity( intent )
    }

    inner class SelectObserverNavMenuItems(
        val callbackRemoveSelection: ()->Unit
    ) : SelectionTracker.SelectionObserver<Long>(){

        /*
         * Método responsável por permitir que seja possível
         * disparar alguma ação de acordo com a mudança de
         * status de algum item em algum dos objetos de seleção
         * de itens de menu gaveta. Aqui vamos proceder com
         * alguma ação somente em caso de item obtendo seleção,
         * para item perdendo seleção não haverá processamento,
         * pois este status não importa na lógica de negócio
         * deste método.
         * */
        override fun onItemStateChanged(
            key: Long,
            selected: Boolean ) {
            super.onItemStateChanged( key, selected )

            /*
             * Padrão Cláusula de Guarda para não seguirmos
             * com o processamento em caso de item perdendo
             * seleção. O processamento posterior ao condicional
             * abaixo é somente para itens obtendo a seleção,
             * selected = true.
             * */
            if( !selected ){
                return
            }

            if( isActivityItemCalledInMenu( key ) ){
                itemCallActivity(key, callbackRemoveSelection)
            }
            else {
                itemCallFragment(key, callbackRemoveSelection)
            }
        }
    }

    private fun itemCallActivity(
        key: Long,
        callbackRemoveSelection: ()->Unit) {

        callbackRemoveSelection()

        navMenu.saveIsActivityItemFired(
            this,
            true
        )

        lateinit var intent: Intent

        when (key) {
            R.id.item_settings.toLong() -> {
                intent = Intent(
                    this,
                    AccountSettingsActivity::class.java
                )

                intent.putExtra(User.Key, user)
            }
        }

        startActivity(intent)
    }

    private fun itemCallFragment(
        key: Long,
        callbackRemoveSelection: ()->Unit) {

        /*
        * Para garantir que somente um item de lista se
        * manterá selecionado, é preciso acessar o objeto
        * de seleção da lista de itens de usuário conectado
        * para então remover qualquer possível seleção
        * ainda presente nela. Sempre haverá somente um
        * item selecionado, mas infelizmente o método
        * clearSelection() não estava respondendo como
        * esperado, por isso a estratégia a seguir.
        * */
        callbackRemoveSelection()

        navMenu.saveLastSelectedItemFragmentID(
            this,
            key
        )

        if( navMenu.wasIsActivityItemFired( this ) ){
            val fragment = getFragment(key)
            replaceFragment(fragment)

            /*
             * Fechando o menu gaveta.
             * */
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else{
            navMenu.saveIsActivityItemFired(
                this,
                false
            )
        }
    }

    private fun isActivityItemCalledInMenu( key: Long )
        = when( key ){
            R.id.item_settings.toLong() -> true
            else -> false
    }

    override fun onResume() {
        super.onResume()

        if( navMenu.wasIsActivityItemFired( this ) ){
            selectNavMenuItems.select(
                navMenu.getLastSelectedItemFragmentID( this )
            )
        }
    }
}

