document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('tax-form');
    const ssnInput = document.getElementById('ssn');
    const ssnError = document.getElementById('ssn-error');

    form.addEventListener('submit', function(event) {
        ssnError.textContent = '';

        if (!validateSSN(ssnInput.value)) {
            event.preventDefault(); // Prevent form submission
            ssnError.textContent = 'Please enter a valid Social Security Number (format: XXX-XX-XXXX).';
        } else {
            event.preventDefault(); // Prevent the default form submission
            showPrintPreview(); // Call the function to show print preview
        }
    });

    function validateSSN(ssn) {
        const ssnPattern = /^\d{3}-\d{2}-\d{4}$/; // Pattern: XXX-XX-XXXX
        return ssnPattern.test(ssn);
    }

    function showPrintPreview() {
        const formData = {
            fullName: document.getElementById('full-name').value,
            address: document.getElementById('address').value,
            ssn: document.getElementById('ssn').value,
            primaryIncome: document.getElementById('primary-income').value,
            additionalIncome: document.getElementById('additional-income').value,
            medicalExpenses: document.getElementById('medical-expenses').value,
            educationalCosts: document.getElementById('educational-costs').value,
            otherExpenses: document.getElementById('other-expenses').value
        };

        // Redirect to print-preview.html, passing form data via query parameters
        const queryString = new URLSearchParams(formData).toString();
        window.location.href = `print-preview.html?${queryString}`;
    }

    // Function to format date and time
    function getCurrentDateTime() {
        const now = new Date();
        const day = String(now.getDate()).padStart(2, '0'); // Two-digit day
        const month = String(now.getMonth() + 1).padStart(2, '0'); // Two-digit month (0-indexed)
        const year = now.getFullYear();
        const hours = String(now.getHours()).padStart(2, '0'); // Two-digit hours
        const minutes = String(now.getMinutes()).padStart(2, '0'); // Two-digit minutes
        const seconds = String(now.getSeconds()).padStart(2, '0'); // Two-digit seconds
        return `Today : ${day}-${month}-${year} ${hours}:${minutes}:${seconds}`; // Return formatted date string
    }

    // Set current date and time
    document.getElementById('current-date-time').textContent = getCurrentDateTime();
});



