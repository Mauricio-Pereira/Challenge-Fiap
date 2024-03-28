import Button from '../../Button';
import '../styles.css';

const ProductForms = () => {
    return(
        <>
        <div className='flex form'>
            <form className="form-login">
                <label htmlFor='nome'>Nome do usuário</label>
                <input className="input" type="text" placeholder=" email@email.com" />
                
                <label htmlFor='senha'>Senha</label>
                <input className="input" type="password" placeholder=" Digite sua senha aqui" />
                
                <Button type="submit" style="login-btn" link="/Products" text="Login" />
            </form>
        </div>
        </>
    );
}

export default ProductForms;
