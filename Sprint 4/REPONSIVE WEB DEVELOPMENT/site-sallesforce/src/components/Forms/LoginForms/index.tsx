'use client'

import axios from 'axios';
import { useState } from 'react';
import '../styles.css';

const LoginForms = () => {
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');
    const [errorMessage, setErrorMessage] = useState(''); // Estado para armazenar a mensagem de erro

    const handleInputChange = (event: any) => {
        switch (event.target.name) {
            case 'email':
                setEmail(event.target.value);
                break;
            case 'senha':
                setSenha(event.target.value);
                break;
            default:
                break;
        }
    };

    const handleSubmit = async (event: any) => {
        event.preventDefault(); // Previne o comportamento padrão de recarga da página

        try {
            const response = await axios.post('http://localhost:8082/salesforcedirections/login', {
                email: email,
                senha: senha,
            }, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            console.log('Login realizado com sucesso:', response.data);
            localStorage.setItem('clienteEmail', email); // Armazena o email no localStorage
            window.location.href = '/Products'; // Redireciona para outra página após o login bem-sucedido
        } catch (error) {
            if (axios.isAxiosError(error)) {
                console.error('Erro ao realizar login:', error.response?.data || error.message);
                setErrorMessage(error.response?.data || 'Erro ao realizar login.');
            } else {
                console.error('Erro desconhecido ao realizar login:', error);
                setErrorMessage('Erro desconhecido ao realizar login.');
            }
        }
    };

    return (
        <div className='flex form'>
            <form className="form-login" onSubmit={handleSubmit}>
                <label htmlFor='email'>Email do Usuário</label>
                <input className="input" type="text" id="email" name="email" placeholder="email@email.com" value={email} onChange={handleInputChange} />

                <label htmlFor='senha'>Senha</label>
                <input className="input" type="password" id="senha" name="senha" placeholder="Digite sua senha aqui" value={senha} onChange={handleInputChange} />

                <input type="submit" className="login-btn" value="Login" />

                {errorMessage && <p className="error-message">{errorMessage}</p>} {/* Exibe a mensagem de erro */}
            </form>
        </div>
    );
}

export default LoginForms;
