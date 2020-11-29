import React, {Component} from 'react'
import {Card} from 'antd'

const {Meta} = Card

class OrderProduct extends Component {
    state = {
        id: this.props.product.id,
        dateOfLastUpdate: this.props.product.dateOfLastUpdate,
        // flowerType: this.props.product.flowerType.flowerType,
        flowerColor: this.props.product.flowerColor,
        flowerSort: this.props.product.flowerSort,
        flowerLengthCosts: this.props.product.flowerLengthCosts,
        // price: this.props.product.flowerLengthCosts.price,
        // country: this.props.product.country.countryNameRu,
        description: this.props.product.description,
        availableAmountOnStock: this.props.product.availableAmountOnStock,
        // shopAddress: this.props.product.shop.contacts.address,
        // shopCity: this.props.product.shop.contacts.city,
        // shopFirstPhoneNumber: this.props.product.shop.contacts.firstPhoneNumber,
        // imageUrl: this.props.product.image === null ? '' : this.props.product.image.imageUrl,

        quantity: this.props.product.quantity
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
