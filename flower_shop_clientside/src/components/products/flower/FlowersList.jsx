import React, {useEffect} from 'react'

import {Col, List, Row, Select} from 'antd'
import AddFlowerModal from "./AddFlowerModal";
import FlowerCardProxy from "./FlowerCardProxy";
import {withRouter} from "react-router-dom";
import LoadingIndicator from "../../common/util/LoadingIndicator";
import {useDispatch, useSelector} from "react-redux";
import {
    getProducts,
    getShops,
    productSelector,
    setPage,
    setShopId,
    setShopValue,
    setSize
} from "../../../redux/reducers/ProductsSliceReducer";

const {Option} = Select;


const FlowersList = (props) => {

    const dispatch = useDispatch()
    const {productsState, loading, errors} = useSelector(productSelector)


    useEffect(() => {
        dispatch(getShops())
        loadList()
    }, [dispatch])

    const updateList = () => {
        loadList(productsState.page, productsState.size, productsState.shopId)
    }

    const loadList=(page, size, shopId, minPrice, maxPrice, sortBy, sortType)=> {
        const searchCriteria = {
            page: page,
            size: size,
            shopId: shopId,

            minPrice: minPrice,
            maxPrice: maxPrice,
            sortBy: sortBy,
            sortType: sortType
        };

        if (!productsState.shopId) {
            dispatch(getProducts(searchCriteria))
        } else {
            dispatch(getProducts(searchCriteria, productsState.shopId))
        }
    }

    const loadSearchList = (productName, minPrice, maxPrice, sortBy, sortType, checkedBrands) => {
        loadList(productsState.page, productsState.size, productName, minPrice, maxPrice, sortBy, sortType, checkedBrands);
    }


    if (loading === true) {
        return <LoadingIndicator/>
    }

    const addProductButton = productsState.shopId === undefined ? '' :
        (
            <AddFlowerModal shopId={productsState.shopId}
                            updateList={updateList}
            />
        )

    const flowers = productsState.products
        .map(product => (
                <FlowerCardProxy
                    history={props.history}
                    currentUser={props.currentUser}
                    isAuthenticated={props.isAuthenticated}
                    key={product.id}

                    product={product}
                    productId={product.id}
                    shopId={product.shop.id}
                />
            )
        )

    const shopOptions = productsState.shops.map(
        shop =>
            <Option key={shop.id} value={shop.contacts.address}>
                {shop.contacts.city}, {shop.contacts.address}
            </Option>
    )


    const handleShopChange = (input, option) => {
        dispatch(setShopId(option.props.key))
        dispatch(setShopValue(option.props.value))

        // this.setState({
        //         shopId: option.props.key,
        //         shopValue: option.props.value
        //     },
        //     () => {
        //         updateList(productsState.page, productsState.size, productsState.shopId)
        //     })

    }

    const onSizeChangeHandler = (page, size) => {
        dispatch(setSize(size))
        dispatch(setPage(page))
        loadList(page, size);
    };

    const onPageChangeHandler = (pageNumber) => {
        dispatch(setPage(pageNumber))
        loadList(pageNumber, productsState.size);
    };

    const loadMore = () => {
        loadList(productsState.page + 1, productsState.size);
    }


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
                                defaultValue={{key: productsState.shopId, value: productsState.shopValue}}
                                value={productsState.shopValue}
                                style={{width: 200}}
                                placeholder="Выберите магазин"
                                onChange={handleShopChange}
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

                            loading: productsState.loading,
                            showSizeChanger: true,

                            defaultCurrent: Number(productsState.page),
                            defaultPageSize: Number(productsState.size),

                            pageSizeOptions: ["6", "9", "12"],
                            position: "bottom",

                            total: productsState.totalElements,

                            showQuickJumper: true,
                            onShowSizeChange: onSizeChangeHandler,
                            onChange: onPageChangeHandler,

                            loadMore: loadMore
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

export default withRouter(FlowersList)
