import React, {Component} from 'react'

import {isAdmin} from '../../../app/App'
import DeleteOutlined from '@ant-design/icons/lib/icons/DeleteOutlined'

import DeleteBouquetModal from "./DeleteBouquetModal";
import EditBouquetModal from "./EditBouquetModal";
import BouquetCard from "./BouquetCard";

class BouquetCardProxy extends Component {
    state = {}
    // addToBasket = () => {
    //     const productBasket = {
    //         userId: this.props.currentUser.id,
    //         productId: this.props.product.id
    //     };
    //
    //     addProductToBasket(productBasket)
    //         .then(() => {
    //             notification.success({
    //                 message: localizedStrings.alertAppName,
    //                 description: 'Продукт добавлен в корзину!',
    //             });
    //         }).catch(error => {
    //
    //         notification.error({
    //             message: localizedStrings.alertAppName,
    //             description: 'Не удалось добавить продукт в корзину!',
    //         });
    //     });
    // };

    render() {
        /*
          <Link
                to={'/company/shops/' + this.props.product.id}>
                    <span className={isAdmin(this.props.currentUser) ? '' : 'custom-hidden'}>
                      <SettingOutlined style={{fontSize: '25px'}}/>
                    </span>
            </Link>
         */

        const editAction = (
            <EditBouquetModal
                shopId={this.props.shopId}
                productId={this.props.productId}
            />
        )
        const deleteAction = (

            <div className={isAdmin(this.props.currentUser) ? '' : 'custom-hidden'}>
                <DeleteBouquetModal
                    productId={this.props.product.id}
                    button={
                        <DeleteOutlined style={{fontSize: '25px'}}/>
                    }/>
            </div>)

        return (
            <BouquetCard
                key={this.props.productId}
                product={this.props.product}
                firstAction={editAction}
                secondAction={deleteAction}
            />
        )
    }
}

export default BouquetCardProxy
