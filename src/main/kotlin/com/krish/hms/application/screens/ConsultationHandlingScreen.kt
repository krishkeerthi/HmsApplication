
package com.krish.hms.application.screens

import com.krish.hms.application.helper.*
import com.krish.hms.application.helper.yesOrNoForMedicine
import com.krish.hms.library.helper.generateId
import com.krish.hms.library.model.Consultation
import com.krish.hms.library.model.IdHolder
import com.krish.hms.library.model.Medicine
import com.krish.hms.library.repository.Failure
import com.krish.hms.library.repository.HmsRepository
import com.krish.hms.library.repository.Success

fun handleConsultation(){
    val doctorId = readDoctorId()

    when(val consultationStatus = HmsRepository.getDoctorsFirstConsultation(doctorId)){
        is Success -> {
            val consultation = consultationStatus.value

            while(!addAssessment(consultation, doctorId))
                println("Provide valid data")

            while(yesOrNoForMedicine())
            while(!addConsultationMedicine(consultation.consultationId))
                println("Provide valid data")

        }
        is Failure -> {
            println(consultationStatus.reason.message)
        }
    }
}

private fun addAssessment(consultation: Consultation, doctorId: String): Boolean{
    val assessmentMessage = readAssessment()

    return when(val assessmentResult = HmsRepository.addAssessment(consultation.consultationId,
        doctorId, assessmentMessage)){

        is Success -> {
            println(assessmentResult.value)
            true
        }
        is Failure -> {
            println(assessmentResult.reason.message)
            false
        }
    }
}

private fun addConsultationMedicine(consultationId: String): Boolean{
    if(!HmsRepository.checkConsultationExistence(consultationId))
        return false
    val medicine = readMedicine(consultationId)

    return when(val medicineResult = HmsRepository.addMedicine(consultationId, medicine)){
        is Success -> {
            println(medicineResult.value)
            true
        }
        is Failure -> {
            println(medicineResult.reason.message)
            false
        }
    }
}

private fun readMedicine(consultationId: String): Medicine {
    val medicineId = generateId(IdHolder.MEDICINE)
    val medicineName = readMedicineName()
    val medicineType = readMedicineType()
    val count = readCount()
    val days = readDays()
    val morning = forMorning()
    val afternoon = forAfternoon()
    val night = forNight()

    return Medicine(medicineId, consultationId, medicineName,
        medicineType, count, days, morning, afternoon, night)
}