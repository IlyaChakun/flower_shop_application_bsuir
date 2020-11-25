import React, {PureComponent} from 'react'

import {List, Card} from 'antd'
import {getBouquetsByShopIdRequest, getFlowersByShopIdRequest, getFlowersRequest} from "../../util/utilsAPI";
import AddFlowerModal from "./AddFlowerModal";
import FlowerCardProxy from "./FlowerCardProxy";
import AddShopModal from "../../shop/AddShopModal";
import {withRouter} from "react-router-dom";

class FlowersList extends PureComponent {

    state = {

        flowers: [],

        page: 1,
        size: 6,
        pagesCount: 0,

        searchString: '',

        totalPages: 0,
        totalElements: 0,


        isLoading: false,

    }


    componentDidMount() {
        this.updateList()
    }

    updateList = () => {
        this.loadList(this.state.page, this.state.size)
    }


    // loadSearchList = (productName, minPrice, maxPrice, sortBy, sortType, checkedBrands) => {
    //     this.loadList(this.state.page, this.state.size, productName, minPrice, maxPrice, sortBy, sortType, checkedBrands);
    // };


    loadList = (page, size, minPrice, maxPrice, sortBy, sortType) => {

        const searchCriteria = {
            page: page,
            size: size,

            minPrice: minPrice,
            maxPrice: maxPrice,
            sortBy: sortBy,
            sortType: sortType
        };

        if (!this.state.shopId) {
            const promise = getFlowersRequest(searchCriteria);
            if (!promise) {
                return;
            }
            this.extractPromise(promise);
        } else {
            const promise = getFlowersByShopIdRequest(searchCriteria, this.state.shopId);
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
                    flowers: response.objects.slice(),
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

        const addProductButton = this.props.shopId === undefined ? '' :
            (
                <AddFlowerModal shopId={this.props.shopId}
                                updateList={this.updateList}
                />
            )

        const flowers = this.state.flowers
            .map(product => (
                    <FlowerCardProxy
                        history={this.props.history}
                        currentUser={this.props.currentUser}
                        isAuthenticated={this.props.isAuthenticated}
                        key={product.id}

                        product={product}
                        productId={product.id}
                        shopId={product.shop.id}
                    />
                )
            )


        return (
            <div className="container-fluid">
                <div className="row justify-content-between">
                    <div className="col-2"><h1>Цветы</h1></div>

                    <div className="col-2">{addProductButton}</div>
                </div>

                <div className="row">
                    <List
                        grid={{
                            gutter: 70,
                            column: 3,
                        }}

                        pagination={{

                            loading: this.state.isLoading,
                            showSizeChanger: true,

                            defaultCurrent: Number(this.state.page),
                            defaultPageSize: Number(this.state.size),

                            pageSizeOptions: ["6", "9", "12"],
                            position: "bottom",

                            total: this.state.totalElements,

                            showQuickJumper: true,
                            onShowSizeChange: this.onSizeChangeHandler,
                            onChange: this.onPageChangeHandler,

                            loadMore: this.loadMore
                        }}

                        dataSource={flowers}

                        renderItem={item => (
                            <List.Item>
                                {item}
                            </List.Item>
                        )}
                    />
                </div>
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

export default withRouter(FlowersList)
