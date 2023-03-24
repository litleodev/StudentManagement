package com.studentmanagement.enums;

public enum Subject {
    MATH, PHYSICS, CHEMISTRY;

    public String getSubjectName() {
        switch (this) {
            case MATH:
                return "Math";
            case PHYSICS:
                return "Physics";
            default:
                return "Chemistry";
        }
    }
}
