import { Props } from "../interface";
import ProductForms from "../../Forms/ProductForms";
import '../styles.css'

const ProductsCard = (props: Props) => {
    return(
        <>
        <section className="flex">
            <div className={props.style}>
             <h1>{props.title}</h1>
             <ProductForms/>   
             <img src={props.img} alt={props.img} title={props.description}/>
            </div>
        </section>
        </>
    );
};

export default ProductsCard;