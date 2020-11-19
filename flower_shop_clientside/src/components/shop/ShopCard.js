import React, { Component } from 'react'

class ShopCard extends Component {
  render () {
    return (

      <div className="item border border-light box-shadow">
        <div className="row">
          <div className="col-md-6 col-sm-8 col-xs-12 left-block-contacts">
            <div className="image pull-left">
              <a href="#">
                <img src="/upload/iblock/aa1/Bedy_ma.png"
                  data-src="/upload/iblock/aa1/Bedy_ma.png" alt="ул. Беды, 2Б"
                  title="ул. Беды, 2Б" className="img-responsive lazyloaded"/>
              </a>
            </div>
            <div className="top-wrap">
              <div className="title font_mxs darken">
                <a href="/contacts/stores/bedy/" className="darken">
                                    ул. Беды, 2Б </a>
              </div>
              <div className="middle-prop">
                <div className="show_on_map font_upper colored_theme_text">
                  <span className="text_wrap"
                    data-coordinates="53.930613,27.588529">
                    <i className="fas fa-map-marker-alt"></i>
                    <span className="text"> Показать на карте</span>
                  </span>
                </div>
                <div className="metro font_upper">
                  <i className="fas fa-subway"></i>
                  <span className="text muted777">Академия Наук - 1,3 км</span>
                </div>
              </div>
              <div className="schedule">
                <i className="far fa-clock"></i>
                <span
                  className="text font_xs muted777">ежедневно с 09.00 до 20.00 без обеда и выходных</span>
              </div>
            </div>
          </div>
          <div className="col-md-6 col-sm-4 col-xs-12 right-block-contacts">
            <div className="item-body">
              <div className="row">
                <div className="phones col-md-6 col-sm-12 col-xs-12">
                  <div className="phone font_sm darken">
                    <a href="tel:+375445670197" className="black">
                                            +375 (44) 567-01-97</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
}

export default ShopCard
