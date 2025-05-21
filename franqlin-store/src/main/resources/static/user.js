// Replace this with actual logged-in user ID, maybe from login response or localStorage
const userId = localStorage.getItem('userId') || 1; // example userId fallback

const manageAddressBtn = document.getElementById('manageAddressBtn');
const addressContainer = document.getElementById('addressContainer');
const addressForm = document.getElementById('addressForm');
const addressesTableBody = document.querySelector('#addressesTable tbody');

manageAddressBtn.addEventListener('click', () => {
    addressContainer.style.display = 'block';
    loadAddresses();
});

// Load addresses from backend for this user
async function loadAddresses() {
    try {
        const response = await fetch(`/api/addresses/${userId}`);
        if (!response.ok) throw new Error('Failed to fetch addresses');
        const addresses = await response.json();
        renderAddresses(addresses);
    } catch (err) {
        alert(err.message);
    }
}

// Render addresses in the table
function renderAddresses(addresses) {
    addressesTableBody.innerHTML = '';
    if (addresses.length === 0) {
        addressesTableBody.innerHTML = `<tr><td colspan="6" style="text-align:center;">No addresses found.</td></tr>`;
        return;
    }
    addresses.forEach(addr => {
        const tr = document.createElement('tr');

        tr.innerHTML = `
            <td>${addr.street}</td>
            <td>${addr.city}</td>
            <td>${addr.state}</td>
            <td>${addr.postalCode}</td>
            <td>${addr.country}</td>
            <td><button class="deleteBtn" data-id="${addr.id}" style="background-color: #ef4444; color: white; border:none; border-radius:6px; padding:6px 12px; cursor:pointer;">Delete</button></td>
        `;

        addressesTableBody.appendChild(tr);
    });

    // Attach delete event listeners
    document.querySelectorAll('.deleteBtn').forEach(btn => {
        btn.addEventListener('click', async (e) => {
            const addressId = e.target.getAttribute('data-id');
            if (confirm('Are you sure you want to delete this address?')) {
                await deleteAddress(addressId);
                loadAddresses();
            }
        });
    });
}

// Save new address
addressForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const addressData = {
        street: addressForm.street.value.trim(),
        city: addressForm.city.value.trim(),
        state: addressForm.state.value.trim(),
        postalCode: addressForm.postalCode.value.trim(),
        country: addressForm.country.value.trim(),
    };

    try {
        const response = await fetch(`/api/addresses/${userId}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(addressData),
        });
        if (!response.ok) throw new Error('Failed to save address');
        alert('Address saved successfully!');
        addressForm.reset();
        loadAddresses();
    } catch (err) {
        alert(err.message);
    }
});

// Delete address by ID
async function deleteAddress(addressId) {
    try {
        const response = await fetch(`/api/addresses/${addressId}`, {
            method: 'DELETE',
        });
        if (!response.ok) throw new Error('Failed to delete address');
        alert('Address deleted successfully!');
    } catch (err) {
        alert(err.message);
    }
}
