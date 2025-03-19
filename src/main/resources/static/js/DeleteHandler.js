document.getElementById("deleteModal")
    .addEventListener("submit",function(event) {
    event.preventDefault()
    console.log("delete User btn")
    const deleteUserId = document.getElementById("modalInputId").value

    const jwtToken = localStorage.getItem("token")
    fetch("http://localhost:8080/api/admin/delete/" + deleteUserId, {
        method : "DELETE",
        headers : {
            "Content-Type" : "application/json",
            "Authorization" : "Bearer " + jwtToken
        }
    })
        .then(response => {
            if (response.ok) {
                //надо сюда 204 как-то засунуть
                closeModal("deleteModal")
                updateTable()
            }
            else {
                alert("404")
            }
        })
        .catch(error => {
            alert("Error adding user");
            console.error("Error:", error);
        })
})

