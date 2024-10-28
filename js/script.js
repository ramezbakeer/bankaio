// Select all input elements inside the element with class 'otp'
const otpInputs = document.querySelectorAll('.otp input');


otpInputs.forEach((input) => {
    input.addEventListener('input', () => {
        const currentInput = input;
        const nextInput = currentInput.nextElementSibling;

        // Allow only one character input
        if (currentInput.value.length > 1) {
            currentInput.value = currentInput.value.charAt(0); // Keep only the first character
        }

        // Move to the next input if the current one is filled
        if (nextInput && nextInput.hasAttribute('disabled') && currentInput.value !== "") {
            nextInput.removeAttribute('disabled');
            nextInput.focus();
        }
    });

    input.addEventListener('keyup', (e) => {
        if (e.key === "Backspace") {
            if (input.previousElementSibling) {
                input.value = ""; // Clear current input
                input.setAttribute("disabled", true); // Disable current input
                input.previousElementSibling.focus(); // Move focus to previous input
            }
        }
    });
});

// Clear inputs when the modal is closed
$('#exampleModalScrollable').on('hidden.bs.modal', () => {
    otpInputs.forEach(input => {
        input.value = ""; // Clear input value
        input.setAttribute('disabled', true); // Disable the input again
    });
    otpInputs[0].removeAttribute('disabled'); // Re-enable the first input
});

// Focus on the first input when the modal is shown
$('#exampleModalScrollable').on('shown.bs.modal', () => {
    otpInputs[0].focus(); // Focus the first input when modal is opened
});

// To show modal on form submission
document.querySelector('form').addEventListener('submit', (event) => {
    event.preventDefault(); // Prevent default form submission
    // Show the modal
    $('#exampleModalScrollable').modal('show'); // Show the modal
});
