import { getRoles } from '../api/roles.js';

// Функция для обновления бокового меню
export async function updateAside() {
    const roles = await getRoles();
    const asideElement = document.getElementById('roleList');
    if (!asideElement) return;

    asideElement.innerHTML = roles.map(role => `
        <li class="list-group-item">
            <a href="#" class="text-decoration-none text-dark">${role}</a>
        </li>
    `).join('');
} 