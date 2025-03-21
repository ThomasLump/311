import { getCurrentUser, isAdmin } from './api/auth.js';
import { updateAside } from './components/aside.js';
import { initializeAdminPanel } from './components/admin-panel.js';
import { initializeUserPanel } from './components/user-panel.js';

async function initializePage() {
    const user = await getCurrentUser();
    if (!user) return;

    const headerTitle = document.querySelector('header h2');
    if (headerTitle) {
        headerTitle.textContent = `Hello, ${user.username}!`;
    }

    await updateAside();

    const adminPanel = document.getElementById('adminPanel');
    const userPanel = document.getElementById('userPanel');
    
    if (isAdmin(user)) {
        adminPanel.style.display = 'block';
        initializeAdminPanel();
    }
    
    if (user.roles.includes('user')) {
        userPanel.style.display = 'block';
        initializeUserPanel(user);
    }
}

document.addEventListener('DOMContentLoaded', initializePage); 