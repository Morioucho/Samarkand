const API_URL = 'http://localhost:8080/api/users';

const registerUser = async (username, password) => {
    const response = await fetch(`${API_URL}/register`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password })
    });

    if (!response.ok) {
        throw new Error('Registration failed');
    }

    return response.json();
};

const loginUser = async (username, password) => {
    const response = await fetch(`${API_URL}/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password })
    });

    if (!response.ok) {
        throw new Error('Login failed');
    }

    return response.json();
};

document.addEventListener('DOMContentLoaded', () => {
    const loginFormContainer = document.getElementById('loginFormContainer');
    const registerFormContainer = document.getElementById('registerFormContainer');
    const showLoginButton = document.getElementById('showLogin');
    const showRegisterButton = document.getElementById('showRegister');

    showLoginButton.addEventListener('click', () => {
        loginFormContainer.style.display = 'block';
        registerFormContainer.style.display = 'none';
    });

    showRegisterButton.addEventListener('click', () => {
        loginFormContainer.style.display = 'none';
        registerFormContainer.style.display = 'block';
    });

    const registerForm = document.getElementById('registerForm');
    const loginForm = document.getElementById('loginForm');
    const registerMessage = document.getElementById('registerMessage');
    const loginMessage = document.getElementById('loginMessage');

    if (registerForm) {
        registerForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            const username = document.getElementById('registerUsername').value;
            const password = document.getElementById('registerPassword').value;

            try {
                await registerUser(username, password);

                registerMessage.textContent = 'Registration successful';
                registerMessage.style.color = 'green';
            } catch (error) {
                registerMessage.textContent = 'Registration failed';
                registerMessage.style.color = 'red';
            }
        });
    }

    if (loginForm) {
        loginForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            const username = document.getElementById('loginUsername').value;
            const password = document.getElementById('loginPassword').value;

            try {
                await loginUser(username, password);
                loginMessage.textContent = 'Login successful';
                loginMessage.style.color = 'green';
            } catch (error) {
                loginMessage.textContent = 'Login failed';
                loginMessage.style.color = 'red';
            }
        });
    }
});