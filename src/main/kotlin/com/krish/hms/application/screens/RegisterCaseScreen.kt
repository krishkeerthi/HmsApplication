
package com.krish.hms.application.screens

import com.krish.hms.application.helper.*
import com.krish.hms.library.model.Case
import com.krish.hms.library.repository.Failure
import com.krish.hms.library.repository.HmsRepository
import com.krish.hms.library.repository.Success

fun registerCase(){
    val ssn = readSsn()
    val inTime = readPatientInTime()
    val issue = readIssue()

    val case = getOrCreateCase(ssn)

    if(case != null){
        when(val doctorAssignmentResult = HmsRepository.assignDoctor(ssn, issue, inTime, case.caseId)){
            is Success -> {
                println(doctorAssignmentResult.value)
            }
            is Failure -> {
                println(doctorAssignmentResult.reason.message)
            }
        }
    }
}


private fun getOrCreateCase(ssn: Int): Case?{
    val newCase = isNewCase()

    if(newCase){
        return when(val caseResult = HmsRepository.createNewCase(ssn)){
            is Success -> caseResult.value
            is Failure -> {
                println(caseResult.reason.message)
                null
            }
        }

    }
    else{
        val caseId = readCaseId()

        if(!HmsRepository.checkPatientExistence(ssn)){
            println("Patient does not exist, register patient first")
            return null
        }

        return when(val caseResult = HmsRepository.getCase(caseId)){
            is Success -> caseResult.value
            is Failure -> {
                println(caseResult.reason.message)
                null
            }
        }
    }
}

