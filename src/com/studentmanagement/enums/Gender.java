package com.studentmanagement.enums;

public enum Gender {
    FEMALE(1), MALE(2), OTHER(3);

    public final int value;

    private Gender(int value) {
        this.value = value;
    }

    public static boolean isDefine(int enumValue){
        for (var e : values()){
            if(e.value == enumValue)
                return true;
        }
        return false;
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
