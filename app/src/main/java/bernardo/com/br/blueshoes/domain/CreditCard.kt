package bernardo.com.br.blueshoes.domain

class CreditCard(
        val number: String,
        val enterprise: String,
        val ownerFullName: String,
        val ownerRegNumber: String = "",
        val expiryMonth: Int = 0,
        val expiryYear: Int = 0,
        val securityCode: String = ""
    ) {

    fun getNumberAsHidden()
        = String.format("**** **** **** %s", number)

    fun getOwnerFullNameAsHidden() : String{
        val nameList = ownerFullName.split(" ")

        val firstName = nameList.first().substring(0, 2)
        val lastName = nameList.last()

        return String.format("%s... %s", firstName, lastName)
    }
}