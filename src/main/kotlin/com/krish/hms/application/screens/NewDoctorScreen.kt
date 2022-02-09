
package com.krish.hms.application.screens

import com.krish.hms.application.helper.*
import com.krish.hms.library.helper.generateId
import com.krish.hms.library.model.Doctor
import com.krish.hms.library.model.IdHolder
import com.krish.hms.library.repository.Failure
import com.krish.hms.library.repository.HmsRepository
import com.krish.hms.library.repository.Success

fun addDoctor(){
    val doctor = readDoctor()

    when(val result = HmsRepository.addDoctor(doctor)){
        is Success -> {
            println(result.value)
        }
        is Failure -> {
            println(result.reason.message)
        }
    }
}

private fun readDoctor(): Doctor {
    val name = readName()
    val gender = readGender()
    val dob = readDOB()
    val address = readAddress()
    val contact = readContact()
    val bloodGroup = readBloodGroup()
    val ssn = readSsn()
    val doctorId = generateId(IdHolder.DOCTOR)
    val department = readDepartment()
    val experience = readExperience()
    val startTime = readStartTime()
    val endTime = readEndTime()

    return Doctor(name, gender, dob, address,
        contact, bloodGroup, ssn, doctorId,
        department, experience, startTime, endTime)
}