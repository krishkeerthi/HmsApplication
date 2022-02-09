
package com.krish.hms.application.screens

import com.krish.hms.application.helper.*
import com.krish.hms.library.helper.generateId
import com.krish.hms.library.helper.getToday
import com.krish.hms.library.model.IdHolder
import com.krish.hms.library.model.Patient
import com.krish.hms.library.repository.Failure
import com.krish.hms.library.repository.HmsRepository
import com.krish.hms.library.repository.Success

fun addPatient(){
    val patient = readPatient()

    when(val result = HmsRepository.addPatient(patient)){
        is Success -> {
            println(result.value)
        }

        is Failure -> {
            println(result.reason.message)
        }
    }
}

private fun readPatient(): Patient {
    val name = readName()
    val gender = readGender()
    val dob = readDOB()
    val address = readAddress()
    val contact = readContact()
    val bloodGroup = readBloodGroup()
    val ssn = readSsn()
    val patientId = generateId(IdHolder.PATIENT)

    return Patient(name, gender, dob, address,
        contact, bloodGroup, ssn, patientId, getToday(),
        getToday())
}
