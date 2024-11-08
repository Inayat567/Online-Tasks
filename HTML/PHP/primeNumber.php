<?php
// Function to check if a number is prime
function isPrime($number) {
    // Handle numbers less than 2
    if ($number < 2) {
        return false;
    }
    // Check for factors from 2 to the square root of the number
    for ($i = 2; $i <= sqrt($number); $i++) {
        if ($number % $i == 0) {
            return false;
        }
    }
    return true;
}

// Prompt the user to enter a number
echo "Please enter a number: ";
$number = trim(fgets(STDIN));

// Validate the input to ensure it's a number
if (is_numeric($number)) {
    $number = (int)$number;
    // Call the function and display the result
    if (isPrime($number)) {
        echo "$number is a prime number.\n";
    } else {
        echo "$number is not a prime number.\n";
    }
} else {
    echo "Invalid input. Please enter a valid number.\n";
}
?>
