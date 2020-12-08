import React, {Component} from 'react'
import {withRouter} from 'react-router-dom'
import {getOrderById} from "../util/utilsAPI";
import LoadingIndicator from "../common/util/LoadingIndicator";
import OrderProduct from "./OrderProduct";
import {Col, List, Row} from "antd";

class OrderPage extends Component {

    state = {
        order: null,
        orderProducts: []
    }

    componentDidMount() {
        const promise = getOrderById(this.props.match.params.id);
        promise
            .then(response => {
                    console.log(response.orderProducts.length)
                    this.setState({
                        order: response,
                        orderProducts: response.orderProducts.slice()
                    })
                }
            )
    }

    render() {
        if (this.state.order === null || this.state.orderProducts === null) {
            return <LoadingIndicator/>
        }

        const orderProducts =
            this.state.orderProducts
                .map(orderProduct => (
                        <OrderProduct
                            key={orderProduct.id}
                            orderProduct={orderProduct}
                        />
                    )
                )

        console.log('product0')
        console.log(this.state.orderProducts[0])

        return (

            this.state.order === null ?
                (
                    <LoadingIndicator/>
                ) : (

                    <Row justify="center">
                        <Col span={22}>
                            <h1>Заказ №{this.state.order.id}</h1>
                            <p>Комментарий к заказу: {this.state.order.comment}</p>
                            <p>Статус заказа: {this.state.order.orderStatus}</p>
                            <p>Общая стоимость заказа: {this.state.order.totalAmount} руб.</p>
                            <p>Доставка: {this.state.order.address},
                                этаж: {this.state.order.floorNumber},
                                подъезд: {this.state.order.entranceNumber}</p>
                            <br/>
                            <p>
                                Заказан в магазине по адресу: {this.state.orderProducts[0].product.shop.contacts.city},
                                {this.state.orderProducts[0].product.shop.contacts.address}
                            </p>
                            <br/>

                            <p>Список товаров:</p>

                            <List
                                grid={{
                                    gutter: 8,
                                    column: 1,
                                }}

                                dataSource={orderProducts}

                                renderItem={item => (
                                    <List.Item>
                                        {item}
                                    </List.Item>
                                )}
                            />
                        </Col>
                    </Row>
                )
        )
    }
}

export default withRouter(OrderPage)

