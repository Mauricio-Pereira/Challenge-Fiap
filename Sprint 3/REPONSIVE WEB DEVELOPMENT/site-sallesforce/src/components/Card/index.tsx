import { Props } from "./interface";
import './styles.css'

const Card = (props: Props) => {
    return(
        <>
        <div className={props.style}>
            <h1>{props.title}</h1>
            <p>{props.text}</p>
            <img src={props.img} alt={props.img} title={props.description}/>
        </div>
        </>
    );
};

export default Card;