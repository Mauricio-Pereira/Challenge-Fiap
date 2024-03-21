import Button from "../Button";
import Link from "next/link";
import './styles.css';

const Header = () => {
    return(
        <>
        <header className="flex">
          <Link href="/"><img src="/logoSalesForce.png" alt="logoSalesForce.png" title="© Copyright 2023 Salesforce"/></Link>
          <nav>
            <ul className="flex">
              <li><Link href="/">Página Inicial</Link></li>
              <li><Link href="/Salesforce">A Salesforce</Link></li>
              <li><Link href="/Products">Produtos</Link></li>
              <li><Link href="/Members">Integrantes</Link></li>
            </ul>

            <div className="header-contact">
              <a href="https://www.salesforce.com/br/form/contact/contactme/?d=cta-header-9">Entre em contato <br/> 0800 891 1887</a>
            </div>

            <div className="login">
              <Link href="/Login"><img src="/profile-circle-svgrepo-com.svg" alt="Logo de login"/></Link>
              <Link href="/Login">Login</Link>
            </div>

            <Button type="free-trial-btn" link="/Free-Trial" text="Teste Grátis" />
          </nav>
        </header>
        </>
    );
};

export default Header;