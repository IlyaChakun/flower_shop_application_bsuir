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
        style={{ width: 1000, marginTop: 16 }}
        extra={
          ''
        }
        title={
          <span>
            <a href="#">{contacts.city} {contacts.address}</a>
          </span>
        }
      >

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
                <span className="text  muted777">{workingHours.hours}</span>
              </div>
              <div className="row phone">
                <a href="tel:{contacts.firstPhoneNumber}" className="black">
                  {contacts.firstPhoneNumber}</a>
              </div>
              <div className="row phone">
                <a href="tel:{contacts.firstPhoneNumber}" className="black">
                  {contacts.secondPhoneNumber}</a>
              </div>

              <div className="">
                                Последнее обновление: {dateOfLastUpdate}
                <br/>
                                Дата открытия: {dateOfCreation}
              </div>
            </div>
          }
        />

      </Card>

    )
  }
}

export default ShopCard
