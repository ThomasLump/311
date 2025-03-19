document.getElementById("login-form").addEventListener('submit', function (event) {
    event.preventDefault();

    const username = document.querySelector('input[name="username"]').value;
    const password = document.querySelector('input[name="password"]').value;
    console.log("login interceptor")

    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({
            username: username,
            password: password
        })
    })
        .then(response => {
            if (response.ok) {
                const token = response.headers.get('Authorization');
                if (token) {
                    localStorage.setItem('token', token.replace('Bearer ', ''));
                }

            } else {
                alert('Ошибка авторизации');
            }
            return response.json()
        })
        .then(data => {
            window.location.href = data.redirectUrl
        })
        .catch(error => {
            console.error('Ошибка при авторизации:', error);
            alert('Произошла ошибка при отправке запроса');
        });
});
