import React, { Component } from 'react'

import magaz1 from '../../img/shops/magaz1.jpg'
import './ShopCard.css'
import { Button, Card } from 'antd'

class ShopCard extends Component {
  render () {
    const { image, dateOfCreation, dateOfLastUpdate, contacts, workingHours } = this.props.shop

    return (

      <Card
        hoverable
      >
        <div className="row">
          <div className="col-4 col-xl-2">
            <a href="#">
              <img src={magaz1} className="img-fluid" alt={contacts.address}/>
            </a>
          </div>
          <div className="col-8 col-xl-10">
            <div className="row">
              <div className="col-md-6 col-lg-5">
                <div className="row top-prop mb-2">
                  <a href="#" className="grey">{contacts.address}</a>
                </div>
                <div className="row middle-prop">
                  <div className="row show-on-map mb-2">
                    <span className="text-wrap" data-coordinates="53.930613,27.588529">
                      <i className="fas fa-map-marker-alt mr-3"></i>
                      <span className="text">Показать на карте</span>
                    </span>
                  </div>
                  <div className="row schedule">
                    <span>
                      <i className="far fa-clock mr-3"></i>
                      <span className="text">{workingHours.hours}</span>
                    </span>
                  </div>
                </div>
              </div>
              <div className="col-md-3 col-lg-4 text-lg-center text-md-right">
                <div className="row phone">
                  <a href="tel:${contacts.firstPhoneNumber}" className="black">
                    {contacts.firstPhoneNumber}</a>
                </div>
                <div className="row phone">
                  <a href='tel:{contacts.firstPhoneNumber}' className="black">
                    {contacts.secondPhoneNumber}</a>
                </div>
              </div>
              <div className="col-md-3 col-lg-3 d-flex flex-column">
                <Button type="primary" className="mb-4">Изменить</Button>
                <Button type="primary" danger>Удалить</Button>
              </div>
            </div>
          </div>
        </div>
      </Card>
    )
  }
}

export default ShopCard
