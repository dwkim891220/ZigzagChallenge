fun main(){
    calculate("AAAAAAAAAAAA", listOf(30, 60, 90, 120, 150, 180, 210, 240, 270, 300))
    calculate("82A900519A", listOf(20, 39, 48, 53, 83, 93))
}

fun calculate(
    input: String,
    requiredOutput: List<Int>
){
    val frameList : ArrayList<Frame> = arrayListOf()
    val resultList : ArrayList<Int> = arrayListOf()
    var index = 0
    var isError = false

    while (index < input.length){
    	println("index = $index")
        val value = input[index].toString()
        val frame = Frame().apply {
            first = validValue(value)
        }

        if(value.toLowerCase() == "a"){
            if(index+1 <= input.length) {
                frame.bonus += validValue(input[index+1].toString())
            }

            if(index+2 <= input.length) {
                frame.bonus += validValue(input[index+2].toString())
            }

            index ++
        }else{
            if(index+1 <= input.length) {
                frame.second = validValue(input[index+1].toString())
            }

            isError = frame.first + frame.second > 10
            if(isError) break

            if((frame.first + frame.second == 10)
                && index+2 <= input.length)
            {
                frame.bonus = validValue(input[index+2].toString())
            }

            index=+2
        }

        frameList.add(frame)
    }

    frameList.forEach { frame ->
        resultList.add(frame.first + frame.second + frame.bonus)
    }

    println(
        if(isError){
            "invalid input values"
        }else {
            String.format(
                "input: %s, result: %s, success: %b",
                input,
                frameList,
                resultList == requiredOutput
            )
        }
    )
}

fun validValue(str: String): Int = if(str.toLowerCase() == "a") 10 else str.toInt()

data class Frame(
    var first: Int = 0,
    var second: Int = 0,
    var bonus: Int = 0
)