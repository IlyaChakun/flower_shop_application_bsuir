import React, { Component } from 'react'

import magaz1 from '../../img/shops/magaz1.jpg'
import './ShopCard.css'
import { Card, Rate } from 'antd'

const { Meta } = Card

class ShopCard extends Component {
  render () {
    const { image, dateOfCreation, dateOfLastUpdate, contacts, workingHours } = this.props.shop

    return (

      <Card
        hoverable
      >
        <div className="row">
          <div className="col-4">
            <a href="#">
              <img src={magaz1} className="img-fluid" alt={contacts.address}/>
            </a>
          </div>
          <div className="col-8">
            <div className="row">
              <div className="col-6">
                <div className="row top-prop">
                  <a href="#">{contacts.city} {contacts.address}</a>
                </div>
                <div className="row middle-prop">
                  <div className="show_on_map  ">
                    <span className="text_wrap"
                      data-coordinates="53.930613,27.588529">
                      <i className="fas fa-map-marker-alt"></i>
                      <span className="text"> Показать на карте</span>
                    </span>
                  </div>
                  <div className="row schedule">
                    <i className="far fa-clock"></i>
                    <span className="text  muted777">{workingHours.hours}</span>
                  </div>
                </div>
                <div className="col-6">
                  <div className="row phone">
                    <a href="tel:{contacts.firstPhoneNumber}" className="black">
                      {contacts.firstPhoneNumber}</a>
                  </div>
                  <div className="row phone">
                    <a href="tel:{contacts.firstPhoneNumber}" className="black">
                      {contacts.secondPhoneNumber}</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </Card>
    )
  }
}

export default ShopCard
