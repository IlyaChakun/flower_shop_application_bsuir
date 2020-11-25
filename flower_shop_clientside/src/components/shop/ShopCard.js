import React, {Component} from 'react'

import './ShopCard.css'
import {Card} from 'antd'
import {withRouter} from "react-router-dom";

const {Meta} = Card

class ShopCard extends Component {

    state = {
        dateOfCreation: this.props.shop.dateOfCreation,
        dateOfLastUpdate: this.props.shop.dateOfLastUpdate,
        contacts: this.props.shop.contacts,
        workingHours: this.props.shop.workingHours,
        imageUrl: this.props.shop.image === undefined ? '' : this.props.shop.image.imageUrl
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
                                   src={this.state.imageUrl}
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
export default withRouter(ShopCard)

