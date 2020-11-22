import React, { Component } from 'react'

import { withRouter } from 'react-router-dom'
import ReviewsBlock from '../company/review/ReviewsBlock'
import FlowersList from '../products/flower/FlowersList'
import CarouselComponent from '../common/carousel/CarouselComponent'
import ShopsBlock from '../company/shops/ShopsBlock'

class Home extends Component {
  render () {
    return (
      <div className="container-fluid">

        <div className="col-12">
          <div className="row mb-5">
            <CarouselComponent/>
          </div>

          <div className="row mb-2">
            <FlowersList/>
          </div>

          <div className="row">
            <ReviewsBlock/>
          </div>

          <ShopsBlock/>

        </div>
      </div>
    )
  }
}

export default withRouter(Home)
