package kr.dwkim.zigzagchallenge.model

class ShopModel(shop: Shop, private val pStyleList: List<DataPresenter.StyleModel>?) {
    val score: Int
    val name: String
    val imageUrl: String
    val ageArray: IntArray
    val ageStringList: Array<String>
    var ageEqualsCount = 0
    val styleList: List<DataPresenter.StyleModel>
    var styleEqualsCount = 0
    private val style: String
    private val homePageUrl: String

    init {
        this.score = shop.score
        this.name = shop.name
        this.homePageUrl = shop.url
        this.imageUrl = getImageUrl(shop.url)
        this.ageArray = shop.ageArray
        this.style = shop.style
        this.styleList = parseStyleList(shop.style)
        this.ageStringList = getAgeStringArray(shop.ageArray)
    }

    private fun getImageUrl(url: String) : String =
        try {
            val startIndex: Int
            val endIndex: Int
            val dividerIndex = url.indexOf("://") + 3
            val firstDot = url.indexOf(".")
            val secondDot = url.indexOf(".", firstDot + 1)

            if(url.substring(dividerIndex, firstDot) == "www"){
                startIndex = firstDot + 1
                endIndex = secondDot
            }else{
                startIndex = dividerIndex
                endIndex = firstDot
            }

            String.format("https://cf.shop.s.zigzag.kr/images/%s.jpg",url.substring(startIndex, endIndex))
        }catch (e: StringIndexOutOfBoundsException){
            e.printStackTrace()
            ""
        }

    private fun parseStyleList(style: String) : List<DataPresenter.StyleModel> {
        val tempList = arrayListOf<DataPresenter.StyleModel>()

        style.split(",").forEach { styleText ->
            pStyleList?.forEach { model ->
                if(model.style == styleText){
                    tempList.add(model)
                }
            }
        }

        return tempList
    }


    private fun getAgeStringArray(ageArray: IntArray): Array<String>{
        val ageStringList = arrayListOf<String>()

        if(ageArray[0] == 1
            && ageArray[1] == 1
            && ageArray[2] == 1
            && ageArray[3] == 1
            && ageArray[4] == 1
            && ageArray[5] == 1
            && ageArray[6] == 1
        ){
            ageStringList.add("모두")
        }else {
            if (ageArray[0] == 1) {
                ageStringList.add("10대")
            }

            if (ageArray[1] == 1
                || ageArray[2] == 1
                || ageArray[3] == 1
            ) {
                ageStringList.add("20대")
            }

            if (ageArray[4] == 1
                || ageArray[5] == 1
                || ageArray[6] == 1
            ) {
                ageStringList.add("30대")
            }
        }

        return ageStringList.toTypedArray()
    }

    fun setStyleEqualsCount(filterList: List<String>){
        styleEqualsCount = 0

        if(filterList.isEmpty()){
            styleEqualsCount = 0
        }else {
            filterList.forEach { item ->
                if(style.contains(item)){
                    styleEqualsCount++
                }
            }
        }
    }

    fun setAgeEqualsCount(ageList: List<Int>){
        ageEqualsCount = 0

        if(ageList.isEmpty()){
            ageEqualsCount = 0
        }else {
            ageList.forEachIndexed { index, item ->
                if(ageArray[index] == item){
                    ageEqualsCount++
                }
            }
        }
    }
}