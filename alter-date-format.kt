fun main(){
	printResult("AM 07:10:47", 3, "07:10:50")
	printResult("AM 07:10:12", 180, "07:13:12")
	printResult("AM 07:10:12", 3600, "08:10:12")

	printResult("AM 00:00:00", 60, "00:01:00")
	printResult("AM 00:00:00", 3600, "01:00:00")
	printResult("AM 00:00:59", 1, "00:01:00")
	printResult("AM 00:59:59", 1, "01:00:00")
	printResult("AM 00:00:00", 86400, "00:00:00")

	printResult("AM 11:59:59", 1, "12:00:00")
	printResult("AM 12:59:59", 1, "13:00:00")

	printResult("PM 07:10:47", 3, "19:10:50")
	printResult("PM 07:10:12", 180, "19:13:12")
	printResult("PM 07:10:12", 3600, "20:10:12")

	printResult("PM 00:00:00", 60, "12:01:00")
	printResult("PM 00:00:00", 3600, "13:00:00")
	printResult("PM 00:00:59", 1, "12:01:00")
	printResult("PM 00:59:59", 1, "13:00:00")
	printResult("PM 00:00:00", 86400, "12:00:00")

	printResult("PM 11:59:59", 1, "00:00:00")
	printResult("PM 12:59:59", 1, "01:00:00")
	printResult("PM 11:59:59", 43201, "12:00:00")
}

fun printResult(
    time: String,
    N: Int,
    requiredString: String
){
    val result = convert(time, N)

    println(
        String.format(
            "time: %s, N: %d, result: %s, success: %b",
            time,
            N,
            result,
            result == requiredString
        )
    )
}

fun convert(time: String, N:Int): String{
    val hourSec = 3600
    val minSec = 60

    var hour = time.substring(3, 5).toInt()
    var min = time.substring(6, 8).toInt()
    var sec = time.substring(9, 11).toInt()

    if(time.substring(0, 2).toLowerCase() == "pm"){
        hour += 12
    }

    val divideHour = N / hourSec
    val divideHourRemain = N % hourSec
    val divideMin = divideHourRemain / minSec
    val divideMinRemain = divideHourRemain % minSec

    sec += divideMinRemain
    if(sec >= 60){
        min += sec/60
        sec %= 60
    }

    min += divideMin
    if(min >= minSec){
        hour += min/minSec
        min %= minSec
    }

    hour += divideHour
    if(hour > 23){
        hour %= 24
    }

    var result: String = if(hour < 10) "0"+hour else hour.toString()
    result += ":"
    result += if(min < 10) "0"+min else min
    result += ":"
    result += if(sec < 10) "0"+sec else sec
 
    return result
}