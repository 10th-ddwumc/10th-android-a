package ddwu.umc.chapter03

data class ProductData(
    val id: Int?,
    val image: Int,
    val name: String,
    val price: String,

    val isBestSeller: Boolean?,
    val category: String?,
    val colors: String?,

    var isWished: Boolean = false
)
