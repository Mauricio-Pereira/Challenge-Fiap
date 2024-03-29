import Button from '../../Button';
import '../styles.css';

const ProductForms = () => {
    return(
        <>
        <div className='flex form'>
            <form className='product-form'>
                <label htmlFor='nome'>Nome do Produto</label>
                <input className="input" type="text"/>
                
                <div>
                    <div>
                        <label htmlFor='preco'>Preço do Produto</label>
                        <input className="input" type="text"/>
                    </div>

                    <div>
                        <label htmlFor='quantidade'>Quantidade em Estoque</label>
                        <input className="input" type="number"/>
                    </div>
                </div>
                
                <label htmlFor='descricao'>Descrição do Produto</label>
                <input className="input description" type="text"/>
                
                <Button type="submit" style="submit-btn" link="/Products" text="Adicionar Produtos" />
            </form>
        </div>
        </>
    );
}

export default ProductForms;
