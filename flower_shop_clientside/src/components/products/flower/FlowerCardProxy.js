import React, {Component} from 'react'

import './FlowerCard.css'
import Link from 'react-router-dom/Link'
import FlowerCard from './FlowerCard'
import {isAdmin} from '../../../app/App'
import SettingOutlined from '@ant-design/icons/lib/icons/SettingOutlined'
import DeleteFlowerModal from './DeleteFlowerModal'
import DeleteOutlined from '@ant-design/icons/lib/icons/DeleteOutlined'
import EditFlowerModal from "./EditFlowerModal";
import AddFlowerModal from "./AddFlowerModal";

class FlowerCardProxy extends Component {
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
            <EditFlowerModal
                shopId={this.props.shopId}
                productId={this.props.productId}
            />
        )
        const deleteAction = (

            <div className={isAdmin(this.props.currentUser) ? '' : 'custom-hidden'}>
                <DeleteFlowerModal
                    productId={this.props.product.id}
                    button={
                        <DeleteOutlined style={{fontSize: '25px'}}/>
                    }/>
            </div>)
        // const buyAction = (
        //   <span className={this.props.isAuthenticated ? '' : 'custom-hidden'}
        //     onClick={() => this.addToBasket()}>
        //     <PlusCircleOutlined style={{ fontSize: '27px', color: '#cc3242' }}/>
        //   </span>
        // )
        /*
                         <FlowerCard
                        history={this.props.history}
                        currentUser={this.props.currentUser}
                        isAuthenticated={this.props.isAuthenticated}
                        product={this.props.product}
                        firstAction={editAction}
                        secondAction={deleteAction}
                        thirdAction={buyAction}
                      />
                     */
        return (
            <FlowerCard
                key={this.props.productId}
                product={this.props.product}
                firstAction={editAction}
                secondAction={deleteAction}
            />
        )
    }
}

export default FlowerCardProxy
