import { getUsers, addUser, updateUser, deleteUser } from '../api/users.js';
import { getRoles } from '../api/roles.js';

export function initializeAdminPanel() {
    initializeUserTable();
    initializeNewUserForm();
    initializeModals();
}

async function initializeUserTable() {
    const users = await getUsers();
    const tbody = document.getElementById('usersTableBody');
    if (!tbody) return;

    tbody.innerHTML = users.map(user => `
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.phone_number}</td>
            <td>
                <button type="button" class="btn btn-danger btn-sm"
                        data-bs-toggle="modal"
                        data-bs-target="#deleteModal"
                        data-id="${user.id}"
                        data-name="${user.username}"
                        data-phonenumber="${user.phone_number}">
                    delete
                </button>
            </td>
            <td>
                <button type="button" class="btn btn-primary btn-sm"
                        data-bs-toggle="modal"
                        data-bs-target="#edituserModal"
                        data-id="${user.id}"
                        data-name="${user.username}"
                        data-phonenumber="${user.phone_number}">
                    edit
                </button>
            </td>
        </tr>
    `).join('');
}

function initializeNewUserForm() {
    const newUserTabContent = document.getElementById('newUserTabContent');
    if (!newUserTabContent) return;

    // Создаем форму для нового пользователя
    newUserTabContent.innerHTML = `
        <div class="border border-1 bg-white p-3">
            <h5 class="text-dark mb-3">Add new user</h5>
            <form id="newUserForm">
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div class="mb-3">
                    <label for="phone_number" class="form-label">Phone Number</label>
                    <input type="text" class="form-control" id="phone_number" name="phone_number" required>
                </div>
                <div class="mb-3">
                    <label for="newuserrolename" class="form-label">Role</label>
                    <select class="form-select" id="newuserrolename" name="newuserrolename" required>
                        <option value="">Select role</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Add User</button>
            </form>
        </div>
    `;

    // Заполняем список ролей
    const roleSelect = document.getElementById('newuserrolename');
    if (roleSelect) {
        getRoles().then(roles => {
            roleSelect.innerHTML = `
                <option value="">Select role</option>
                ${roles.map(role => `<option value="${role}">${role}</option>`).join('')}
            `;
        });
    }

    // Обработчик отправки формы
    const form = document.getElementById('newUserForm');
    if (form) {
        form.addEventListener('submit', async (e) => {
            e.preventDefault();
            const formData = new FormData(form);
            const userData = {
                username: formData.get('username'),
                password: formData.get('password'),
                phone_number: formData.get('phone_number'),
                roles: [formData.get('newuserrolename')]
            };

            try {
                await addUser(userData);
                form.reset();
                await initializeUserTable();
            } catch (error) {
                console.error('Error adding user:', error);
            }
        });
    }
}

function initializeModals() {
    // Модальное окно удаления
    const deleteModal = document.getElementById('deleteModal');
    if (deleteModal) {
        deleteModal.innerHTML = `
            <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteModalLabel">Delete User</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p>Are you sure you want to delete this user?</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            <button type="button" class="btn btn-danger">Delete</button>
                        </div>
                    </div>
                </div>
            </div>
        `;

        deleteModal.addEventListener('show.bs.modal', (event) => {
            const button = event.relatedTarget;
            const id = button.getAttribute('data-id');
            const name = button.getAttribute('data-name');
            const phoneNumber = button.getAttribute('data-phonenumber');

            const modalBody = deleteModal.querySelector('.modal-body');
            modalBody.innerHTML = `
                <p>Are you sure you want to delete user "${name}"?</p>
                <p>Phone: ${phoneNumber}</p>
            `;

            const confirmButton = deleteModal.querySelector('.btn-danger');
            confirmButton.onclick = async () => {
                try {
                    await deleteUser(id);
                    await initializeUserTable();
                } catch (error) {
                    console.error('Error deleting user:', error);
                }
            };
        });
    }

    // Модальное окно редактирования
    const editModal = document.getElementById('edituserModal');
    if (editModal) {
        editModal.addEventListener('show.bs.modal', (event) => {
            const button = event.relatedTarget;
            const id = button.getAttribute('data-id');
            const name = button.getAttribute('data-name');
            const phoneNumber = button.getAttribute('data-phonenumber');

            const form = editModal.querySelector('form');
            form.querySelector('#editmodalInputId').value = id;
            form.querySelector('#editmodalInputName').value = name;
            form.querySelector('#editmodalInputPhonenumber').value = phoneNumber;

            form.onsubmit = async (e) => {
                e.preventDefault();
                const formData = new FormData(form);
                const userData = {
                    id: parseInt(formData.get('userId')),
                    username: formData.get('name'),
                    phone_number: formData.get('phonenumber'),
                    roles: ['user'] // По умолчанию роль user
                };

                try {
                    await updateUser(userData);
                    await initializeUserTable();
                } catch (error) {
                    console.error('Error updating user:', error);
                }
            };
        });
    }
} 