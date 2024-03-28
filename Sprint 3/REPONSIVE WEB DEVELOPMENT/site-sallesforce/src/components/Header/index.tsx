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
              <li><Link className="links" href="/">Página Inicial</Link></li>
              <li><Link className="links" href="/Salesforce">A Salesforce</Link></li>
              <li><Link className="links" href="/Login">Login</Link></li>
              <li><Link className="links" href="/Members">Integrantes</Link></li>
            </ul>

            <Button style="free-trial-btn" link="/Free-Trial" text="Teste Grátis"/>
          </nav>
        </header>
        </>
    );
};

export default Header;