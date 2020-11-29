import React, {Component} from 'react'
import {Card} from 'antd'

const {Meta} = Card

class OrderProduct extends Component {
    state={
        id: this.props.product.id,
        quantity: this.props.product.quantity,
        imageUrl: this.props.product.image === undefined ? '' : this.props.product.image.imageUrl
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
                            <span>Цена:  Кол-во: {this.props.product.quantity} </span>
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

                                </div>
                            }
                        />
                    </Card>

        )
    }
}

export default OrderProduct
