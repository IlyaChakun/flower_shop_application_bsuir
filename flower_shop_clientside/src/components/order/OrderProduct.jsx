import React, {Component} from 'react'
import {Card} from 'antd'

const {Meta} = Card

class OrderProduct extends Component {
    state = {
        id: this.props.orderProduct.id,
        dateOfLastUpdate: this.props.orderProduct.product.dateOfLastUpdate,
        flowerType: this.props.orderProduct.product.flowerType.flowerType,
        flowerColor: this.props.orderProduct.product.flowerColor.colorName,
        flowerSort: this.props.orderProduct.product.flowerSort.sortNameRu,
        flowerLengthCosts: this.props.orderProduct.product.flowerLengthCosts,
        price: this.props.orderProduct.product.flowerLengthCosts.price,
        country: this.props.orderProduct.product.country.countryNameRu,
        description: this.props.orderProduct.product.description,
        availableAmountOnStock: this.props.orderProduct.product.availableAmountOnStock,
        shopAddress: this.props.orderProduct.product.shop.contacts.address,
        shopCity: this.props.orderProduct.product.shop.contacts.city,
        shopFirstPhoneNumber: this.props.orderProduct.product.shop.contacts.firstPhoneNumber,
        imageUrl: this.props.orderProduct.product.image === null ? '' : this.props.orderProduct.product.image.imageUrl,

        quantity: this.props.orderProduct.quantity
    }

    render() {
        return (
            < Card
                bodyStyle={
                    {
                        padding: '10px'
                    }
                }
                hoverable
                extra={
                    <span>Цена:{this.state.price}  Кол-во: {this.state.quantity} </span>
                }
                title={
                    <span></span>
                }
            >

                <
                    Meta
                    style={{padding: '5px'}}
                    avatar={<img src={this.state.imageUrl} alt=""/>}

                    description={
                        <div>
                            {this.state.description}
                            {this.state.availableAmountOnStock}
                            {this.state.flowerColor}
                            {this.state.flowerSort}
                            {this.state.dateOfLastUpdate}
                        </div>
                    }
                />
            </Card>

        )
    }
}

export default OrderProduct
