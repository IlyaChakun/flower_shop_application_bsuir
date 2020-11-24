import React, {Component} from 'react'
import {Card} from 'antd'
import './BouquetCard.css'
import DownOutlined from "@ant-design/icons/lib/icons/DownOutlined";


import {Menu, Dropdown, Button, message} from 'antd';

const {Meta} = Card

function handleMenuClick(e) {
    message.info('Click on menu item.');
    console.log('click', e);
}

class BouquetCard extends Component {

    state = {
        dateOfLastUpdate: this.props.product.dateOfLastUpdate,

        title: this.props.product.title,
        bouquetType: this.props.product.bouquetType.bouquetType,

        flowerColors: this.props.product.flowerColors,
        flowerLengthCosts: this.props.product.flowerLengthCosts,
        flowerSorts: this.props.product.flowerSorts,
        country: this.props.product.country.countryNameRu,
        description: this.props.product.description,
        availableAmountOnStock: this.props.product.availableAmountOnStock,
        shopAddress: this.props.product.shop.contacts.address,
        shopCity: this.props.product.shop.contacts.city,
        shopFirstPhoneNumber: this.props.product.shop.contacts.firstPhoneNumber
    }

    render() {

        const flowerSorts = 'Сорт: ' + this.state.flowerSorts
            .map(sort => (
                    sort.sortNameRu + ' '
                )
            )

        const flowerLengthCosts = this.state.flowerLengthCosts
            .map(lengthCost => (
                <Menu.Item
                    key={lengthCost.id}
                >
                    Длина стебля: {lengthCost.stemLength}
                    <br/>
                    Стоимость: {lengthCost.price}
                </Menu.Item>
            ))
        const flowerLengthCostsMenu = (
            <Menu onClick={handleMenuClick}>
                {flowerLengthCosts}
            </Menu>
        )
        const flowerLengthCostsDropdown = (
            <Dropdown overlay={flowerLengthCostsMenu}>
                <Button>
                    Стоимость и длина <DownOutlined/>
                </Button>
            </Dropdown>
        )

        const flowerColors = 'Цвета: ' + this.state.flowerColors
            .map(color => (color.colorName + ' '))

        return (


            <Card
                hoverable
                style={{width: 600, marginTop: 16}}
                extra={
                    'Производитель:  ' + this.state.country
                }
                title={
                    <span>
                        {this.state.title}
                        <br/>
                        {this.state.bouquetType}
                    </span>
                }
                actions={[
                    this.props.firstAction,
                    this.props.secondAction,
                    this.state.availableAmountOnStock > 0 ? 'тут кнопка в корзину' : ''
                ]}>
            >

                <Meta
                    avatar={
                        <span>
                              <img alt="picture"
                                   className="img-fluid"
                                   src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
                                   width={'200px'}
                                   height={'400px'}
                              />
                        </span>
                    }

                    title={
                        <div>
                            <p>
                                {flowerSorts}
                                <br/>
                                {flowerColors}
                            </p>
                            <p>
                                {flowerLengthCostsDropdown}
                            </p>

                        </div>
                    }

                    description={
                        <div>
                            <div className="product-content-body">
                                Количества в наличии: {this.state.availableAmountOnStock} штук в
                                магазине по адресу: {this.state.shopCity}
                                {this.state.shopAddress},
                                номер: {this.state.shopFirstPhoneNumber}
                            </div>


                            <div className="product-rating-footer">
                                Последнее обновление: {this.state.dateOfLastUpdate}
                            </div>
                        </div>
                    }
                />

            </Card>

        )
    }
}

export default BouquetCard
