import React, {Component} from 'react'
import {withRouter} from 'react-router-dom'

import {getOrdersByShopIdRequest, getOrdersRequest} from "../util/utilsAPI";
import {Collapse} from 'antd';
import OrderDetail from "./OrderDetail";


class OrderList extends Component {

    state = {

        shopId: this.props.shopId,

        orders: [
            {
                orderProducts: [{id:1}, {id:2}],
                id: 1
            },
            {
                orderProducts: [{id:1}, {id:2}],
                id: 2
            },
            {
                orderProducts: [{id:1}, {id:2}],
                id: 3
            }
        ],

        page: 1,
        size: 6,
        pagesCount: 0,

        searchString: '',

        totalPages: 0,
        totalElements: 0,


        isLoading: false
    }


    componentDidMount() {
        this.updateList()
    }

    updateList = () => {
        this.loadList(this.state.page, this.state.size, this.state.shopId)
    }

    loadList = (page, size) => {

        const searchCriteria = {
            page: page,
            size: size
        };

        if (!this.state.shopId) {
            const promise = getOrdersRequest(searchCriteria);
            if (!promise) {
                return;
            }
            this.extractPromise(promise);
        } else {
            const promise = getOrdersByShopIdRequest(searchCriteria, this.state.shopId);
            if (!promise) {
                return;
            }
            this.extractPromise(promise);
        }
    };


    extractPromise = (promise) => {

        this.setState({
            isLoading: true
        });

        promise
            .then(response => {

                this.setState({
                    orders: response.objects.slice(),
                    totalPages: response.totalPages,
                    totalElements: response.totalElements,
                });

            }).catch(() => {
            this.setState({
                isLoading: false
            });
        });
    };

    render() {

        const orders = this.state.orders
            .map(order => (
                    <OrderDetail
                        key={order.uniqueId}
                        order={order}
                    />
                )
            )

        return (
            // <Collapse accordion>
            //     {orders}
            // </Collapse>


            <div className="row">
                <Collapse accordion>

                    {orders}


                    {/*<List*/}
                    {/*    grid={{*/}
                    {/*        gutter: 70,*/}
                    {/*        column: 3,*/}
                    {/*    }}*/}

                    {/*    pagination={{*/}

                    {/*        loading: this.state.isLoading,*/}
                    {/*        showSizeChanger: true,*/}

                    {/*        defaultCurrent: Number(this.state.page),*/}
                    {/*        defaultPageSize: Number(this.state.size),*/}

                    {/*        pageSizeOptions: ["6", "9", "12"],*/}
                    {/*        position: "bottom",*/}

                    {/*        total: this.state.totalElements,*/}

                    {/*        showQuickJumper: true,*/}
                    {/*        onShowSizeChange: this.onSizeChangeHandler,*/}
                    {/*        onChange: this.onPageChangeHandler,*/}

                    {/*        loadMore: this.loadMore*/}
                    {/*    }}*/}

                    {/*    dataSource={orders}*/}

                    {/*    renderItem={item => (*/}
                    {/*        <List.Item>*/}
                    {/*            {item}*/}
                    {/*        </List.Item>*/}
                    {/*        )}*/}
                    {/*/>*/}

                </Collapse>
            </div>
        )
    }


    onSizeChangeHandler = (page, size) => {

        this.setState({
            page: page,
            size: size
        });
        this.loadList(page, size);
    };

    onPageChangeHandler = (pageNumber) => {

        console.log('onPageChangeHandler')
        console.log('pageNumber', pageNumber)
        console.log('totalElements', this.state.totalElements)
        console.log('totalPages', this.state.totalPages)

        this.setState({
            page: pageNumber
        });


        this.loadList(pageNumber, this.state.size);
    };

    loadMore = () => {

        console.log('LOAD MORE WORKS')

        this.loadList(this.state.page + 1, this.state.size);
    }

}

export default withRouter(OrderList)

