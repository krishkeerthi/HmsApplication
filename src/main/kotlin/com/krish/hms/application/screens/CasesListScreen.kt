
package com.krish.hms.application.screens

import com.krish.hms.application.helper.readCaseId
import com.krish.hms.library.repository.Failure
import com.krish.hms.library.repository.HmsRepository
import com.krish.hms.library.repository.Success

fun listCases(){
    val caseId = readCaseId()

    showCase(caseId)
}

private fun showCase(caseId: String){
    when(val caseResult = HmsRepository.getCase(caseId)){
        is Success ->{
            val case = caseResult.value
            println("Case id   |  Patient id  |  First visit  |  Last Visit")
            println("${case.caseId}  ${case.patientId}  ${case.firstVisit}  ${case.lastVisit}")

            showConsultations(case.caseId)
       }
        is Failure -> println(caseResult.reason.message)
    }
}

private fun showConsultations(caseId: String){
    when(val consultationIdsResult = HmsRepository.getConsultations(caseId)){
        is Success -> {
            for(consultationId in consultationIdsResult.value)
                showConsultation(consultationId)
        }
        is Failure ->{
            println(consultationIdsResult.reason.message)
        }
    }
}

private fun showConsultation(consultationId: String){
    when(val consultationResult = HmsRepository.getConsultation(consultationId)){
        is Success ->{
            val consultation = consultationResult.value
            println("Consultation id   |  Doctor id   |  Department   | Issue   |  Visit date |  Assessment")
            println("${consultation.consultationId} ${consultation.doctorId} ${consultation.department.name.lowercase()} " +
                    "${consultation.issue} ${consultation.visitDate}  ${consultation.assessment}")

            showMedicines(consultation.consultationId)
        }
        is Failure -> println(consultationResult.reason.message)
    }
}

private fun showMedicines(consultationId: String){
    when(val medicineIdsResult = HmsRepository.getMedicines(consultationId)){
        is Success -> {
            println("Medicine name  |  Medicine type  |  count   | days  | morning | Afternoon | Evening")
            for(medicineId in medicineIdsResult.value)
                showMedicine(medicineId)
        }
        is Failure ->{
            println(medicineIdsResult.reason.message)
        }
    }
}

private fun showMedicine(medicineId: String){

    when(val medicineResult = HmsRepository.getMedicine(medicineId)){
        is Success ->{
            val medicine = medicineResult.value
            println("${medicine.medicineName}  ${medicine.medicineType.name.lowercase()} " +
                    "${medicine.count} ${medicine.days} ${medicine.morning}  ${medicine.afternoon}  ${medicine.night}")
        }
        is Failure -> println(medicineResult.reason.message)
    }
}