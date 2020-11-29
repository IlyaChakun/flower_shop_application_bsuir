import React, {Component} from 'react'
import {withRouter} from 'react-router-dom'
import {getOrderById} from "../util/utilsAPI";
import LoadingIndicator from "../common/util/LoadingIndicator";
import OrderProduct from "./OrderProduct";
import {Col, List, Row} from "antd";

class OrderPage extends Component {

    state = {
        order: null,
        products: []
    }

    componentDidMount() {
        const promise = getOrderById(this.props.match.params.id);
        promise
            .then(response => {
                    console.log(response.orderProducts.length)
                    this.setState({
                        order: response,
                        products: response.orderProducts.slice()
                    })
                }
            )
    }

    render() {

        const orderProducts = (this.state.order === null && this.state.products ===null ) ? null :
            (
                this.state.products
                    .map(product => (
                            <OrderProduct
                                key={product.id}
                                product={product}
                            />
                        )
                    )
            )

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

