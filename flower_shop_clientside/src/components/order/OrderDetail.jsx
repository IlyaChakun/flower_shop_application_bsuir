import React, {Component} from 'react'
import {withRouter} from 'react-router-dom'
import {Collapse, List} from 'antd'
import OrderProduct from "./OrderProduct";

const { Panel } = Collapse;

class OrderDetail extends Component {

    state = {
        isLoading: false
    }


    render() {

        const orderProducts = this.props.order.orderProducts
            .map(orderProduct => (
                    <OrderProduct
                        key={orderProduct.id}
                        orderProduct={orderProduct}
                    />
                )
            )
        return (

        <Panel header="Заказ ___" key={this.props.order.uniqueId}>
            <p> Заказ № {this.props.order.uniqueId}<br/>
                Комментарий к заказу: {this.props.order.comment===undefined? "": this.props.order.comment}<br/>
                Статус заказа: {this.props.order.orderStatus}<br/>
                Общая стоимость заказа: {this.props.order.totalAmount}<br/>

                Доставка: {this.props.order.address} {this.props.order.floorNumber} {this.props.order.entranceNumber}
            </p>
            <p>Список покупок:</p>

            <List
                grid={{
                    gutter: 70,
                    column: 3,
                }}

                dataSource={orderProducts}

                renderItem={item => (
                    <List.Item>
                        {item}
                    </List.Item>
                )}
            />

        </Panel>

        )
    }
}

export default withRouter(OrderDetail)

