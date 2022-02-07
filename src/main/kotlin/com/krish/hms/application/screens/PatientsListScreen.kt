
package com.krish.hms.application.screens

import com.krish.hms.application.helper.readName
import com.krish.hms.application.helper.readPatientId
import com.krish.hms.application.helper.readPatientSelection
import com.krish.hms.library.model.PatientSelection
import com.krish.hms.library.repository.HmsRepository

fun listPatients(){
    val patients = when(readPatientSelection()){
        PatientSelection.ALL -> HmsRepository.getAllPatients()

        PatientSelection.NAME -> HmsRepository.getPatientByName(readName())

        PatientSelection.ID -> HmsRepository.getPatientById(readPatientId())
    }

    if(patients.isNotEmpty()){
        println("Name   |   Age   |  Gender  | First visit  | Last visit")
        for(patient in patients)
            println("${patient.name} ${patient.age} ${patient.gender.name.lowercase()}" +
                    "  ${patient.firstRegistered}  ${patient.lastRegistered}")
    }
    else
        println("Patients list is empty")
}