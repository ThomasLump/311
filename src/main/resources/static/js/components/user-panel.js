export function initializeUserPanel(user) {
    const tbody = document.getElementById('userInfoTableBody');
    if (!tbody) return;

    tbody.innerHTML = `
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.phone_number}</td>
            <td>${user.roles.join(', ')}</td>
        </tr>
    `;
} 