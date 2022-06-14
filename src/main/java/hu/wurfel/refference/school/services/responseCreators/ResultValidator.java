package hu.wurfel.refference.school.services.responseCreators;

import java.util.ArrayList;

public class ResultValidator {
    public static ArrayList validate(ArrayList originalList) {
        ArrayList list = originalList;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j)) && i != j) {
                    list.remove(j);
                    j--;
                }
            }
        }
        return list;
    }
}
