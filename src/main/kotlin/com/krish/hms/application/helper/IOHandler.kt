
package com.krish.hms.application.helper

import com.krish.hms.library.helper.*
import com.krish.hms.library.model.*
import java.time.DateTimeException
import java.time.LocalDate
import java.time.LocalTime

private fun readString(field: String): String{
    println("Enter $field:")
    var input = readLine()
    while((input == null) || (input == "") || input.isBlank()){
        println("$field should not be empty! Re-enter $field:")
        input = readLine()
    }
    return input
}

private fun readInt(field: String): Int{
    println("Enter $field:")
    var input = readLine()
    while(input == null || input.toIntOrNull() == null) {
        println("$field must contains integers only! Re-enter $field:")
        input = readLine()
    }
    return input.toIntOrNull()!!
}

private fun readDate(field: String): LocalDate{
    var date = readString(field).toDateOrNull()
    while(date == null){
        println("Invalid Date")
        date = readString("valid $field").toDateOrNull()
    }
    return date
}

private fun readBoolean(field: String): Boolean{
    val choice = readString(field).trim().lowercase()
    if(choice == "yes")
        return true
    else if(choice == "no")
        return false

    println("Enter yes or no only")
    return readBoolean(field)
}

private fun readTime(field: String): LocalTime {
    var hour = readInt("$field hour (1 to 12)")
    while(hour !in 1..12){
        println("Hour should be in 1 to 12")
        hour = readInt("valid hour (1 to 12)")
    }

    var minutes = readInt("$field minutes (0 to 59)")
    while(minutes !in 0..59){
        println("Minutes should be in 0 to 59")
        minutes = readInt("valid minutes (0 to 59)")
    }

    val meridian = getMeridian(readOptions(Meridian.values()))
    return getTime(hour, minutes, meridian)!!
}

private fun getTime(hour: Int, minute: Int, meridian: Meridian): LocalTime?{
    try{
        if(meridian == Meridian.AM){
            if(hour == 12)
                return LocalTime.of(0, minute, 0)
            return LocalTime.of(hour, minute, 0)
        }
        else{
            if(hour == 12)
                return LocalTime.of(hour, minute, 0)
            return LocalTime.of(12 + hour, minute, 0)
        }
    }
    catch (e: DateTimeException){
        return null
    }
}

private fun <T> readOptions(options: Array<T>): Int{
    var i= 1
    for(option in options)
        println("${i++} . $option")
    var result = readInt("option")

    while(result !in 1..options.size){
        println("Option should be in 1 to ${options.size}")
        result = readInt("option")
    }
    return result - 1
}

fun readName() = readString("name")

fun readExperience() = readInt("experience")

fun readDOB() = readDate("Date of Birth(dd-mm-yyyy)")

fun readStartTime() = readTime("start")

fun readEndTime() = readTime("end")

fun readAddress() = readString("address")

fun readSsn() = readInt("SSN")

fun readContact() = readString("contact")

fun readMedicineName() = readString("medicine name")

fun readCount() = readInt("count of usage")

fun readDays() = readInt("no. of days")

fun readIssue() = readString("issue")

fun readAssessment() = readString("assessment")

fun forMorning() = readBoolean("yes or no for morning")

fun forAfternoon() = readBoolean("yes or no for afternoon")

fun forNight() = readBoolean("yes or no for night")

fun readDepartment() = getDepartment(readOptions(Department.values()))

fun readGender() = getGender(readOptions(Gender.values()))

fun readBloodGroup() = getBloodGroup(readOptions(BloodGroup.values()))

fun readMedicineType() = getMedicineType(readOptions(MedicineType.values()))

fun readModules() = getModule((readOptions(Modules.values())))

fun readPatientInTime() = readTime("patient in")

fun isNewCase() = readBoolean("yes or no for new case")

fun readCaseId() = readString("case id")

fun yesOrNoForMedicine() = readBoolean("yes or no to add medicine")

fun readDoctorId() = readString("doctor id")

fun readPatientId() = readString("patient id")

fun readDoctorSelection() = getDoctorSelection(readOptions(DoctorSelection.values()))

fun readPatientSelection() = getPatientSelection(readOptions(PatientSelection.values()))

