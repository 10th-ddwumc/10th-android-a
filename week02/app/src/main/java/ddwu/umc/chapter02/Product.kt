package ddwu.umc.chapter02

data class Product(
    val id: Int,
    val name: String,
    val subName: String?,
    val colorsCount: Int?,
    val price: Int,
    val imageResId: Int,
    val isBestSeller: Boolean = false
)