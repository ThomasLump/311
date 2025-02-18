    const deleteModal = document.getElementById('deleteModal');
    const editModal = document.getElementById('edituserModal');

    editModal.addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget; // Кнопка, которая открыла модальное окно
        const id = button.getAttribute('data-id');
        const name = button.getAttribute('data-name');
        const phone = button.getAttribute('data-phonenumber');
        console.log(button,id,name,phone);

        console.log(document.getElementById('editmodalInputId').value = id)
        document.getElementById('editmodalInputName').value = name;
        document.getElementById('editmodalInputPhonenumber').value = phone;
    });

    deleteModal.addEventListener('show.bs.modal', function (event) {

        const button = event.relatedTarget; // Кнопка, которая открыла модальное окно
        const id = button.getAttribute('data-id');
        const name = button.getAttribute('data-name');
        const phone = button.getAttribute('data-phonenumber');


        document.getElementById('modalInputId').value = id;
        document.getElementById('modalInputName').value = name;
        document.getElementById('modalInputPhonenumber').value = phone;
    });
