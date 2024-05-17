import { Props } from "../interface";
import FreeForms from "../../Forms/FreeForms";
import '../styles.css'

const FreeCard = (props: Props) => {
    return(
        <>
        <section className="flex cartao">
            <div className={props.style}>
             <img src={props.img} alt={props.img} title={props.description}/>
             <FreeForms text={props.text}/>
            </div>
        </section>
        </>
    );
};

export default FreeCard;