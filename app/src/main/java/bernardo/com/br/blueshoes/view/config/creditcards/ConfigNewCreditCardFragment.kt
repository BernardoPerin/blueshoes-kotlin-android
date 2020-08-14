package bernardo.com.br.blueshoes.view.config.creditcards


import android.os.Bundle
import android.view.View

import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.util.isValidCNPJ
import bernardo.com.br.blueshoes.util.isValidCPF
import bernardo.com.br.blueshoes.view.FormFragment
import com.santalu.maskedittext.MaskEditText
import kotlinx.android.synthetic.main.fragment_config_new_credit_card.*


class ConfigNewCreditCardFragment :
    FormFragment(),
    View.OnFocusChangeListener {

    companion object{
        const val TAB_TITLE = R.string.config_credit_cards_tab_new
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        updateFlFormToFullFreeScreen()

        bt_add_credit_card.setOnClickListener {
            mainAction()
        }

        et_credit_card_security_code.setOnEditorActionListener( this )

        met_credit_card_owner_reg.setOnFocusChangeListener(this)
    }

    override fun getLayoutResourceID()
        = R.layout.fragment_config_new_credit_card

    override fun backEndFakeDelay() {
        backEndFakeDelay(
            false,
            getString(R.string.invalid_credit_card)
        )
    }

    override fun blockFields(status: Boolean) {
        met_credit_card_number.isEnabled = !status
        sp_credit_card_enterprise.isEnabled = !status
        et_credit_card_owner_name.isEnabled = !status
        met_credit_card_owner_reg.isEnabled = !status
        sp_credit_card_expiry_month.isEnabled = !status
        et_credit_card_expiry_year.isEnabled = !status
        et_credit_card_security_code.isEnabled = !status
        bt_add_credit_card.isEnabled = !status
    }

    override fun isMainButtonSending(status: Boolean) {
        bt_add_credit_card.text = if( status )
            getString(R.string.add_credit_card_going)
        else
            getString(R.string.add_credit_card)
    }

    override fun onFocusChange(
        view: View,
        hasFocus: Boolean) {

        var mask = ""
        val editText = view as MaskEditText

        val pattern = Regex("[^0-9]")
        val content = editText.text.toString().replace( pattern, "")

        if( !hasFocus ){
            if( content.isValidCPF() ){
                mask = "###.###.###-##"
            }
            else if( content.isValidCNPJ() ){
                mask = "##.###.###/####.##"
            }
        }

        editText.mask = mask

        editText.setText( content )
    }

}
