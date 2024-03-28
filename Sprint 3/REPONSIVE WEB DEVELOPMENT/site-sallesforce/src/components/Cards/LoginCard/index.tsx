import { Props } from "../interface";
import LoginForms from "../../Forms/LoginForms";
import '../styles.css'

const LoginCard = (props: Props) => {
    return(
        <>
        <section className="flex">
            <div className={props.style}>
             <img src={props.img} alt={props.img} title={props.description}/>
             <LoginForms/>
            </div>
        </section>
        </>
    );
};

export default LoginCard;