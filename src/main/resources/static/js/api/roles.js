export async function getRoles() {
    const response = await fetch('/api/user/roles');
    if (!response.ok) throw new Error('Failed to fetch roles');
    return response.json();
} 