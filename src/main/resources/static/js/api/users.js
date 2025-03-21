import { getAuthHeader } from './auth.js';

export async function getUsers() {
    const response = await fetch('/api/admin/users', {
        headers: {
            'Authorization': getAuthHeader()
        }
    });
    if (!response.ok) throw new Error('Failed to fetch users');
    return response.json();
}

export async function addUser(userData) {
    const response = await fetch('/api/admin/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': getAuthHeader()
        },
        body: JSON.stringify(userData)
    });
    if (!response.ok) throw new Error('Failed to add user');
}

export async function updateUser(userData) {
    const response = await fetch('/api/admin/update', {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': getAuthHeader()
        },
        body: JSON.stringify(userData)
    });
    if (!response.ok) throw new Error('Failed to update user');
}

export async function deleteUser(id) {
    const response = await fetch(`/api/admin/delete?id=${id}`, {
        method: 'DELETE',
        headers: {
            'Authorization': getAuthHeader()
        }
    });
    if (!response.ok) throw new Error('Failed to delete user');
} 