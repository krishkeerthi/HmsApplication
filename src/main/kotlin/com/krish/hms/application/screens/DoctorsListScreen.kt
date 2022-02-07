
package com.krish.hms.application.screens

import com.krish.hms.application.helper.*
import com.krish.hms.library.model.DoctorSelection
import com.krish.hms.library.repository.HmsRepository

fun listDoctors(){
    val doctors = when(readDoctorSelection()){
        DoctorSelection.ALL -> HmsRepository.getAllDoctors()

        DoctorSelection.DEPARTMENT -> HmsRepository.getDoctorByDepartment(readDepartment())

        DoctorSelection.ID -> HmsRepository.getDoctorById(readDoctorId())
    }

    if(doctors.isNotEmpty()){
        println("Name  | Age | Gender | Department | years of Experience  | Avail time start  | Avail time end")
        for(doctor in doctors)
            println("${doctor.name} ${doctor.age}  ${doctor.gender.name.lowercase()} ${doctor.department.name.lowercase()} " +
                    "${doctor.yearsOfExperience} ${doctor.startTime}  ${doctor.endTime}")
    }
    else
        println("Doctors list is empty")
}