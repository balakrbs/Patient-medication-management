package com.PatientMedication.Utils;

import com.PatientMedication.Model.Patients;

public class LoggedInUserHolder {
    private static Patients loggedInUser;

    public static Patients getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(Patients Patients) {
        loggedInUser = Patients;
    }
}


