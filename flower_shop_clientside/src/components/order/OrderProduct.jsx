import React, { Component } from 'react'

class OrderProduct extends Component {
  render () {
    return (

      <div>
        {this.props.orderProduct.product} - {this.props.orderProduct.quantity}
      </div>

    )
  }
}

export default OrderProduct
