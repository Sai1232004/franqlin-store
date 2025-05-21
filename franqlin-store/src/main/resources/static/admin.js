document.addEventListener('DOMContentLoaded', () => {
    fetch('/api/users') // Make sure this matches your Spring Boot endpoint
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not OK');
            }
            return response.json();
        })
        .then(users => {
            const tableBody = document.querySelector('#userTable tbody');
            tableBody.innerHTML = ''; // Clear any existing rows

            users.forEach(user => {
                const row = document.createElement('tr');

                row.innerHTML = `
                    <td>${user.id}</td>
                    <td>${user.fullName}</td>
                    <td>${user.email}</td>
                    <td>${user.address}</td>
                    <td>${user.phone}</td>
                `;

                tableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error fetching user data:', error);
            alert('Failed to load user data. Please try again later.');
        });
});
