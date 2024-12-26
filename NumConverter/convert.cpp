#include <iostream>
using namespace std;

// Function to convert a number from any base to decimal
int toDecimal(const string& num, int base) {
    int decimalValue = 0;
    int power = 1; // Base raised to the power of 0 is 1

    for (int i = num.length() - 1; i >= 0; --i) {
        char digit = num[i];
        int digitValue;

        if (digit >= '0' && digit <= '9') {
            digitValue = digit - '0';
        } else if (digit >= 'A' && digit <= 'F') {
            digitValue = digit - 'A' + 10;
        } else if (digit >= 'a' && digit <= 'f') {
            digitValue = digit - 'a' + 10;
        } else {
            throw invalid_argument("Invalid digit in input number");
        }

        if (digitValue >= base) {
            throw invalid_argument("Digit exceeds base range");
        }

        decimalValue += digitValue * power;
        power *= base;
    }

    return decimalValue;
}

// Function to convert a decimal number to any base
string fromDecimal(int decimal, int base) {
    if (decimal == 0) return "0";

    string result;
    char buffer[33];
    int index = 0;

    while (decimal > 0) {
        int remainder = decimal % base;
        if (remainder < 10) {
            buffer[index++] = '0' + remainder;
        } else {
            buffer[index++] = 'A' + (remainder - 10);
        }
        decimal /= base;
    }

    for (int i = 0; i < index / 2; ++i) {
        char temp = buffer[i];
        buffer[i] = buffer[index - 1 - i];
        buffer[index - 1 - i] = temp;
    }

    buffer[index] = '\0';
    return string(buffer);
}

int main() {
    char inputNumber[33];
    int inputBase, outputBase;

    
    cin >> inputNumber;

    
    cin >> inputBase;

    cin >> outputBase;

    try {
        // Convert input number to decimal
        int decimalValue = toDecimal(inputNumber, inputBase);

        // Convert decimal number to the desired base
        string outputNumber = fromDecimal(decimalValue, outputBase);

        cout  << outputNumber << endl;
    } catch (const exception& e) {
        cout << "Error: " << e.what() << endl;
    }

    return 0;
}
