<!-- <?php
// Initialize a variable to hold the sum
$total = 0;

// Use a for loop to iterate through integers from 0 to 50
for ($i = 0; $i <= 50; $i++) {
    // Add the current integer to the total sum
    $total += $i;
}

// Display the total sum
echo "The total sum of integers from 0 to 50 is: " . $total;
?> -->



<?php
// Function to check if a person is eligible to vote
function checkVotingEligibility($age) {
    if ($age >= 18) {
        echo "You are eligible to vote.\n";
    } else {
        echo "You are not eligible to vote.\n";
    }
}

// Prompt the user to enter their age
echo "Please enter your age: ";
$age = trim(fgets(STDIN));

// Validate the input to ensure it's a number
if (is_numeric($age)) {
    // Call the function with the entered age
    checkVotingEligibility((int)$age);
} else {
    echo "Invalid input. Please enter a valid number.\n";
}
?>
