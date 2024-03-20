import './style.css'

const Header = () => {
  return (
    <>
        <header> 
        <div className="caixa">
            <h1> <img className="logo" src="/logoSalesForce.png" alt="" /></h1> 

            <nav>
                    <ul className='lista'>
                        <li> <a className='item-lista' href=""> Pagina Inicial </a> </li>
                        <li> <a className='item-lista' href=""> A SalesForce </a> </li>
                        <li> <a className='item-lista' href=""> Login </a> </li>
                        <li> <a className='item-lista' href=""> Integrantes</a></li>
                    </ul>
            </nav>

            <button className='teste-gratis'> Teste Grátis </button>
        </div>
        
    
    </header>
    </>
  );
}

export default Header;