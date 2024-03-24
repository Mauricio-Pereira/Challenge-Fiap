import { Props } from "../interface";
import '../styles.css'

const Card = (props: Props) => {
    return(
        <>
        <section className="flex">
            <h1 className={props.intitle}>{props.title}</h1>
            <div className={props.style}>
             <h1 className={props.intitle}>{props.title}</h1>
             <p>{props.text}</p>
             <img src={props.img} alt={props.img} title={props.description}/>
            </div>
        </section>
        </>
    );
};

export default Card;