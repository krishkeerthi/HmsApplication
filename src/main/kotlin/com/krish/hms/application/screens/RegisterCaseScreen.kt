
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
        val case = HmsRepository.createNewCase(ssn)
        return if(case == null){
            println("Patient does not exist, register patient first")
            null
        } else
            case
    }
    else{
        val caseId = readCaseId()

        if(!HmsRepository.checkPatientExistence(ssn)){
            println("Patient does not exist, register patient first")
            return null
        }

        val case = HmsRepository.getCase(caseId)

        return if(case == null){
            println("Case id does not exists")
            null
        } else
            case
    }
}

