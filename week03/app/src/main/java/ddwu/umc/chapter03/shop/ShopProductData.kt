package ddwu.umc.chapter03.shop

data class ShopProductData(

    val image: Int,
    val isBestSeller: Boolean,
    val name: String,
    val category: String,
    val colors: String,
    val price: String
)