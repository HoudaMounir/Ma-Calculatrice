package ma.ensaf.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener  {
    private boolean dotUsed = false;

    private boolean equalClicked = false;
    private String lastExpression = "";

    private final static int EXCEPTION = -1;
    private final static int IS_NUMBER = 0;
    private final static int IS_OPERAND = 1;
    private final static int IS_OPEN_PARENTHESIS = 2;
    private final static int IS_CLOSE_PARENTHESIS = 3;
    private final static int IS_DOT = 4;

    Button buttonNumber0, buttonNumber1, buttonNumber2, buttonNumber3, buttonNumber4, buttonNumber5, buttonNumber6,
            buttonNumber7, buttonNumber8, buttonNumber9;

    Button buttonClear, buttonBackspace, buttonDivision, buttonMultiplication, buttonSubtraction,buttonModulo, buttonLog, buttonLn, button10puissancex,
            buttonAddition, buttonEqual, buttonDot, buttonRacineCarree, buttonPlusMoins, buttonPuissance, buttonInverse, buttonExpo, buttonPi,
            buttonValeureAbsolue, buttonparOuvrante, buttonparFermante, buttonfactorielle, button2nd, buttonExponentielle, buttonPow2;
    TextView textViewInputNumbers;

    ScriptEngine scriptEngine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        scriptEngine = new ScriptEngineManager().getEngineByName("rhino");
        try {
            initializeViewVariables();
        } catch (Exception e) {
            Log.e("MainActivity2", "Erreur lors de l'initialisation des variables de vue", e);
        }

        try {
            setOnClickListeners();
        } catch (Exception e) {
            Log.e("MainActivity2", "Erreur lors de la configuration des écouteurs de clic", e);
        }

        try {
            setOnTouchListener();
        } catch (Exception e) {
            Log.e("MainActivity2", "Erreur lors de la configuration des écouteurs tactiles", e);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_standard) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_scientific) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void initializeViewVariables()
    {
        buttonNumber0 = (Button) findViewById(R.id.button_zero);
        buttonNumber1 = (Button) findViewById(R.id.button_one);
        buttonNumber2 = (Button) findViewById(R.id.button_two);
        buttonNumber3 = (Button) findViewById(R.id.button_three);
        buttonNumber4 = (Button) findViewById(R.id.button_four);
        buttonNumber5 = (Button) findViewById(R.id.button_five);
        buttonNumber6 = (Button) findViewById(R.id.button_six);
        buttonNumber7 = (Button) findViewById(R.id.button_seven);
        buttonNumber8 = (Button) findViewById(R.id.button_eight);
        buttonNumber9 = (Button) findViewById(R.id.button_nine);

        buttonRacineCarree = (Button) findViewById(R.id.button_RacineCarree);
        buttonClear = (Button) findViewById(R.id.button_clear);
        buttonBackspace = (Button) findViewById(R.id.button_backspace);
        buttonDivision = (Button) findViewById(R.id.button_division);
        buttonMultiplication = (Button) findViewById(R.id.button_multiplication);
        buttonSubtraction = (Button) findViewById(R.id.button_subtraction);
        buttonAddition = (Button) findViewById(R.id.button_addition);
        buttonEqual = (Button) findViewById(R.id.button_equal);
        buttonDot = (Button) findViewById(R.id.button_dot);
        buttonPlusMoins = (Button) findViewById(R.id.button_plusmoins);
        buttonPuissance = (Button) findViewById(R.id.button_puissance);
        buttonInverse = (Button) findViewById(R.id.button_Inverse);
        buttonModulo = (Button) findViewById(R.id.button_modulo);
        buttonPow2 = (Button) findViewById(R.id.button_pow2);
        buttonValeureAbsolue = (Button) findViewById(R.id.button_ValeureAbsolue);
        buttonparOuvrante = (Button) findViewById(R.id.button_parOuvrante);
        buttonparFermante = (Button) findViewById(R.id.button_parFermante);
        buttonfactorielle = (Button) findViewById(R.id.button_factorielle);
        buttonExponentielle = (Button) findViewById(R.id.button_exponentielle);
        button10puissancex = (Button) findViewById(R.id.button_10puissancex);
        buttonLog = (Button) findViewById(R.id.button_log);
        buttonLn = (Button) findViewById(R.id.button_ln);
        button2nd = (Button) findViewById(R.id.button_2nd);
        buttonPi = (Button) findViewById(R.id.button_pi);
        buttonExpo = (Button) findViewById(R.id.button_expo);
        textViewInputNumbers = (TextView) findViewById(R.id.textView_input_numbers);
    }

    private void setOnClickListeners()
    {
        buttonNumber0.setOnClickListener((View.OnClickListener) this);
        buttonNumber1.setOnClickListener((View.OnClickListener) this);
        buttonNumber2.setOnClickListener((View.OnClickListener) this);
        buttonNumber3.setOnClickListener((View.OnClickListener) this);
        buttonNumber4.setOnClickListener((View.OnClickListener) this);
        buttonNumber5.setOnClickListener((View.OnClickListener) this);
        buttonNumber6.setOnClickListener((View.OnClickListener) this);
        buttonNumber7.setOnClickListener((View.OnClickListener) this);
        buttonNumber8.setOnClickListener((View.OnClickListener) this);
        buttonNumber9.setOnClickListener((View.OnClickListener) this);

        buttonClear.setOnClickListener((View.OnClickListener) this);
        buttonBackspace.setOnClickListener((View.OnClickListener) this);
        buttonDivision.setOnClickListener((View.OnClickListener) this);
        buttonMultiplication.setOnClickListener((View.OnClickListener) this);
        buttonSubtraction.setOnClickListener((View.OnClickListener) this);
        buttonAddition.setOnClickListener((View.OnClickListener) this);
        buttonEqual.setOnClickListener((View.OnClickListener) this);
        buttonDot.setOnClickListener((View.OnClickListener) this);
        buttonInverse.setOnClickListener((View.OnClickListener) this);
        buttonPow2.setOnClickListener((View.OnClickListener) this);
        buttonRacineCarree.setOnClickListener((View.OnClickListener) this);
        buttonPlusMoins.setOnClickListener((View.OnClickListener) this);
        buttonPuissance.setOnClickListener((View.OnClickListener) this);
        button2nd.setOnClickListener((View.OnClickListener) this);
        buttonparOuvrante.setOnClickListener((View.OnClickListener) this);
        buttonparFermante.setOnClickListener((View.OnClickListener) this);
        buttonModulo.setOnClickListener((View.OnClickListener) this);
        buttonExponentielle.setOnClickListener((View.OnClickListener) this);
        button10puissancex.setOnClickListener((View.OnClickListener) this);
        buttonfactorielle.setOnClickListener((View.OnClickListener) this);
        buttonPi.setOnClickListener((View.OnClickListener) this);
        buttonExpo.setOnClickListener((View.OnClickListener) this);
        buttonLog.setOnClickListener((View.OnClickListener) this);
        buttonLn.setOnClickListener((View.OnClickListener) this);
        buttonValeureAbsolue.setOnClickListener((View.OnClickListener) this);

    }

    private void setOnTouchListener()
    {
        buttonNumber0.setOnTouchListener((View.OnTouchListener) this);
        buttonNumber1.setOnTouchListener((View.OnTouchListener) this);
        buttonNumber2.setOnTouchListener((View.OnTouchListener) this);
        buttonNumber3.setOnTouchListener((View.OnTouchListener) this);
        buttonNumber4.setOnTouchListener((View.OnTouchListener) this);
        buttonNumber5.setOnTouchListener((View.OnTouchListener) this);
        buttonNumber6.setOnTouchListener((View.OnTouchListener) this);
        buttonNumber7.setOnTouchListener((View.OnTouchListener) this);
        buttonNumber8.setOnTouchListener((View.OnTouchListener) this);
        buttonNumber9.setOnTouchListener((View.OnTouchListener) this);

        buttonClear.setOnTouchListener((View.OnTouchListener) this);
        buttonBackspace.setOnTouchListener((View.OnTouchListener) this);
        buttonDivision.setOnTouchListener((View.OnTouchListener) this);
        buttonMultiplication.setOnTouchListener((View.OnTouchListener) this);
        buttonSubtraction.setOnTouchListener((View.OnTouchListener) this);
        buttonAddition.setOnTouchListener((View.OnTouchListener) this);
        buttonDot.setOnTouchListener((View.OnTouchListener) this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.button_zero) {
            if (addNumber("0")) equalClicked = false;
        } else if (viewId == R.id.button_one) {
            if (addNumber("1")) equalClicked = false;
        } else if (viewId == R.id.button_two) {
            if (addNumber("2")) equalClicked = false;
        } else if (viewId == R.id.button_three) {
            if (addNumber("3")) equalClicked = false;
        } else if (viewId == R.id.button_four) {
            if (addNumber("4")) equalClicked = false;
        } else if (viewId == R.id.button_five) {
            if (addNumber("5")) equalClicked = false;
        } else if (viewId == R.id.button_six) {
            if (addNumber("6")) equalClicked = false;
        } else if (viewId == R.id.button_seven) {
            if (addNumber("7")) equalClicked = false;
        } else if (viewId == R.id.button_eight) {
            if (addNumber("8")) equalClicked = false;
        } else if (viewId == R.id.button_nine) {
            if (addNumber("9")) equalClicked = false;
        } else if (viewId == R.id.button_addition) {
            if (addOperand("+")) equalClicked = false;
        } else if (viewId == R.id.button_subtraction) {
            if (addOperand("-")) equalClicked = false;
        } else if (viewId == R.id.button_multiplication) {
            if (addOperand("x")) equalClicked = false;
        } else if (viewId == R.id.button_division) {
            if (addOperand("\u00F7")) equalClicked = false;
        }  else if (viewId == R.id.button_dot) {
            if (addDot()) equalClicked = false;
        } else if (viewId == R.id.button_backspace) {
            if (handleBackspace()) equalClicked = false;
        } else if (viewId == R.id.button_Inverse) {
            if (inverseNumber()) equalClicked = false;
        } else if (viewId == R.id.button_pow2) {
            if (squareNumber()) equalClicked = false;
        } else if (viewId == R.id.button_racineCarree) {
            if (calculateSquareRoot()) equalClicked = false;
        }else if (viewId == R.id.button_ValeureAbsolue) {
            if (calculateAbsoluteValue()) equalClicked = false;
        }else if (viewId == R.id.button_pi) {
            if (calculatePi()) equalClicked = false;
        }else if (viewId == R.id.button_exponentielle) {
            if (calculateExponential()) equalClicked = false;
        }else if (viewId == R.id.button_factorielle) {
            if (calculateFactorial()) equalClicked = false;
        }else if (viewId == R.id.button_puissance) {
            if (addPower()) equalClicked = false;
        }else if (viewId == R.id.button_clear) {
            textViewInputNumbers.setText("");
            dotUsed = false;
            equalClicked = false;
        } else if (viewId == R.id.button_equal) {
            if (textViewInputNumbers.getText().toString() != null && !textViewInputNumbers.getText().toString().equals(""))
                calculate(textViewInputNumbers.getText().toString());
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        switch (motionEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                view.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                view.invalidate();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                view.getBackground().clearColorFilter();
                view.invalidate();
                break;
            }
        }
        return false;
    }
    private boolean addPower() {
        boolean done = false;
        int operationLength = textViewInputNumbers.getText().length();

        if (operationLength > 0) {
            String lastInput = textViewInputNumbers.getText().charAt(operationLength - 1) + "";

            if (lastInput.equals("+") || lastInput.equals("-") || lastInput.equals("*") || lastInput.equals("\u00F7") || lastInput.equals("%")) {
                Toast.makeText(getApplicationContext(), "Wrong format", Toast.LENGTH_LONG).show();
            } else {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + "^");
                dotUsed = false;
                equalClicked = false;
                lastExpression = "";
                done = true;
            }
        } else {
            Toast.makeText(getApplicationContext(), "Wrong Format. Operand Without any numbers?", Toast.LENGTH_LONG).show();
        }
        return done;
    }
    private boolean calculateFactorial() {
        boolean done = false;
        String input = textViewInputNumbers.getText().toString().trim();

        if (!input.isEmpty() && isLastCharacterNumber()) {
            double number = Double.parseDouble(input);
            if (number >= 0 && number == (long) number) {
                long result = calculateFactorial((long) number);
                textViewInputNumbers.setText(String.valueOf(result));
                dotUsed = false;
                equalClicked = false;
                lastExpression = "";
                done = true;
            } else {
                Toast.makeText(getApplicationContext(), "Invalid input for factorial", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Cannot calculate factorial. Invalid input.", Toast.LENGTH_LONG).show();
        }

        return done;
    }

    private boolean isLastCharacterNumber() {
        int length = textViewInputNumbers.length();
        return length > 0 && Character.isDigit(textViewInputNumbers.getText().charAt(length - 1));
    }

    private long calculateFactorial(long n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * calculateFactorial(n - 1);
        }
    }
    private boolean calculateExponential() {
        try {
            String inputText = textViewInputNumbers.getText().toString();

            if (!inputText.isEmpty()) {
                double inputValue = Double.parseDouble(inputText);

                double exponentialValue = Math.exp(inputValue);

                textViewInputNumbers.setText(String.valueOf(exponentialValue));
                return true;
            } else {
                Toast.makeText(getApplicationContext(), "Entrer un nombre avant de calculer l'exponentielle", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Format de nombre invalide", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    private boolean addDot()
    {
        boolean done = false;

        if (textViewInputNumbers.getText().length() == 0)
        {
            textViewInputNumbers.setText("0.");
            dotUsed = true;
            done = true;
        } else if (dotUsed == true)
        {
        } else if (defineLastCharacter(textViewInputNumbers.getText().charAt(textViewInputNumbers.getText().length() - 1) + "") == IS_OPERAND)
        {
            textViewInputNumbers.setText(textViewInputNumbers.getText() + "0.");
            done = true;
            dotUsed = true;
        } else if (defineLastCharacter(textViewInputNumbers.getText().charAt(textViewInputNumbers.getText().length() - 1) + "") == IS_NUMBER)
        {
            textViewInputNumbers.setText(textViewInputNumbers.getText() + ".");
            done = true;
            dotUsed = true;
        }
        return done;
    }
    private boolean calculateAbsoluteValue() {
        try {
            String inputText = textViewInputNumbers.getText().toString();

            if (!inputText.isEmpty()) {
                double inputValue = Double.parseDouble(inputText);
                double absoluteValue = Math.abs(inputValue);

                textViewInputNumbers.setText(String.valueOf(absoluteValue));
                return true;
            } else {
                Toast.makeText(getApplicationContext(), "Entrer un nombre avant de calculer la valeur absolue", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException e) {
            // Gestion de l'exception si le texte n'est pas un nombre valide
            Toast.makeText(getApplicationContext(), "Format de nombre invalide", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    private boolean calculatePi() {
        textViewInputNumbers.append(String.valueOf(Math.PI));
        return true;
    }
    private boolean handleBackspace() {
        String currentInput = textViewInputNumbers.getText().toString();
        if (!currentInput.isEmpty()) {
            char lastChar = currentInput.charAt(currentInput.length() - 1);
            if (lastChar == 'x' || lastChar == '\u00F7' || lastChar == '%' || lastChar == '+' || lastChar == '-') {
                // Si le dernier caractère est un opérateur, supprimez trois caractères pour gérer les opérations telles que "sin", "cos", etc.
                textViewInputNumbers.setText(currentInput.substring(0, currentInput.length() - 3));
            } else {
                textViewInputNumbers.setText(currentInput.substring(0, currentInput.length() - 1));
            }
            return true;
        }
        return false;
    }

    private boolean inverseNumber() {
        String currentInput = textViewInputNumbers.getText().toString();

        if (!currentInput.isEmpty()) {
            try {
                double number = Double.parseDouble(currentInput);

                if (number != 0) {
                    double result = 1 / number;

                    textViewInputNumbers.setText(formatResult(result));
                    return true;
                } else {
                    Toast.makeText(getApplicationContext(), "Division by zero is not allowed", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Invalid number format", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

    private String formatResult(double result) {
        // Utilisez une instance de DecimalFormat pour formater le résultat
        DecimalFormat decimalFormat = new DecimalFormat("#.########");
        return decimalFormat.format(result);
    }
    private boolean squareNumber() {
        String currentInput = textViewInputNumbers.getText().toString();

        if (!currentInput.isEmpty()) {
            try {
                double number = Double.parseDouble(currentInput);
                double squaredNumber = Math.pow(number, 2);

                textViewInputNumbers.setText(String.valueOf(squaredNumber));

                return true;
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Invalid input for squaring", Toast.LENGTH_SHORT).show();
            }
        }

        return false;
    }
    private boolean calculateSquareRoot() {
        String currentInput = textViewInputNumbers.getText().toString();

        if (!currentInput.isEmpty()) {
            try {
                double number = Double.parseDouble(currentInput);

                if (number >= 0) {
                    double squareRoot = Math.sqrt(number);

                    textViewInputNumbers.setText(String.valueOf(squareRoot));
                    return true;
                } else {
                    Toast.makeText(getApplicationContext(), "Cannot calculate square root of a negative number", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Invalid input for square root", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }
    private boolean addOperand(String operand)
    {
        boolean done = false;
        int operationLength = textViewInputNumbers.getText().length();
        if (operationLength > 0)
        {
            String lastInput = textViewInputNumbers.getText().charAt(operationLength - 1) + "";

            if ((lastInput.equals("+") || lastInput.equals("-") || lastInput.equals("*") || lastInput.equals("\u00F7") || lastInput.equals("%")))
            {
                Toast.makeText(getApplicationContext(), "Wrong format", Toast.LENGTH_LONG).show();
            } else if (operand.equals("%") && defineLastCharacter(lastInput) == IS_NUMBER)
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + operand);
                dotUsed = false;
                equalClicked = false;
                lastExpression = "";
                done = true;
            } else if (!operand.equals("%"))
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + operand);
                dotUsed = false;
                equalClicked = false;
                lastExpression = "";
                done = true;
            }
        } else
        {
            Toast.makeText(getApplicationContext(), "Wrong Format. Operand Without any numbers?", Toast.LENGTH_LONG).show();
        }
        return done;
    }

    private boolean addNumber(String number)
    {
        boolean done = false;
        int operationLength = textViewInputNumbers.getText().length();
        if (operationLength > 0)
        {
            String lastCharacter = textViewInputNumbers.getText().charAt(operationLength - 1) + "";
            int lastCharacterState = defineLastCharacter(lastCharacter);

            if (operationLength == 1 && lastCharacterState == IS_NUMBER && lastCharacter.equals("0"))
            {
                textViewInputNumbers.setText(number);
                done = true;
            } else if (lastCharacterState == IS_OPEN_PARENTHESIS)
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + number);
                done = true;
            } else if (lastCharacterState == IS_CLOSE_PARENTHESIS || lastCharacter.equals("%"))
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + "x" + number);
                done = true;
            } else if (lastCharacterState == IS_NUMBER || lastCharacterState == IS_OPERAND || lastCharacterState == IS_DOT)
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + number);
                done = true;
            }
        } else
        {
            textViewInputNumbers.setText(textViewInputNumbers.getText() + number);
            done = true;
        }
        return done;
    }


    private void calculate(String input)
    {
        String result = "";
        try
        {
            String temp = input;
            if (equalClicked)
            {
                temp = input + lastExpression;
            } else
            {
                saveLastExpression(input);
            }
            result = scriptEngine.eval(temp.replaceAll("%", "/100").replaceAll("x", "*").replaceAll("[^\\x00-\\x7F]", "/")).toString();
            BigDecimal decimal = new BigDecimal(result);
            result = decimal.setScale(8, BigDecimal.ROUND_HALF_UP).toPlainString();
            equalClicked = true;

        } catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Wrong Format", Toast.LENGTH_SHORT).show();
            return;
        }

        if (result.equals("Infinity"))
        {
            Toast.makeText(getApplicationContext(), "Division by zero is not allowed", Toast.LENGTH_SHORT).show();
            textViewInputNumbers.setText(input);

        } else if (result.contains("."))
        {
            result = result.replaceAll("\\.?0*$", "");
            textViewInputNumbers.setText(result);
        }
    }

    private void saveLastExpression(String input)
    {
        String lastOfExpression = input.charAt(input.length() - 1) + "";
        if (input.length() > 1)
        {
            if (lastOfExpression.equals(")"))
            {
                lastExpression = ")";
                int numberOfCloseParenthesis = 1;

                for (int i = input.length() - 2; i >= 0; i--)
                {
                    if (numberOfCloseParenthesis > 0)
                    {
                        String last = input.charAt(i) + "";
                        if (last.equals(")"))
                        {
                            numberOfCloseParenthesis++;
                        } else if (last.equals("("))
                        {
                            numberOfCloseParenthesis--;
                        }
                        lastExpression = last + lastExpression;
                    } else if (defineLastCharacter(input.charAt(i) + "") == IS_OPERAND)
                    {
                        lastExpression = input.charAt(i) + lastExpression;
                        break;
                    } else
                    {
                        lastExpression = "";
                    }
                }
            } else if (defineLastCharacter(lastOfExpression + "") == IS_NUMBER)
            {
                lastExpression = lastOfExpression;
                for (int i = input.length() - 2; i >= 0; i--)
                {
                    String last = input.charAt(i) + "";
                    if (defineLastCharacter(last) == IS_NUMBER || defineLastCharacter(last) == IS_DOT)
                    {
                        lastExpression = last + lastExpression;
                    } else if (defineLastCharacter(last) == IS_OPERAND)
                    {
                        lastExpression = last + lastExpression;
                        break;
                    }
                    if (i == 0)
                    {
                        lastExpression = "";
                    }
                }
            }
        }
    }

    private int defineLastCharacter(String lastCharacter)
    {
        try
        {
            Integer.parseInt(lastCharacter);
            return IS_NUMBER;
        } catch (NumberFormatException e)
        {
        }

        if ((lastCharacter.equals("+") || lastCharacter.equals("-") || lastCharacter.equals("x") || lastCharacter.equals("\u00F7") || lastCharacter.equals("%")))
            return IS_OPERAND;

        if (lastCharacter.equals("("))
            return IS_OPEN_PARENTHESIS;

        if (lastCharacter.equals(")"))
            return IS_CLOSE_PARENTHESIS;

        if (lastCharacter.equals("."))
            return IS_DOT;

        return -1;
    }

}