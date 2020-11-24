import React, {Component} from 'react'

import magaz1 from '../../img/shops/magaz1.jpg'
import './ShopCard.css'
import {Card, Rate} from 'antd'

const {Meta} = Card

class ShopCard extends Component {

    state = {
        image: this.props.shop.image,
        dateOfCreation: this.props.shop.dateOfCreation,
        dateOfLastUpdate: this.props.shop.dateOfLastUpdate,
        contacts: this.props.shop.contacts,
        workingHours: this.props.shop.workingHours,
    }

    render() {

        return (

            <Card
                hoverable
                style={{marginTop: 16}}
                extra={
                    ''
                }
                title={
                    <span>
            <a href="#">{this.state.contacts.city} {this.state.contacts.address}</a>
          </span>
                }

                actions={[
                    this.props.firstAction
                ]}>

                <Meta
                    avatar={
                        <span>
              <img alt="picture"
                   className="img-fluid"
                   src={magaz1}
                   width={'200px'}
                   height={'400px'}
              />
            </span>
                    }

                    title={
                        <div>
                            <p>
                <span className="text_wrap" data-coordinates="53.930613,27.588529">
                  <i className="fas fa-map-marker-alt"></i>
                  <span className="text"> Показать на карте</span>
                </span>
                            </p>
                        </div>
                    }

                    description={
                        <div>
                            <div className="">
                                <i className="far fa-clock"></i>
                                <span className="text  muted777">{this.state.workingHours.hours}</span>
                            </div>
                            <div className="row phone">
                                <a href="tel:{contacts.firstPhoneNumber}" className="black">
                                    {this.state.contacts.firstPhoneNumber}</a>
                            </div>
                            <div className="row phone">
                                <a href="tel:{contacts.firstPhoneNumber}" className="black">
                                    {this.state.contacts.secondPhoneNumber}</a>
                            </div>

                            <div className="">
                                Последнее обновление: {this.state.dateOfLastUpdate}
                                <br/>
                                Дата открытия: {this.state.dateOfCreation}
                            </div>
                        </div>
                    }
                />

            </Card>

        )
    }
}

export default ShopCard
