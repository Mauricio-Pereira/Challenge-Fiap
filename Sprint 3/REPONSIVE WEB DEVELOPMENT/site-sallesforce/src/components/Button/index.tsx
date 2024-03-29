import { Props } from "./interface";
import Link from "next/link";
import './styles.css'

const Button = (props: Props) => {
    return(<Link className={props.style} href={props.link}>{props.text}</Link>);
};

export default Button;