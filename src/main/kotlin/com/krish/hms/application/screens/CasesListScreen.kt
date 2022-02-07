
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
    val case = HmsRepository.getCase(caseId)

    if(case == null){
        println("Case does not exist")
    }
    else{
        println("Case id   |  Patient id  |  First visit  |  Last Visit")
        println("${case.caseId}  ${case.patientId}  ${case.firstVisit}  ${case.lastVisit}")

        showConsultations(case.caseId)
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
    val consultation = HmsRepository.getConsultation(consultationId)

    if(consultation == null){
        println("Consultation does not exist")
    }
    else{
        println("Consultation id   |  Doctor id   |  Department   | Issue   |  Visit date |  Assessment")
        println("${consultation.consultationId} ${consultation.doctorId} ${consultation.department.name.lowercase()} " +
                "${consultation.issue} ${consultation.visitDate}  ${consultation.assessment}")

        showMedicines(consultation.consultationId)
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
    val medicine = HmsRepository.getMedicine(medicineId)

    if(medicine == null)
        println("Medicine does not exist")
    else{
        println("${medicine.medicineName}  ${medicine.medicineType.name.lowercase()} " +
        "${medicine.count} ${medicine.days} ${medicine.morning}  ${medicine.afternoon}  ${medicine.night}")
    }
}