import { Props } from "../interface";
import '../styles.css'

const Card = (props: Props) => {
    return(
        <>
        <section className="flex home-page">
            <h1>{props.title}</h1>
            <div className={props.style}>
             <p>{props.text}</p>
             <img src={props.img} alt={props.img} title={props.description}/>
            </div>
        </section>
        </>
    );
};

export default Card;