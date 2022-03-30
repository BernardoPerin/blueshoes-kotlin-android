package bernardo.com.br.blueshoes.view.config


import bernardo.com.br.blueshoes.view.FormFragment


abstract class ConfigFormFragment :
    FormFragment() {

    abstract fun title() : Int
}
