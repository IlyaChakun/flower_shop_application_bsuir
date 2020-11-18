import React, { Component } from 'react'

class FlowerCard extends Component {
  render () {
    const { id, flowertype, cost } = this.props.flower
    return (
      <li className="item-wrapper col-xs-12">
        <div className="item clearfix  no_img  bordered box-shadow">
          <div className="top-info">
            <div className="title pull-left">
              {flowertype}
            </div>
          </div>
          <div className="body-info">
            <div className="preview-text">
              {cost}
            </div>
          </div>
        </div>
      </li>
    )
  }
}

export default FlowerCard
