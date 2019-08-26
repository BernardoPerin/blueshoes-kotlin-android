package bernardo.com.br.blueshoes.view


import android.os.Bundle
import android.text.SpannedString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import bernardo.com.br.blueshoes.R


class PrivacyPolicyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater
            .inflate(
                R.layout.fragment_privacy_policy,
                container,
                false)
    }

    private fun privacyPolicyLoad(){
        val text = getText( R.string.privacy_policy_content ) as SpannedString
    }

    override fun onResume() {
        super.onResume()

        (activity as MainActivity)
            .updateToolbarTitleInFragment( R.string.privacy_policy_frag_title )
    }

}
