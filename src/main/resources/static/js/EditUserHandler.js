document.getElementById("edituserModal")
    .addEventListener("submit",function(event) {
        event.preventDefault()
        console.log("edit User btn")
        const editUserData = {
            id : document.getElementById("editmodalInputId").value,
            username : document.getElementById("editmodalInputName").value,
            phone_number: document.getElementById("editmodalInputPhonenumber").value,
            role : document.getElementById()
        }

        const jwtToken = localStorage.getItem("token")
        fetch("http://localhost:8080/api/admin/update", {
            method : "PATCH",
            headers : {
                "Content-Type" : "application/json",
                "Authorization" : "Bearer " + jwtToken
            },
            body : JSON.stringify(editUserData)
        })
            .then(response => {
                if (response.ok) {
                    //надо сюда 204 как-то засунуть
                    closeModal("edituserModal")
                    updateTable()
                }
                else {
                    alert("404")
                }
            })
            .catch(error => {
                alert("Error updating user");
                console.error("Error:", error);
            })
    })

