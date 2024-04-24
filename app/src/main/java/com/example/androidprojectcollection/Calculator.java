package com.example.androidprojectcollection;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

public class Calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        StringBuilder equation = new StringBuilder();

        final boolean[] parIsOpen = {false};

        TextView txtEquation = findViewById(R.id.txtEquation);
        TextView txtResult = findViewById(R.id.txtResult);

        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(findViewById(R.id.btnNum0));
        buttons.add(findViewById(R.id.btnNum1));
        buttons.add(findViewById(R.id.btnNum2));
        buttons.add(findViewById(R.id.btnNum3));
        buttons.add(findViewById(R.id.btnNum4));
        buttons.add(findViewById(R.id.btnNum5));
        buttons.add(findViewById(R.id.btnNum6));
        buttons.add(findViewById(R.id.btnNum7));
        buttons.add(findViewById(R.id.btnNum8));
        buttons.add(findViewById(R.id.btnNum9));
        buttons.add(findViewById(R.id.btnDiv));
        buttons.add(findViewById(R.id.btnMul));
        buttons.add(findViewById(R.id.btnSub));
        buttons.add(findViewById(R.id.btnAdd));
        buttons.add(findViewById(R.id.btnDot));

        Button btnAllClr = findViewById(R.id.btnAllClr);
        Button btnClr = findViewById(R.id.btnClr);
        Button btnEqu = findViewById(R.id.btnEqu);
        Button btnPar = findViewById(R.id.btnPar);

        AtomicBoolean hasDecimal = new AtomicBoolean(false);

        for (Button button : buttons) {
            button.setOnClickListener(v -> {
                CharSequence text = button.getText();
                if (text.equals("+") || text.equals("-") || text.equals("*") || text.equals("/")) {
                    if (equation.length() == 0) return;
                    if (equation.toString().endsWith(" ")) {
                        equation.delete(equation.length() - 3, equation.length() - 1);
                    } else if (equation.toString().endsWith(".")) {
                        equation.deleteCharAt(equation.length() - 1);
                    }
                    hasDecimal.set(false);
                    equation.append(" ").append(button.getText()).append(" ");
                } else {
                    if (text.equals(".")) {
                        if (hasDecimal.get()) return;
                        if (equation.toString().endsWith(" ") || equation.toString().equals("")) {
                            equation.append("0");
                        } else if (equation.toString().endsWith(".")) {
                            equation.deleteCharAt(equation.length() - 1);
                            txtResult.setText(equation);
                            return;
                        }
                        hasDecimal.set(true);
                    }
                    equation.append(button.getText());
                }
                txtResult.setText(equation);
                String calculation;
                try {
                    calculation = calculatePartial(equation.toString());
                    if (calculation.endsWith(".0")) {
                        calculation = calculation.substring(0, calculation.length() - 2);
                    }
                } catch (Exception e) {
                    calculation = "Error";
                }
                txtEquation.setText(calculation);
            });
        }

        btnAllClr.setOnClickListener(v -> {
            equation.setLength(0);
            txtEquation.setText("");
            txtResult.setText("");
            parIsOpen[0] = false;
        });

        btnClr.setOnClickListener(v -> {
            if (equation.toString().equals("")) return;
            switch (equation.charAt(equation.length() - 1)) {
                case ' ':
                    for (int i = 0; i < 3; i++) {
                        equation.deleteCharAt(equation.length() - 1);
                    }
                    break;
                case '(':
                    parIsOpen[0] = false;
                    equation.deleteCharAt(equation.length() - 1);
                    break;
                case ')':
                    parIsOpen[0] = true;
                    equation.deleteCharAt(equation.length() - 1);
                    break;
                default:
                    equation.deleteCharAt(equation.length() - 1);
            }
            txtResult.setText(equation);
            String calculation;
            try {
                calculation = calculatePartial(equation.toString());
                if (calculation.endsWith(".0")) {
                    calculation = calculation.substring(0, calculation.length() - 2);
                }
            } catch (Exception e) {
                calculation = "Error";
            }
            txtEquation.setText(calculation);
        });

        btnEqu.setOnClickListener(v -> {
            txtEquation.setText(equation);
            String calculation;
            try {
                calculation = calculate(equation.toString());
                if (calculation.endsWith(".0")) {
                    calculation = calculation.substring(0, calculation.length() - 2);
                }
            } catch (Exception e) {
                calculation = "Error";
            }
            txtResult.setText(calculation);
        });

        btnPar.setOnClickListener(v -> {
            if (parIsOpen[0]) {
                equation.append(")");
            } else {
                equation.append("(");
            }
            parIsOpen[0] = !parIsOpen[0];
            txtResult.setText(equation);
        });

    }

    public String calculatePartial(String equation) {
        if (equation.endsWith(" ")) return "";
        equation = equation.replaceAll("[()]", "");
        String[] operations = equation.split("\\s+");
        double result = Double.parseDouble(operations[0]);

        for (int i = 1; i < operations.length; i += 2) {
            String operator = operations[i];
            try {
                double operand = Double.parseDouble(operations[i + 1]);
                switch (operator) {
                    case "+":
                        result += operand;
                        break;
                    case "-":
                        result -= operand;
                        break;
                    case "*":
                        result *= operand;
                        break;
                    case "/":
                        if (operand != 0) {
                            result /= operand;
                        }
                        break;
                }
            } catch (Exception e) {
                return "";
            }
        }

        return String.valueOf(result);
    }

    public String calculate(String equation) {
        Stack<Double> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();
        for (int i = 0; i < equation.length(); i++) {
            char c = equation.charAt(i);
            if (Character.isDigit(c)) {
                StringBuilder num = new StringBuilder();
                while (i < equation.length() && (Character.isDigit(equation.charAt(i)) || equation.charAt(i) == '.')) {
                    num.append(equation.charAt(i++));
                }
                i--;
                operands.push(Double.parseDouble(num.toString()));
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    calculate(operands, operators);
                }
                operators.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
                    calculate(operands, operators);
                }
                operators.push(c);
            }
        }
        while (!operators.isEmpty()) {
            calculate(operands, operators);
        }
        return operands.isEmpty() ? "Error" : String.valueOf(operands.pop());
    }
    private void calculate(Stack<Double> operands, Stack<Character> operators) {
        char operator = operators.pop();
        double operand2 = operands.pop();
        double operand1 = operands.pop();
        switch (operator) {
            case '+':
                operands.push(operand1 + operand2);
                break;
            case '-':
                operands.push(operand1 - operand2);
                break;
            case '*':
                operands.push(operand1 * operand2);
                break;
            case '/':
                if (operand2 != 0) {
                    operands.push(operand1 / operand2);
                } else {
                    operands.clear();
                }
                break;
        }
    }
    private int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }
}