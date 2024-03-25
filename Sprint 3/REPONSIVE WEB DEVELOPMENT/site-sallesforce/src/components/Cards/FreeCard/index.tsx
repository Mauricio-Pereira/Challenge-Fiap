import { Props } from "../interface";
import Forms from "../../Forms/FreeForms";
import '../styles.css'

const FreeCard = (props: Props) => {
    return(
        <>
        <section className="flex">
            <div className={props.style}>
             <img src={props.img} alt={props.img} title={props.description}/>
             <Forms
             text={props.text}
             />
            </div>
        </section>
        </>
    );
};

export default FreeCard;