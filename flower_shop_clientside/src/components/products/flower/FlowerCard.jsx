import React, {Component} from 'react'
import {Card} from 'antd'
import './FlowerCard.css'
import DownOutlined from "@ant-design/icons/lib/icons/DownOutlined";


import {Menu, Dropdown, Button, message, Tooltip} from 'antd';

const {Meta} = Card

function handleMenuClick(e) {
    message.info('Click on menu item.');
    console.log('click', e);
}

class FlowerCard extends Component {

    state = {
        dateOfLastUpdate: this.props.flower.dateOfLastUpdate,
        flowerType: this.props.flower.flowerType.flowerType,
        flowerColors: this.props.flower.flowerColors,
        flowerLengthCosts: this.props.flower.flowerLengthCosts,
        flowerSorts: this.props.flower.flowerSorts,
        country: this.props.flower.country.countryNameRu,
        description: this.props.flower.description,
        availableAmountOnStock: this.props.flower.availableAmountOnStock,
        shopAddress: this.props.flower.shop.contacts.address,
        shopCity: this.props.flower.shop.contacts.city,
        shopFirstPhoneNumber: this.props.flower.shop.contacts.firstPhoneNumber
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
                        {this.state.flowerType}
                    </span>
                }
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

export default FlowerCard
