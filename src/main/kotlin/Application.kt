
import com.krish.hms.application.screens.*
import com.krish.hms.application.helper.readModules
import com.krish.hms.library.model.Modules

fun main() {
    println("Welcome to Hospital Management System")

    while(true){
        when(readModules()){
            Modules.ADDDOCTOR -> addDoctor()

            Modules.ADDPATIENT -> addPatient()

            Modules.REGISTERCASE -> registerCase()

            Modules.HANDLECONSULTATION -> handleConsultation()

            Modules.LISTDOCTORS -> listDoctors()

            Modules.LISTPATIENTS -> listPatients()

            Modules.LISTCASES -> listCases()

            Modules.EXIT -> break
        }
    }
    println("Thank you")
}