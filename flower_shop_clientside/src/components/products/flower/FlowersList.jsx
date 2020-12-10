import React, {PureComponent} from 'react'

import {Col, List, Row, Select} from 'antd'
import {getAllShops, getFlowersByShopIdRequest, getFlowersRequest} from "../../util/utilsAPI";
import AddFlowerModal from "./AddFlowerModal";
import FlowerCardProxy from "./FlowerCardProxy";
import {withRouter} from "react-router-dom";
import LoadingIndicator from "../../common/util/LoadingIndicator";

const {Option} = Select;


class FlowersList extends PureComponent {

    state = {

        flowers: [],
        shops: [],

        shopId: '',

        shopValue: '',

        page: 1,
        size: 6,
        pagesCount: 0,

        searchString: '',

        totalPages: 0,
        totalElements: 0,

        isLoading: true
    }


    componentDidMount() {
        this.updateList()
        this.getShopList()
    }

    updateList = () => {
        this.loadList(this.state.page, this.state.size, this.state.shopId)
    }


    getShopList = () => {

        const promise = getAllShops();
        if (!promise) {
            return;
        }
        promise
            .then(response => {
                this.setState({
                    shops: response.objects.slice(),
                    shopValue: response.objects[0] === null ? null : response.objects[0].contacts.address,
                    shopId: response.objects[0] === null ? null : response.objects[0].id
                });

            }).catch(() => {
            this.setState({
                isLoading: false
            });
        });
    };


    // loadSearchList = (productName, minPrice, maxPrice, sortBy, sortType, checkedBrands) => {
    //     this.loadList(this.state.page, this.state.size, productName, minPrice, maxPrice, sortBy, sortType, checkedBrands);
    // };


    loadList = (page, size, shopId, minPrice, maxPrice, sortBy, sortType) => {

        const searchCriteria = {
            page: page,
            size: size,
            shopId: shopId,

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
                    isLoading: false
                });

            }).catch(() => {
            this.setState({
                isLoading: false
            });
        });
    };


    render() {

        if (this.state.isLoading === true) {
            return <LoadingIndicator/>
        }

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

        const shopOptions = this.state.shops.map(
            shop =>
                <Option key={shop.id} value={shop.contacts.address}>
                    {shop.contacts.city}, {shop.contacts.address}
                </Option>
        )

        return (

            <div className="pb-5">
                <Row justify="center">
                    <Col span={22}>
                        <Row justify="space-between">
                            <Col span={4}>
                                <h1>Цветы</h1>
                            </Col>
                            <Col>
                                <Select
                                    name={"shopSelect"}
                                    showSearch
                                    defaultValue={{key: this.state.shopId, value: this.state.shopValue}}
                                    value={this.state.shopValue}
                                    style={{width: 200}}
                                    placeholder="Выберите магазин"
                                    onChange={this.handleShopChange}
                                >
                                    {shopOptions}
                                </Select>
                            </Col>
                            <Col span={4}>
                                {addProductButton}
                            </Col>
                        </Row>

                        <List
                            grid={{
                                gutter: 16,
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
                    </Col>
                </Row>
            </div>
        )
    }

    handleShopChange = (input, option) => {
        this.setState({
                shopId: option.props.key,
                shopValue: option.props.value
            },
            () => {
                this.updateList(this.state.page, this.state.size, this.state.shopId)
            })

    }

    onSizeChangeHandler = (page, size) => {

        this.setState({
            page: page,
            size: size
        });
        this.loadList(page, size);
    };

    onPageChangeHandler = (pageNumber) => {
        this.setState({
            page: pageNumber
        });


        this.loadList(pageNumber, this.state.size);
    };

    loadMore = () => {
        this.loadList(this.state.page + 1, this.state.size);
    }

}

export default withRouter(FlowersList)
