'use client'

import axios from 'axios';
import { useState } from 'react';

import '../styles.css';

const LoginForms = () => {
    // Estados para os campos de entrada
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');

    // Função para lidar com a mudança nos campos
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
    
    const handleSubmit = (event: any) => {
        event.preventDefault(); // Previne o comportamento padrão de recarga da página
        axios.get('http://localhost:8082/login', {
            params: {
                email: email,
                senha: senha,
            },
        }).then((response) => {
            console.log('Login realizado com sucesso:', response.data);
            // Redireciona para outra página após o login bem-sucedido
            window.location.href = '/Products';
        }).catch((error) => {
            console.error('Erro ao realizar login:', error);
        });
    };

    return(
        <>
        <div className='flex form'>
            <form className="form-login" onSubmit={handleSubmit}>
                <label htmlFor='email'>Email do Usuário</label>
                <input className="input" type="text" id="email" name="email" placeholder="email@email.com" value={email} onChange={handleInputChange}/>
                
                <label htmlFor='senha'>Senha</label>
                <input className="input" type="password" id="senha" name="senha" placeholder="Digite sua senha aqui" value={senha} onChange={handleInputChange}/>
                
                <input type="submit" className="login-btn" value="Login"/>
            </form>
        </div>
        </>
    );
}

export default LoginForms;
