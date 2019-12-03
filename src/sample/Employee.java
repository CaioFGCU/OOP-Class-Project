package sample;

import java.util.regex.Pattern;

/**
 * audit trail of the production line that records which employee recorded production
 * Issue 8
 */
public class Employee {
    String name;
    String username;
    String password;
    String email;


    Employee(String name, String password) {

        if (checkName(name)) {
            this.name = name;
            setUsername();
            setEmail();
        } else {
            this.name = "default";
            this.email = "user@oracleacademy.Test";
        }
        if (isValidPassword(password)) {
            this.password = password;
        } else {
            System.out.println("Password was invald so default password 'pw' will be applied instead. ");
            this.password = "pw";
        }

    }

    @Override
    public String toString() {
        return "Employee Details\n" + "Name: " + name + "\n" + "Username: " + username + "\n"
                + "Email: " + email + "\n" + "Initial Password: " + password;
    }

    /**
     * used to set username by spliting the user's name into first and last, making it all lowercase,
     * and taking the first letter of the first name and the entire last name.
     */
    private void setUsername() {
        String[] s = this.name.toLowerCase().split("\\S", 2);
        this.username = s[0].substring(0, 1) + s[1];
    }

    /**
     * regex expression used to check if whole name has a space within it.
     * purpose is to check to make sure there is a space between first and last name.
     *
     * @param n used as parameter for name when method is called
     * @return
     */
    private boolean checkName(String n) {
        return Pattern.compile("\\S").matcher(n).find();
    }

    /**
     * used to set email
     * splits name into first and last, makes it all lowercase
     * then puts a period in between first name and last name,
     * then adds @....
     */
    private void setEmail() {
        String[] s = this.name.toLowerCase().split("\\S", 2);
        this.email = s[0] + "." + s[1] + "@oracleacademy.Test";
    }

    /**
     * validating password by using regex to check if password has lowercase letters,
     * uppercase letters, and special characters within field.
     *
     * @param pw used as parameter for password when method is called
     * @return lowercase, uppercase, and special characters
     */
    private boolean isValidPassword(String pw) {
        boolean lc = Pattern.compile("[a-z]+").matcher(pw).find();
        boolean uc = Pattern.compile("[A-Z]+").matcher(pw).find();
        boolean sc = Pattern.compile("[^a-zA-Z0-9]+").matcher(pw).find();
        return lc && uc && sc;
    }

}
