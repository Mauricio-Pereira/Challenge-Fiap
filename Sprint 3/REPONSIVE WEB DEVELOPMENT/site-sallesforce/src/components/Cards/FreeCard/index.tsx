import { Props } from "../interface";
import Forms from "../../Forms";
import '../styles.css'

const FreeCard = (props: Props) => {
    return(
        <>
        <section className="flex">
            <div className={props.style}>
             <img src={props.img} alt={props.img} title={props.description}/>
             <p>{props.text}</p>
             <Forms/>
            </div>
        </section>
        </>
    );
};

export default FreeCard;