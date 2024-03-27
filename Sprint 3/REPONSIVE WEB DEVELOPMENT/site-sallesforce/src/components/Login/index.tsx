import '../Login/style.css';

const Login = () => {
    return(
        <>
        <section className='section'>
        <img className='imagem-logo' src="./logoSalesForce.png" alt="" />
            <form className="form-login">
                <h1 className='titulo'> Bem Vindo!</h1>
                <div className='conteudo'>
                    <h3 className='texto-input'>Nome do usuário</h3>
                    <input className="input" type="text" placeholder=" email@email.com" />
                    <h3 className='texto-input'>Senha</h3>
                    <input className="input" type="password" placeholder=" Digite sua senha aqui" />
                    <button className='botao-login' type="submit">Login</button>
                </div>
            </form>
        </section>
        </>
    );
}

export default Login;
