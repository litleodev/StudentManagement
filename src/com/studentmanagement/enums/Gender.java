package com.studentmanagement.enums;

public enum Gender {
    FEMALE, MALE, OTHER;
    public int getGenderValue() {
        switch (this) {
            case FEMALE:
                return 1;
            case MALE:
                return 2;
            case OTHER:
                return 3;
            default:
                return 0;
        }
    }

    public static boolean isDefine(int enumValue){
        return enumValue == FEMALE.getGenderValue()
                || enumValue == MALE.getGenderValue()
                || enumValue == OTHER.getGenderValue();
    }

    public static String getGenderName(int enumValue){
        if(!isDefine(enumValue))
            return "";
        switch (enumValue){
            case 1:
                return "Female";
            case 2:
                return "Male";
            default:
                return "Other";
        }
    }
}
