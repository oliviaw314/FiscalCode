import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Data data = new Data("Helen", "Yu","F","1/12/1950");
        Data data1 = new Data("Mickey","Mouse","M","16/1/1928");
        System.out.println(fiscalCode(data));
        System.out.println(fiscalCode(data1));
    }

    public static String fiscalCode(Data data) {
        //create arraylist of vowels to check whether is consonant or vowel
        ArrayList<Character> vowels = new ArrayList<>(Arrays.asList('a','e','i','o','u'));

        //surname method
        String surname = surname(data, vowels);
        //name method
        String name = name(data,vowels);
        //date of birth and gender method
        String birthAndGender = birthDateGender(data, vowels);

        return surname+name+birthAndGender;
    }

    public static String surname(Data data, ArrayList<Character> vowels) {
        StringBuilder surname = new StringBuilder();

        //fill up w/ consonants
        for (int i=0; i<data.getSurname().length(); i++) {
            if (surname.length()<3) {
                //is consonant
                if (!vowels.contains(data.getSurname().charAt(i))) {
                    surname.append(data.getSurname().charAt(i));
                }
            } else {
                break;
            }
        }
        //less than 3 consonants - fill up with vowels
        if (surname.length()<3) {
            for (int i=0; i<data.getSurname().length(); i++) {
                if (surname.length()<3) {
                    //is vowel
                    if (vowels.contains(data.getSurname().charAt(i))) {
                        surname.append(data.getSurname().charAt(i));
                    }
                }
            }
        }

        //still less than 3 letters - fill up with 'X'
        if (surname.length()<3) {
            surname.append('X');
        }

        return surname.toString().toUpperCase();
    }

    public static String name(Data data, ArrayList<Character> vowels) {
        //count number of consonants
        int consonantsCount = 0;
        for (int i=0; i<data.getName().length(); i++) {
            char letter = data.getName().charAt(i);
            if (!vowels.contains(letter)) {
                consonantsCount++;
            }
        }

        StringBuilder name = new StringBuilder();

        //more than 3 consonants - use 1st, 3rd and 4th consonant
        if (consonantsCount>3) {
            for (int i=0; i<data.getName().length(); i++) {
                //get first 4 consonants
                if (name.length()<4) {
                    //is consonant
                    if (!vowels.contains(data.getName().charAt(i))) {
                        name.append(data.getName().charAt(i));
                    }
                } else {
                    break;
                }
            }
            //delete element at index 1 (thus delete 2nd consonant)
            name.deleteCharAt(1);
        }

        //if 3 consonants - use first 3 consonants
        //if less than 3 consonants - fill up with consonants
        else {
            for (int i=0; i<data.getName().length(); i++) {
                if (name.length()<3) {
                    //is consonant
                    if (!vowels.contains(data.getName().charAt(i))) {
                        name.append(data.getName().charAt(i));
                    }
                } else {
                    break;
                }
            }
        }

        //less than 3 consonants - fill up with vowels
        if (name.length()<3) {
            for (int i=0; i<data.getName().length(); i++) {
                if (name.length()<3) {
                    //is vowel
                    if (vowels.contains(data.getName().charAt(i))) {
                        name.append(data.getName().charAt(i));
                    }
                }
            }
        }

        //still less than 3 letters - fill up with 'X'
        if (name.length()<3) {
            name.append('X');
        }

        return name.toString().toUpperCase();
    }

    public static String birthDateGender(Data data, ArrayList<Character> vowels) {
        StringBuilder birthAndGender = new StringBuilder();
        //get last 2 digits of birthdate
        birthAndGender.append(data.getBirthDate().substring(data.getBirthDate().length()-2));

        //assign a letter to each month
        char[] monthLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'T'};

        //split birth date, month, year into an array
        String[] parts = data.getBirthDate().split("/");
        //add the month
        birthAndGender.append(monthLetters[Integer.parseInt(parts[1])-1]);

        //for males
        if (data.getGender().equalsIgnoreCase("M")) {
            if (parts[0].length()==2) {
                birthAndGender.append(parts[0]);
            }
            else {
                birthAndGender.append("0");
                birthAndGender.append(parts[0]);
            }
        }
        //for females
        if (data.getGender().equalsIgnoreCase("F")) {
            birthAndGender.append(Integer.parseInt(parts[0])+40);
        }

        return birthAndGender.toString();
    }
}