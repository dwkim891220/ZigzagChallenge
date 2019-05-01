package kr.dwkim.zigzagchallenge.model

class ShopModel(shop: Shop) {
    val score: Int
    val name: String
    val imageUrl: String
    val ageArray: IntArray
    val ageStringList: Array<String>
    val styleList: Array<String>
    private val style: String
    private val homePageUrl: String

    init {
        this.score = shop.score
        this.name = shop.name
        this.homePageUrl = shop.url
        this.imageUrl = getImageUrl(shop.url)
        this.ageArray = shop.ageArray
        this.style = shop.style
        this.styleList = getStyleArray(shop.style)
        this.ageStringList = getAgeStringArray(shop.ageArray)
    }

    private fun getImageUrl(url: String) : String =
        try {
            val startIndex: Int
            val endIndex: Int
            val firstDot = url.indexOf(".") + 1
            val secondDot = url.indexOf(".", firstDot)

            if(secondDot < 0){
                startIndex = url.indexOf("://")
                endIndex = firstDot
            }else{
                startIndex = firstDot
                endIndex = secondDot
            }

            String.format("https://cf.shop.s.zigzag.kr/images/%s.jpg",url.substring(startIndex, endIndex))
        }catch (e: StringIndexOutOfBoundsException){
            e.printStackTrace()
            ""
        }

    private fun getStyleArray(style: String) : Array<String> =
        style.split(",").toTypedArray()

    private fun getAgeStringArray(ageArray: IntArray): Array<String>{
        val ageStringList = arrayListOf<String>()

        if(ageArray[0] == 1){
            ageStringList.add("10대")
        }

        if(ageArray[1] == 1
            || ageArray[2] == 1
            || ageArray[3] == 1
        ){
            ageStringList.add("20대")
        }

        if(ageArray[4] == 1
            || ageArray[5] == 1
            || ageArray[6] == 1
        ){
            ageStringList.add("30대")
        }

        return ageStringList.toTypedArray()
    }
}