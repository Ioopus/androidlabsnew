package com.example.myapplication;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Moodie Allam
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    /** this holds the text at the center of the screen*/
    TextView tv = null;
    /** this holds the input box for users to enter text*/
    EditText et = null;
    /** this holds the submit button of the screen*/
    Button btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Assigning variables to our menu elements references
         */
        TextView tv = findViewById(R.id.textView3);
        EditText et = findViewById(R.id.editText);
        Button btn = findViewById(R.id.button);

        /**
         * Listen to when the button is clicked, get the text of the input
         * then reference our password check method to add our specific
         * requirements
         */
        btn.setOnClickListener(clk -> {
            String password = et.getText().toString();

            if (checkPasswordComplexity(password)) {
                tv.setText("Your password meets the requirements");
            }
            else {
                tv.setText("You shall not pass!");
            }
        });
    }

    /**
     * this function takes a string the user inputted and checks the complexity of the string to check
     * if it meets the requirments set and if not return an error message prompting the user to fix the password
     * @param pw The String object that we are checking
     * @return returns true if all conditions return true meeting our requirments
     */
    boolean checkPasswordComplexity(String pw) {
        /**
         * Declaring our false booleans to change later, these will
         * be our determining if statements later
         */
        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial, lengthValid;
        lengthValid= foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;

        /**
         *Creating an empty array of chars and creating a length equal
         * to the password
         */
        char[] pass = new char[pw.length()];
        /**
         * We iterate through every character of the password passed to us then save
         * each char to inside the array and filter the results and get our
         * desired output
         */
        if (pw.length() > 4 && pw.length() < 20) {
            for (int i = 0; i < pw.length(); i++) {
                pass[i] = pw.charAt(i);
                if (isUpperCase(pass[i])) {
                    foundUpperCase = true;
                }
                if (isLowerCase(pass[i])) {
                    foundLowerCase = true;
                }
                if (isDigit(pass[i])) {
                    foundNumber = true;
                }
                if (isSpecialCharacter(pass[i])) {
                    foundSpecial = true;
                }
            }
        }
            else {
                lengthValid = false;
            }
        if (!lengthValid){
            Toast.makeText(getApplicationContext(), "enter pass between 4-20 characters", Toast.LENGTH_LONG).show();
            return false;
        }
        else if (!foundUpperCase){
            Toast.makeText(getApplicationContext(), "enter capital", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(!foundLowerCase){
            Toast.makeText(getApplicationContext(), "enter lowercase", Toast.LENGTH_LONG).show();
            return false;
        }
        else if (!foundNumber){
            Toast.makeText(getApplicationContext(), "enter number", Toast.LENGTH_LONG).show();
            return false;
        }
        else if (!foundSpecial){
            Toast.makeText(getApplicationContext(), "enter special character", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * this takes a char and checks if it matches unicode characters that are usually unrecognizable when checked as strings
     * if the character taken is a special character then it returns true otherwise it is false
     * @param c Char variable taken to be checked while parsing through the string in our for loop in the method above
     * @return returns true if the char is a special character otherwise returns false
     */
    boolean isSpecialCharacter(char c){
        switch (c) {
            case '#':
            case '?':
            case '*':
            case '@':
            case '!':
            case '$':
            case '%':
            case '^':
            case '&':
                return true;
            default:
                return false;
        }
    }
}
