export async function getCurrentUser() {
    const response = await fetch('/api/user/header');
    if (!response.ok) throw new Error('Failed to fetch user');
    return response.json();
}

// Функция для проверки роли пользователя
export function isAdmin(user) {
    return user.roles.includes('admin');
}

export function getAuthHeader() {
    return localStorage.getItem('token');
} 