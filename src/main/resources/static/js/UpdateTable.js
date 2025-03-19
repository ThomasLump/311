document.addEventListener("DOMContentLoaded", function () {
    if (window.location.pathname === "/admin/") {
        console.log("page init =_=")
        updateTable();
    }
});

document.getElementById("usersTableTabButton").addEventListener("click", function(){
    updateTable();
})

function switchToTab(tabSelector) {
    const tabE = document.getElementById(tabSelector);
    console.log(tabE);
    let tab = new bootstrap.Tab(tabE)
    tab.show();
}

function closeModal(modalId) {
    const modal = bootstrap.Modal.getInstance(document.getElementById(modalId));
    if (modal) {
        modal.hide();
    }
}

function updateTable() {
    fetch("http://localhost:8080/api/admin/users", {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById("usersTableBody");
            tableBody.innerHTML = "";

            data.forEach(user => {
                let row = `<tr>
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
                </tr>`;
                tableBody.innerHTML += row;
            });
        })
        .catch(error => console.error("Ошибка загрузки таблицы:", error));
}