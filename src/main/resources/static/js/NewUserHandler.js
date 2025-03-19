document.getElementById("newUserForm")
    .addEventListener("submit", function(event) {
    event.preventDefault();
    console.log("new User btn")
    const newUser = {
        username : document.getElementById("newUserName").value,
        password : document.getElementById("newPassword").value,
        phone_number : document.getElementById("newUserPhoneNumber").value,
        roles : [document.getElementById("newUserRole").value]
    }

    const jwtToken = localStorage.getItem("token")
    fetch("http://localhost:8080/api/admin/add", {
        method : "POST",
        headers : {
            "Content-Type" : "application/json",
            "Authorization" : "Bearer " + jwtToken
        },
        body : JSON.stringify(newUser)
    })
        .then(response => {
            if (response.ok) {
                //надо сюда 201 как-то засунуть
                switchToTab("usersTableTabButton")
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